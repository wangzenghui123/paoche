package com.example.paoche.util;

import org.springframework.stereotype.Component;

@Component
public class InitializerUtil {

    public InitializerUtil(TokenSettings tokenSettings){
        JwtTokenUtil.setJwtTokenUtil(tokenSettings);
    }
}
