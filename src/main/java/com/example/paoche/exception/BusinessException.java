package com.example.paoche.exception;

import com.example.paoche.exception.code.BaseResponseCode;

public class BusinessException extends RuntimeException{

    private final int code;

    private final String defaultMessage;

    public BusinessException(int code,String defaultMessage){
        super(defaultMessage);
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public BusinessException(BaseResponseCode baseResponseCode){
        this.code = baseResponseCode.getCode();
        this.defaultMessage = baseResponseCode.getMsg();
    }

    public int getCode(){
        return code;
    }

    public String getDefaultMessage(){
        return defaultMessage;
    }
}
