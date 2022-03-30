package com.example.paoche.exception;

public class BusinessException extends RuntimeException{

    private final int code;

    private final String defaultMessage;

    public BusinessException(int code,String defaultMessage){
        super(defaultMessage);
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public int getCode(){
        return code;
    }

    public String getDefaultMessage(){
        return defaultMessage;
    }
}
