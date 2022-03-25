package com.lingshao.secondhand.user.service;

import com.lingshao.secondhand.user.entity.User;
import org.omg.CORBA.StringHolder;

import java.util.List;

/**
 * @author LingShao
 * @date 2021/4/18
 */
public interface UserService {

    /**
     * @description 查找用户
     * @author LingShao
     * @date 2021/4/17
     */
    User selectUser(String Number, String Password) throws Exception;

    /**
     * @description 获取密码
     * @author LingShao
     * @date 2021/4/17
     */
    String selectPwdByNum(String Number);

    /**
     * @param userNumber 用户账号
     * @param newPwd     新密码
     * @description 修改用户密码
     * @author LingShao
     * @date 2021/4/17
     */
    Integer updatePwd(String userNumber, String newPwd);

    /**
     * @description 修改用户信息
     * @author LingShao
     * @date 2021/4/18
     * @return
     */
    Integer updateUserInfo(String userNumber, String user);

    /**
     * @description 获取所有用户
     * @author LingShao
     * @date 2021/5/11
     * @param @param null:
     * @return
     */
    List<User> selectAllUser();

    /**
     * @description 查看单个用户
     * @author LingShao
     * @date 2021/5/11
     */
    User getOneUser(String stuNumber);

    /**
     * @description 启用禁用用户
     * @author LingShao
     * @date 2021/5/11
     */
    Integer isValidUser(String stuNumber,String isValid);
}
