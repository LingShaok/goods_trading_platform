package com.lingshao.secondhand.shiro;

import com.alibaba.fastjson.JSONObject;
import com.lingshao.secondhand.service.SpringContextBeanService;
import com.lingshao.entity.Constant;
import com.lingshao.entity.PublicResultConstant;
import com.lingshao.secondhand.config.ResponseHelper;
import com.lingshao.secondhand.user.entity.User;
import com.lingshao.secondhand.user.service.UserService;
import com.lingshao.util.ComUtil;
import com.lingshao.util.JWTUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * @description 代码的执行流程preHandle->isAccessAllowed->isLoginAttempt->executeLogin
 * @author LingShao
 * @date 2021/4/18
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    @Resource
    private UserService userService;

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
//        return true;
       // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        String authorization = httpServletRequest.getHeader("Authorization");
        if (verificationPassAnnotation(request, response, httpServletRequest, authorization)){
            return true;
        }
        if(ComUtil.isEmpty(authorization)){
            responseError(request,response);
            return false;
        }else {
            //如果过期
            if (!JWTUtil.verifyWhetherOverdue(authorization)) {
                responseError(request,response);
                return false;
            }
            //刷新token
            else {
                String userNo = JWTUtil.getUserNo(authorization);
                String userNum = userNo.split("#")[0];
                String userPwd = userNo.split("#")[1];
                String userName = userNo.split("#")[2];
                if(this.userService == null){
                    this.userService= SpringContextBeanService.getBean(UserService.class);
                }
                User user = userService.selectUser(userNum,userPwd);
//                UserInfo userAccount=userService.selectOneUser(userNum,Integer.parseInt(userType),userNum);
                //token数据
                String nowUserNo = userNum+"#"+userPwd+"#"+userName;
                String token = JWTUtil.sign(nowUserNo,user.getUserPwd(), 120L);
                httpServletResponse.setHeader("Authorization",token);
            }
        }
        return super.preHandle(request, response);
    }

    /**
     *  重写此方法，对所有请求放行，对于不合法请求，让后续方法进行处理
     * @param request
     * @param response
     * @param mappedValue
     * @return TRUE
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean urlVaild = true;
        if (isLoginAttempt(request, response)) {
            try {
                urlVaild = executeLogin(request, response);
                if (!urlVaild){
                    responseError(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                responseError(request, response);
            }
        }
        return urlVaild;
    }
    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        String url = httpServletRequest.getRequestURI();
        if (url.indexOf("/login")!=-1){
            setUserBean(request, response, token);
            return true;
        }else {
            if (!setUserBean(request, response, token)){
                request.setAttribute("nowMessage", PublicResultConstant.UNAUTHORERROR);
                return false;
            }
        }
        return true;
    }

    /**
     * 根据jwttoken 获取用户信息
     * @param request
     * @param response
     * @param token
     */
    private boolean setUserBean(ServletRequest request, ServletResponse response, JWTToken token) throws Exception {
        if(this.userService == null){
            this.userService= SpringContextBeanService.getBean(UserService.class);
        }
        //从token中获取用户工号
        String userNo =  JWTUtil.getUserNo(token.getPrincipal().toString());
        String userNum = userNo.split("#")[0];
        String userPwd= userNo.split("#")[1];
        String userName = userNo.split("#")[2];
        if(this.userService == null){
            this.userService= SpringContextBeanService.getBean(UserService.class);
        }
        User user = userService.selectUser(userNum,userPwd);
//        UserInfo userAccount=userService.selectOneUser(schoolNum,Integer.parseInt(userType),userNum);
        if (null==user || ComUtil.isEmpty(user.getUserNumber())){
            request.setAttribute("currentUser", null);
           return false;
        }else {
            User currUser=new User();
            if(!ComUtil.isEmpty(user.getId()))currUser.setId(user.getId());
            currUser.setUserNumber(userNum);
            currUser.setUserName(user.getUserName());
            currUser.setUserPwd(userPwd);
            currUser.setAvatar(user.getAvatar());
            currUser.setUserName(userName);
            request.setAttribute("currentUser", currUser);
        }
        return true;
    }

    /**
     * 非法url返回身份错误信息
     */
    private void responseError(ServletRequest request, ServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();
            response.setContentType("application/json; charset=utf-8");
            out.print(JSONObject.toJSONString(ResponseHelper.validAuthorization(PublicResultConstant.LOGIN_EXPIRE)));
            out.flush();
        } catch (Exception e) {
            //e.printStackTrace();
        }finally {
            if (out != null) {
                out.close();
            }
        }
    }
    /**
     * 验证请求方法是否有@Pass注解,有则直接放行
     * @param request
     * @param response
     * @param httpServletRequest
     * @param authorization
     * @return
     * @throws Exception
     */
    private boolean verificationPassAnnotation(ServletRequest request, ServletResponse response, HttpServletRequest httpServletRequest, String authorization) throws Exception {
        for (String urlMethod: Constant.METHOD_URL_SET) {
            String[] split = urlMethod.split(":--:");
            if(httpServletRequest.getRequestURI().contains(split[0])
                    && (split[1].equals(httpServletRequest.getMethod()) ||  split[1].equals("RequestMapping"))){
                Constant.isPass=true;
                if(ComUtil.isEmpty(authorization) || !JWTUtil.verifyWhetherOverdue(authorization)){
                    //如果当前url不需要认证，则注入当前登录用户时，给一个空的
                    httpServletRequest.setAttribute("currentUser",new User());
                    return true;
                }else {
                    super.preHandle(request, response);
                }
            }
            if(StringUtils.countMatches(urlMethod, "{")>0 &&
                    StringUtils.countMatches(urlMethod, "/") == StringUtils.countMatches(split[0], "/")
                    && (split[1].equals(httpServletRequest.getMethod()) ||  split[1].equals("RequestMapping"))){
                if(isSameUrl(split[0],httpServletRequest.getRequestURI())){
                    Constant.isPass=true;
                    if(ComUtil.isEmpty(authorization)){
                        httpServletRequest.setAttribute("currentUser",new User());
                        return true;
                    }else {
                        super.preHandle(request, response);
                    }
                }
            }
        }
        return false;
    }
    /**
     * 判断路径参数的url是否和controller方法url一致
     * @param localUrl
     * @param requestUrl
     * @return
     */
    private boolean isSameUrl(String localUrl,String requestUrl){
        String[] tempLocalUrls = localUrl.split("/");
        String[] tempRequestUrls = requestUrl.split("/");
        if(tempLocalUrls.length != tempRequestUrls.length){
            return false;
        }
        StringBuilder sbLocalUrl =new StringBuilder();
        StringBuilder sbRequestUrl =new StringBuilder();
        for (int i = 0; i < tempLocalUrls.length; i++) {
            if(StringUtils.countMatches(tempLocalUrls[i], "{") > 0){
                tempLocalUrls[i]="*";
                tempRequestUrls[i]="*";
            }
            sbLocalUrl.append(tempLocalUrls[i]+"/");
            sbRequestUrl.append(tempRequestUrls[i]+"/");
        }
        return sbLocalUrl.toString().trim().equals(sbRequestUrl.toString().trim());
    }

}
