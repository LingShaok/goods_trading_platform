package com.lingshao.secondhand.user.dao;

import com.lingshao.secondhand.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LingShao
 * @date 2021/4/18
 */

@Repository
public interface UserDao {
    /**
     * @param studentNum
     * @param studentPwd
     * @return User
     * @description 查询是否存在这个学生
     * @author LingShao
     * @date 2021/4/17
     */
    User getUserInfo(@Param("stuNum") String studentNum, @Param("stuPwd") String studentPwd);

    /**
     * @description 查看用户是否禁用
     * @author LingShao
     * @date 2021/5/11
     */
    Integer selectUserIsValid(@Param("studentNum") String studentNum);

    /**
     * @description 获取所有用户的手机号码+邮箱+昵称
     * @author LingShao
     * @date 2021/5/1
     */
    List<User> getAllUserPEN();

    /**
     * @param studentNum  学号
     * @param studentName 学生姓名
     * @param studentPwd  密码
     * @description 向数据库中插入学生账号信息
     * @author LingShao
     * @date 2021/4/17
     */
    Integer addUserInfo(@Param("stuNum") String studentNum, @Param("stuPwd") String studentPwd, @Param("stuName") String studentName);

    /**
     * @param studentNum  学号
     * @param studentName 姓名
     * @return
     * @description 向数据库中插入学生信息
     * @author LingShao
     * @date 2021/4/18
     */
    Integer addStuInfo(@Param("stuNum") String studentNum, @Param("stuName") String studentName);

    /**
     * @param @param null:
     * @return
     * @description 获取密码
     * @author LingShao
     * @date 2021/4/17
     */
    String getPwdByUserNum(@Param("schNum") String studentNum);

    /**
     * @param studentNum 账号
     * @param newPwd     新密码
     * @return
     * @description 修改密码
     * @author LingShao
     * @date 2021/4/17
     */
    Integer updateUserPwd(@Param("userNum") String studentNum, @Param("newPwd") String newPwd);
    /**
     * @param studentNum 账号
     * @description 修改个人信息
     * @author LingShao
     * @date 2021/4/17
     */
    Integer updateStuInfo(@Param("userNum") String studentNum, @Param("user") User user);

    /**
     * @description 修改头像路径
     * @author LingShao
     * @date 2021/4/18
     */
    Integer updateAvatar(@Param("userNum") String studentNum, @Param("avatarDir") String avatarDir);

    /**
     * @description 查看用户的基本信息是否完善
     * @author LingShao
     * @date 2021/4/28
     * @param @param null:
     */
    User getUserInfoById(@Param("stuNumber") String studentNum);

    /**
     * @description 获取所有用户信息
     * @author LingShao
     * @date 2021/5/11
     */
    List<User> getAllUser();

    /**
     * @description 启用禁用
     * @author LingShao
     * @date 2021/5/11
     */
    Integer isValidUser(@Param("stuNumber") String stuNumber,@Param("isValid") String isValid);
}
