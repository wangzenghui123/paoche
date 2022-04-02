package com.example.paoche.shiro;

import com.example.paoche.constants.Constant;
import com.example.paoche.exception.BusinessException;
import com.example.paoche.exception.code.BaseResponseCode;
import com.example.paoche.service.RedisService;
import com.example.paoche.util.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        MyUsernamePasswordToken myUsernamePasswordToken = (MyUsernamePasswordToken) token;
        String accessToken = (String) myUsernamePasswordToken.getPrincipal();
        String userId = JwtTokenUtil.getUserId(accessToken);

        if(redisService.hasKey(Constant.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }

        if(redisService.hasKey(Constant.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }

        if(redisService.hasKey(Constant.JWT_ACCESS_TOKEN_BLACKLIST+accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }

        if(!JwtTokenUtil.validateToken(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }

        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)){
            if(redisService.getExpire(Constant.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }
        return true;
    }
}
