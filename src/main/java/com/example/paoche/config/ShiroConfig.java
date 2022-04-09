package com.example.paoche.config;
//
//
//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
//import com.example.paoche.Realm.MyRealm;
//import com.example.paoche.shiro.MyAccessControlFilter;
//import com.example.paoche.shiro.MyHashedCredentialsMatcher;
//import com.example.paoche.util.JwtTokenUtil;
//import com.example.paoche.util.TokenSettings;
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.mgt.DefaultSecurityManager;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.Filter;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfig {
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager defaultSecurityManager){
//        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        bean.setSecurityManager(defaultSecurityManager);
//
//        LinkedHashMap<String,Filter> filter = new LinkedHashMap<>();
//        filter.put("token",new MyAccessControlFilter());
//        bean.setFilters(filter);
//
//        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
//        linkedHashMap.put("/**","anon");
//
//        bean.setFilterChainDefinitionMap(linkedHashMap);
//        return bean;
//    }
//
//    @Bean
//    public DefaultWebSecurityManager webSecurityManager(MyRealm myRealm){
//        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
//        defaultSecurityManager.setRealm(myRealm);
//        return defaultSecurityManager;
//    }
//
//    @Bean
//    public MyRealm myRealm(MyHashedCredentialsMatcher myHashedCredentialsMatcher){
//        MyRealm myRealm = new MyRealm();
//        myRealm.setCredentialsMatcher(myHashedCredentialsMatcher);
//        return myRealm;
//    }
//
//    @Bean
//    public MyHashedCredentialsMatcher myHashedCredentialsMatcher(){
//        return new MyHashedCredentialsMatcher();
//    }
//
//    @Bean
//    public JwtTokenUtil jwtTokenUtil(TokenSettings tokenSettings){
//        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//        jwtTokenUtil. secretKey = tokenSettings.getSecretKey();
//        jwtTokenUtil.accessTokenExpireTime = tokenSettings.getAccessTokenExpireTime();
//        jwtTokenUtil.refreshTokenExpireTime = tokenSettings.getRefreshTokenExpireTime();
//        jwtTokenUtil.refreshTokenExpireAppTime = tokenSettings.getRefreshTokenExpireAppTime();
//        jwtTokenUtil.issuer = tokenSettings.getIssuer();
//        return jwtTokenUtil;
//    }
//
//    /**
//     * 开启shiro aop注解支持.
//     * 使用代理方式;所以需要开启代码支持;
//     * @Author:      小霍
//     * @UpdateUser:
//     * @Version:     0.0.1
//     * @param securityManager
//     * @return       org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
//     * @throws
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//    @Bean
//    @ConditionalOnMissingBean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//    @Bean
//    public ShiroDialect shiroDialect() {
//        return new ShiroDialect();
//    }
//}
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.paoche.Realm.MyRealm;
import com.example.paoche.shiro.MyAccessControlFilter;
import com.example.paoche.shiro.MyHashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

//    @Bean
//    public RedisCacheManager redisCacheManager(){
//        return new RedisCacheManager();
//    }

    @Bean
    public MyHashedCredentialsMatcher myHashedCredentialsMatcher(){
        return new MyHashedCredentialsMatcher();
    }
    @Bean("myRealm")
    public MyRealm myRealm(){
        MyRealm customRealm=new MyRealm();
        customRealm.setCredentialsMatcher(myHashedCredentialsMatcher());
        //customRealm.setCacheManager(redisCacheManager());
        return customRealm;
    }
    @Bean("securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm());
        return defaultWebSecurityManager;
    }
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        DefaultWebSecurityManager manager = (DefaultWebSecurityManager) context.getBean("securityManager");
        MyRealm customRealm = (MyRealm) context.getBean("myRealm");
        manager.setRealm(customRealm);
    }
    /**
     * shiro过滤器，配置拦截哪些请求
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param securityManager
     * @return       org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @throws
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //自定义拦截器限制并发人数,参考博客：
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //用来校验token
        filtersMap.put("token", new MyAccessControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断

        filterChainDefinitionMap.put("/api/user/login", "anon");
        filterChainDefinitionMap.put("/api/index/**","anon");
        filterChainDefinitionMap.put("/index","anon");
        filterChainDefinitionMap.put("/login.html","anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/treetable-lay/**", "anon");
        filterChainDefinitionMap.put("/api/user/token", "anon");
        //放开swagger-ui地址
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/","anon");
        filterChainDefinitionMap.put("/csrf","anon");
        filterChainDefinitionMap.put("/**","token,authc");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/api/user/unLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @Author:      小霍
     * @UpdateUser:
     * @Version:     0.0.1
     * @param securityManager
     * @return       org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @throws
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}