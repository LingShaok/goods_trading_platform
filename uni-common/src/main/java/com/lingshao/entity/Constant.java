package com.lingshao.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LingShao
 */
public class Constant {

    //验证码过期时间
    public static final Long PASS_TIME =  50000 * 60 *1000L;

    public static Set<String> METHOD_URL_SET = new HashSet<>();

    public static boolean isPass=false;

    //启用
    public static final int ENABLE = 1;
    //禁用
    public static final int DISABLE = 0;

    public static final String DBNameBefore = "t_bd_log";

    public class RoleType{
        //超级管理员
        public static final String SYS_ASMIN_ROLE= "sysadmin";
        //管理员
        public static final String ADMIN= "admin";
        //普通用户
        public static final String USER= "user";
    }



}
