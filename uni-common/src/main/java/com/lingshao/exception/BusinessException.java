package com.lingshao.exception;

/**
 * 自定义异常
 *
 * @author bin
 * @since on 2021/03/19.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 3455708526465670030L;

    private Integer code;
    private String message;

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}