package com.example.paoche.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

public class MyUsernamePasswordToken   implements AuthenticationToken {
    private String token;

    public MyUsernamePasswordToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
