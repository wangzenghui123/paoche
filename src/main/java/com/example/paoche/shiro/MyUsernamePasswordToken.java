package com.example.paoche.shiro;

import com.example.paoche.serielizer.MyRedisSerializer;
import org.apache.shiro.authc.AuthenticationToken;

public class MyUsernamePasswordToken implements AuthenticationToken {
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
