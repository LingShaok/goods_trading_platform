package com.lingshao.secondhand.config;

import org.springframework.http.HttpStatus;

/**
 * 统一返回相应参数
 * @author LingShao
 */
public class ResponseHelper {

    public ResponseHelper() {
    }

    public static <T> ResponseModel<T> notFound(String message) {
        ResponseModel response = new ResponseModel();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        //response.setCode(HttpStatus.NOT_FOUND.getReasonPhrase());
        response.setMessage(message);
        return response;
    }

    public static <T> ResponseModel<T> internalServerError(String message) {
        ResponseModel response = new ResponseModel();
        response.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
        //response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setMessage(message);
        return response;
    }

    public static <T> ResponseModel<T> validationFailure(String message) {
        ResponseModel response = new ResponseModel();
        response.setStatus(502);
        //response.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMessage(message);
        return response;
    }

    public static <T> ResponseModel<T> validationVaild(String message) {
        ResponseModel response = new ResponseModel();
        response.setStatus(506);
        response.setMessage(message);
        return response;
    }

    public static <T> ResponseModel<T> buildResponseModel(T result) {
        ResponseModel response = new ResponseModel();
        response.setStatus(HttpStatus.OK.value());
        //response.setCode(HttpStatus.OK.getReasonPhrase());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setResult(result);
        return response;
    }

    public static <T> ResponseModel<T> validAuthorization(String message) {
        ResponseModel response = new ResponseModel();
        response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
        response.setMessage(message);
        return response;
    }
}
