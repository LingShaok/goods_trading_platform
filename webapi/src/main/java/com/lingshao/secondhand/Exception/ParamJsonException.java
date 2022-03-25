package com.lingshao.secondhand.Exception;

/**
 * @description
 * @author LingShao
 * @date 2021/4/18
 */
public class ParamJsonException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ParamJsonException() {}

    public ParamJsonException(String message) {
        super(message);
        this.message = message;
    }


}
