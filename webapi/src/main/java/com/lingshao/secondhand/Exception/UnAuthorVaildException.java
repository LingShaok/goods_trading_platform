package com.lingshao.secondhand.Exception;

/**
 * @description
 * @author LingShao
 * @date 2021/4/18
 */
public class UnAuthorVaildException extends RuntimeException{

    public UnAuthorVaildException(String msg) {
        super(msg);
    }

    public UnAuthorVaildException() {
        super();
    }

}
