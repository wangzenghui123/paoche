package com.example.paoche.config;


import com.example.paoche.Realm.MyRealm;
import com.example.paoche.shiro.MyAccessControlFilter;
import com.example.paoche.shiro.MyHashedCredentialsMatcher;
import com.example.paoche.util.JwtTokenUtil;
import com.example.paoche.util.TokenSettings;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultSecurityManager);
        Map<String,Filter> filter = new HashMap<>();
        filter.put("f1",new MyAccessControlFilter());
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager webSecurityManager(MyRealm myRealm){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(myRealm);
        return defaultSecurityManager;
    }

    @Bean
    public MyRealm myRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return new MyRealm();
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        return new MyHashedCredentialsMatcher();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil(TokenSettings tokenSettings){
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        jwtTokenUtil. secretKey = tokenSettings.getSecretKey();
        jwtTokenUtil.accessTokenExpireTime = tokenSettings.getAccessTokenExpireTime();
        jwtTokenUtil.refreshTokenExpireTime = tokenSettings.getRefreshTokenExpireTime();
        jwtTokenUtil.refreshTokenExpireAppTime = tokenSettings.getRefreshTokenExpireAppTime();
        jwtTokenUtil.issuer = tokenSettings.getIssuer();
        return jwtTokenUtil;
    }
}
