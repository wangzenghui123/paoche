package com.example.paoche.shiro;

import com.example.paoche.constants.Constant;
import com.example.paoche.service.RedisService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        MyUsernamePasswordToken myUsernamePasswordToken = (MyUsernamePasswordToken) token;
        myUsernamePasswordToken.getPrincipal();
        if(redisService.hasKey(Constant.ACCOUNT_LOCK_KEY)){
            return true;
        }
        return true;
    }
}
