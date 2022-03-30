package com.example.paoche.exception.handler;


import com.example.paoche.exception.BusinessException;
import com.example.paoche.exception.code.BaseResponseCode;
import com.example.paoche.util.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
@Slf4j
public class RestExceptionHandler{

    @ExceptionHandler(Exception.class)
    public DataResult exception(Exception e){
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public DataResult businessException(BusinessException e){
        return DataResult.getResult(e.getCode(),e.getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    <T> DataResult<T> methodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        return createValidExceptionResp(allErrors);
    }

    public <T>DataResult<T> createValidExceptionResp(List<ObjectError> allErrors){
        String[] msgs = new String[allErrors.size()];
        int i = 0;
        for (ObjectError error : allErrors) {
            msgs[i] = error.getDefaultMessage();
            log.info("msg={}",msgs[i]);
            i++;
        }
        return DataResult.getResult(BaseResponseCode.METHOD_IDENTITY_ERROR.getCode(),msgs[0]);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public DataResult createUnauthorizedExceptionResp(UnauthorizedException e){
        log.error("UnauthrizedException,{},{}",e.getLocalizedMessage(),e);
        return DataResult.getResult(BaseResponseCode.NOT_PERMISSION);
    }



}
