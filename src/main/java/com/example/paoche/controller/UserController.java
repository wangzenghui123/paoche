package com.example.paoche.controller;

import ch.qos.logback.classic.servlet.LogbackServletContainerInitializer;
import com.example.paoche.entity.Permission;
import com.example.paoche.entity.Role;
import com.example.paoche.entity.User;
import com.example.paoche.exception.BusinessException;
import com.example.paoche.exception.code.BaseResponseCode;
import com.example.paoche.service.impl.PermissionServiceImpl;
import com.example.paoche.service.impl.RoleServiceImpl;
import com.example.paoche.service.impl.UserServiceImpl;
import com.example.paoche.util.DataResult;
import com.example.paoche.util.Md5HashUtil;
import com.example.paoche.vo.req.LoginReqVO;
import com.example.paoche.vo.resp.LoginRespVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Filter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private PermissionServiceImpl permissionServiceImpl;

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @RequestMapping("/index/main")
    public String index(){
        return "main";
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public DataResult login(@RequestBody LoginReqVO loginReqVO){
        DataResult dataResult = new DataResult<>(0,"success");
        LoginRespVO loginRespVO = userServiceImpl.login(loginReqVO);
        System.out.println(loginRespVO.getAccess_token());
        dataResult.setData(loginRespVO);
        return dataResult;
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


}
