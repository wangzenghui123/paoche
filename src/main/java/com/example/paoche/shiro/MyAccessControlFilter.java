package com.example.paoche.shiro;

import com.alibaba.fastjson.JSON;
import com.example.paoche.constants.Constant;
import com.example.paoche.exception.BusinessException;
import com.example.paoche.exception.code.BaseResponseCode;
import com.example.paoche.util.DataResult;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Slf4j
public class MyAccessControlFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        servletRequest.setCharacterEncoding("UTF-8");
        log.info("进入过滤器....");
       HttpServletRequest request = (HttpServletRequest) servletRequest;
       String token = request.getHeader(Constant.ACCESS_TOKEN);
       try{
           if( token == null){
               log.info("token为null");
               throw new BusinessException(BaseResponseCode.TOKEN_NOT_NULL);
           }
           MyUsernamePasswordToken myUsernamePasswordToken = new MyUsernamePasswordToken(token);
           getSubject(servletRequest,servletResponse).login(myUsernamePasswordToken);
       }catch (BusinessException e){
           customResponse(e.getCode(),e.getDefaultMessage(),servletResponse);
           return false;
       }catch (AuthorizationException e){
           if(e.getCause() instanceof BusinessException){
               BusinessException exception = (BusinessException) e.getCause();
               customResponse(exception.getCode(),exception.getDefaultMessage(),servletResponse);
           }else{
               customResponse(BaseResponseCode.SHIRO_AUTHENTICATION_ERROR.getCode(), BaseResponseCode.SHIRO_AUTHENTICATION_ERROR.getMsg(), servletResponse);
               return false;
           }
       }
        return true;
    }

    public void customResponse(int code,String msg,ServletResponse response){
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            DataResult result = DataResult.getResult(code, msg);
            String jsonString = JSON.toJSONString(result);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            outputStream.write(jsonString.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
