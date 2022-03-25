package com.lingshao.secondhand.Exception;

/**
 * @description 退出登录，清空Authorization,返回状态码
 * @author LingShao
 * @date 2021/4/18
 */
public class NoAuthorizationException extends RuntimeException{

    private String errmsg;

    public String getErrmsg() { return errmsg; }

    public void setErrmsg(String errmsg) { this.errmsg = errmsg; }

    public NoAuthorizationException(String errmsg) {
        super(errmsg);
    }
    public NoAuthorizationException() {
        super();
    }
}
