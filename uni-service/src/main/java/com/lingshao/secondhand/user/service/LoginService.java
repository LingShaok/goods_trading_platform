package com.lingshao.secondhand.user.service;

import com.lingshao.secondhand.user.entity.User;

import java.util.Map;

/**
 * @author LingShao
 * @date 2021/4/18
 */

public interface LoginService {

    /**
     * @description 登录
     * @author LingShao
     * @date 2021/4/17
     */
    Map<String, Object> userLogin(String number, String password) throws Exception;

    /**
     * @description 管理员登录
     * @author LingShao
     * @date 2021/5/11
     * @return
     */
    Map<String, Object> adminLogin(String number, String password);
    /**
     * 获取个人中心信息
     * @author LingShao
     * @param user
     * @return
     */
    User getPersonalCenter(User user) throws Exception;


    /**
     * 修改用户密码
     * @author LingShao
     * @param userNumber 账号
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return
     */
    Integer updateUserPwd(String userNumber, String oldPwd, String newPwd) throws Exception;

}
