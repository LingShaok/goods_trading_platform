package com.lingshao.secondhand.user.service.impl;

import com.lingshao.secondhand.exception.BusinessException;
import com.lingshao.constant.CommonConstant;
import com.lingshao.constant.ErrCode;
import com.lingshao.entity.PublicResultConstant;
import com.lingshao.secondhand.service.RedisService;
import com.lingshao.secondhand.user.dao.UserDao;
import com.lingshao.secondhand.user.entity.User;
import com.lingshao.secondhand.user.service.LoginService;
import com.lingshao.secondhand.user.service.UserService;
import com.lingshao.util.ComUtil;
import com.lingshao.util.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author LingShao
 * @date 2021/4/18
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;

    @Resource
    private RedisService redisService;

    @Override
    public Map<String, Object> userLogin(String number, String password) throws Exception {
        Map<String, Object> map = new HashMap<>(4);
        User user = null;
        if (ComUtil.isEmpty(number) || ComUtil.isEmpty(password)) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
        //解密
        String newNumber = number;
        String newPassword = password;
//        String newNumber = RSAUtils.decryptDataOnJava(number, privateKey);
//        String newPassword = RSAUtils.encryptedDataOnJava(password, PUBLIC_KEY);
        user = userService.selectUser(number, password);

        String userNo = newNumber + "#" + newPassword + "#" + user.getStudentName();
        String token = JWTUtil.sign(userNo, newPassword, 120L);
        map.put("token", token);
        user.setUserPwd(null);
        map.put("userAccount", user);
        return map;
    }

    @Override
    public Map<String, Object> adminLogin(String number, String password) {
        User user = null;
        if (!number.equals("admin")){
            throw new BusinessException(ErrCode.ARGS_ERROR,PublicResultConstant.ERROR);
        }
        String PWD = userService.selectPwdByNum(number);
        if (PWD.equals(password)) {
            Map<String, Object> map = new HashMap<>(4);
            String userNo = number + "#" + password + "#" + "管理员";
            String token = JWTUtil.sign(userNo, "admin", 120L);
            user = userDao.getUserInfoById(number);
            map.put("token", token);
            user.setUserNumber(number);
            user.setUserPwd(null);
            map.put("userAccount", user);
            return map;
        }else {
            throw new BusinessException(ErrCode.ARGS_ERROR,PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
    }

    @Override
    public User getPersonalCenter(User user) throws Exception {
        User userInfo = userService.selectUser(user.getUserNumber(), user.getUserPwd());
        if (!ComUtil.isEmpty(userInfo)) {
            userInfo.setUserPwd(null);
        }
        return userInfo;
    }

    @Override
    public Integer updateUserPwd(String userNumber, String oldPwd, String newPwd) throws Exception {
        //非对称解秘
//        String dataPwd = RSAUtils.decryptDataOnJava(userService.selectPwdByNum(userNumber),privateKey);
        String dataPwd = userService.selectPwdByNum(userNumber);
        if (!oldPwd.equals(dataPwd)) {
            throw new BusinessException(ErrCode.PASSWORD_ERROR, PublicResultConstant.OLD_PWD_ERROR);
        }
        if (ComUtil.isEmpty(newPwd)) {
            throw new BusinessException(ErrCode.PASSWORD_ERROR, PublicResultConstant.PWD_IS_ERROR);
        }
//        String newPwda = RSAUtils.encryptedDataOnJava(newPwd, PUBLIC_KEY);
        String newPwda = newPwd;
        //修改
        userService.updatePwd(userNumber, newPwda);
        return CommonConstant.MESSAGE_CODE.SUCCESS;
    }
}
