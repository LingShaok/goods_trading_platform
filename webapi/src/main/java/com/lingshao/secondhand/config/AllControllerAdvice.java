package com.lingshao.secondhand.config;


import com.lingshao.secondhand.Exception.NoAuthorizationException;
import com.lingshao.secondhand.Exception.ParamJsonException;
import com.lingshao.secondhand.Exception.UnAuthorVaildException;
import com.lingshao.secondhand.Exception.UnauthorizedException;
import com.lingshao.secondhand.exception.BusinessException;
import com.lingshao.entity.PublicResultConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


/**
 * @description Controller统一异常处理
 * @author LingShao
 * @date 2021/4/18
 */
@ControllerAdvice
public class AllControllerAdvice {
    private static Logger logger = LoggerFactory.getLogger(AllControllerAdvice.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) { 
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseModel<String> errorHandler(Exception ex) {
        logger.error("接口出现严重异常：{}", ex.getMessage());
        if (ex.getMessage().contains("exception")) {
            return ResponseHelper.validationFailure(PublicResultConstant.FAILED);
        }else {
            return ResponseHelper.validationFailure(ex.getMessage());
        }
    }

    /**
     * 捕捉UnauthorizedException
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseModel<String> handleUnauthorized() {
        return ResponseHelper.validationFailure(PublicResultConstant.USER_NO_PERMITION);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UnAuthorVaildException.class)
    @ResponseBody
    public ResponseModel<String> handleUnauthorVaild() {
        return ResponseHelper.validationVaild(PublicResultConstant.UNAUTHORERROR);
    }

    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    @ExceptionHandler(NoAuthorizationException.class)
    @ResponseBody
    public ResponseModel<String> handleNoAuthorizationVaild() {
        return ResponseHelper.validAuthorization(PublicResultConstant.LOGIN_EXPIRE);
    }

    /**
     * 自定义异常抛出
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseModel<Void> btsException(BusinessException ex)throws Exception{
        logger.error("自定义错误", ex);
        ResponseModel result = new ResponseModel();
        result.setStatus(ex.getCode());
        result.setMessage(ex.getMessage());
        return result;
    }

//    /**
//     * 捕捉shiro的异常
//     * @param e
//     * @return
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(ShiroException.class)
//    @ResponseBody
//    public ResponseModel<String> handleShiroException(ShiroException e) {
//        return ResponseHelper.validationFailure(PublicResultConstant.USER_NO_PERMITION);
//    }


    /**
     * 参数异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = ParamJsonException.class)
    @ResponseBody
    public ResponseModel<String> handleParamJsonException(Exception e) {
        if(e instanceof ParamJsonException) {
            logger.error("参数错误："+e.getMessage());
            return ResponseHelper.validationFailure(PublicResultConstant.FAILED);
        }
        return ResponseHelper.validationFailure(PublicResultConstant.ERROR);
    }


}