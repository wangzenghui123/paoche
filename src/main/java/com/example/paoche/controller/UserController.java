package com.example.paoche.controller;

import com.example.paoche.entity.Permission;
import com.example.paoche.entity.Role;
import com.example.paoche.entity.User;
import com.example.paoche.exception.BusinessException;
import com.example.paoche.exception.code.BaseResponseCode;
import com.example.paoche.service.impl.PermissionServiceImpl;
import com.example.paoche.service.impl.RoleServiceImpl;
import com.example.paoche.service.impl.UserServiceImpl;
import com.example.paoche.util.Md5HashUtil;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.Filter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private PermissionServiceImpl permissionServiceImpl;

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @RequestMapping("/")
    @ResponseBody
    public User getUser(){
        User user = new User();
        user.setUsername("wzh");
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        Filter token = filters.get("token");
        System.out.println(token == null);
        return user;
    }

    @RequestMapping("/user/login")
    public String login(){

        return "login";

    }
    @RequestMapping("/index")
    public void getIndex(){
        throw new BusinessException(BaseResponseCode.TOKEN_NOT_NULL);
    }

    public List<Role> getRoles(){
        return roleServiceImpl.findRolesByUserId("1");
    }


    public List<Permission> getPermissions(){
        return permissionServiceImpl.findPermissionsByRoleId("1");
    }


    public String getMd5Password(){
        String password = Md5HashUtil.hashPassword("123456", "tttttt", 1024);
        return password;
    }

}
