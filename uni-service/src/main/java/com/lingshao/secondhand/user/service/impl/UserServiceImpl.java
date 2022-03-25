package com.lingshao.secondhand.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lingshao.secondhand.exception.BusinessException;
import com.lingshao.constant.ErrCode;
import com.lingshao.entity.PublicResultConstant;
import com.lingshao.secondhand.user.dao.UserDao;
import com.lingshao.secondhand.user.entity.User;
import com.lingshao.secondhand.user.service.UserService;
import com.lingshao.util.ComUtil;
import com.lingshao.util.LoginUtil;
import com.lingshao.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LingShao
 * @description 用户业务层
 * @date 2021/4/18
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Value("${mainDb}")
    private String mainDb;

    @Override
    public User selectUser(String Number, String Password) throws Exception {
        String dataPwd = userDao.getPwdByUserNum(Number);
        if (dataPwd!=null&&userDao.selectUserIsValid(Number)==1){
            throw new BusinessException(ErrCode.USER_IS_VALID,PublicResultConstant.USER_WAS_VALID);
        }
        if (dataPwd != null && !dataPwd.equals(Password)) {
            throw new BusinessException(ErrCode.PASSWORD_ERROR, PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
        if (dataPwd != null && dataPwd.equals(Password)) {
            User user = userDao.getUserInfo(Number, Password);
            return user;
        } else {
            //如果数据库没有，就去转发到教务系统
            Map<Integer, String> stuMap = LoginUtil.login(Number, Password);
            for (Integer key : stuMap.keySet()) {
                if (key == ErrCode.UNKOWN_ERROR) {
                    throw new BusinessException(key, stuMap.get(key));
                }
                if (key == ErrCode.PASSWORD_ERROR) {
                    throw new BusinessException(key, stuMap.get(key));
                } else {
                    userDao.addUserInfo(Number, Password, stuMap.get(key));
                    userDao.addStuInfo(Number, stuMap.get(key));
                }
            }
            return userDao.getUserInfo(Number, Password);
        }
    }

    @Override
    public String selectPwdByNum(String Number) {
        return userDao.getPwdByUserNum(Number);
    }

    @Override
    public Integer updatePwd(String userNumber, String newPwd) {
        return userDao.updateUserPwd(userNumber, newPwd);
    }

    @Override
    public Integer updateUserInfo(String userNumber, String jsonUser) {
        User user = JSONObject.parseObject(jsonUser,User.class);
        user.setUserNumber(userNumber);
        if (StringUtil.checkMobileNumber(user.getPhone()) == false) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.PHONE_ERROR);
        }
        if (user.getEmail().contains("@") == false) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.EMAIL_ERROR);
        }
        //判重
        List<User> tempUserList = userDao.getAllUserPEN();
        tempUserList = tempUserList.stream().filter(e -> !e.getUserNumber().equals(user.getUserNumber())).collect(Collectors.toList());
        tempUserList = tempUserList.stream().filter(e->e.getPhone()!=null&&e.getUserName()!=null&&e.getEmail()!=null).collect(Collectors.toList());
        List<User> samePhone = tempUserList.stream().filter(e -> e.getPhone().equals(user.getPhone())).collect(Collectors.toList());
        List<User> sameName = tempUserList.stream().filter(e -> e.getUserName().equals(user.getUserName())).collect(Collectors.toList());
        List<User> sameEmail = tempUserList.stream().filter(e -> e.getEmail().equals(user.getEmail())).collect(Collectors.toList());
        if (!ComUtil.isEmpty(samePhone)) {
            throw new BusinessException(ErrCode.USER_PHONE_EXIST, PublicResultConstant.PHONE_EXIST);
        }
        if (!ComUtil.isEmpty(sameName)) {
            throw new BusinessException(ErrCode.USER_NAME_EXIST, PublicResultConstant.NAME_EXIST);
        }
        if (!ComUtil.isEmpty(sameEmail)) {
            throw new BusinessException(ErrCode.USER_EMAIL_EXIST, PublicResultConstant.EMAIL_EXIST);
        }
        return userDao.updateStuInfo(userNumber, user);
    }

    @Override
    public List<User> selectAllUser() {
        List<User> dataUser = userDao.getAllUser();
        dataUser = dataUser.stream().filter(e->!e.getUserNumber().equals("admin")).collect(Collectors.toList());
        return dataUser;
    }

    @Override
    public User getOneUser(String stuNumber) {
        return userDao.getUserInfoById(stuNumber);
    }

    @Override
    public Integer isValidUser(String stuNumber,String isValid) {
        return userDao.isValidUser(stuNumber,isValid);
    }

}
