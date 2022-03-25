package com.lingshao.secondhand.Exception;

/**
 * @description
 * @author LingShao
 * @date 2021/4/18
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
