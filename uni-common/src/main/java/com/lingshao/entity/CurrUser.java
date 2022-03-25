package com.lingshao.entity;

import lombok.Data;
/**
 * @description 当前登录用户信息对象封装
 * @author LingShao
 * @date 2021/4/18
 * @param @param null:
 * @return
 */
@Data
public class CurrUser {
    //用户id
    private Integer userId;
    //用户学号
    private String number;
    //用户姓名
    private String name;
    //密码
    private String password;
}
