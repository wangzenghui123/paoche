package com.example.paoche.controller;

import com.example.paoche.entity.Permission;
import com.example.paoche.entity.Role;
import com.example.paoche.entity.User;
import com.example.paoche.service.impl.PermissionServiceImpl;
import com.example.paoche.service.impl.RoleServiceImpl;
import com.example.paoche.service.impl.UserServiceImpl;
import com.example.paoche.util.Md5HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private PermissionServiceImpl permissionServiceImpl;

    @RequestMapping("/")
    @ResponseBody
    public User getUser(){

        return userServiceImpl.findUserByName("a3");
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
