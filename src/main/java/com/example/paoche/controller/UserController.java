package com.example.paoche.controller;

import com.example.paoche.entity.SysPermission;
import com.example.paoche.entity.SysRole;
import com.example.paoche.exception.BusinessException;
import com.example.paoche.exception.code.BaseResponseCode;
import com.example.paoche.service.impl.PermissionServiceImpl;
import com.example.paoche.service.impl.RoleServiceImpl;
import com.example.paoche.service.impl.UserServiceImpl;
import com.example.paoche.util.DataResult;
import com.example.paoche.vo.req.LoginReqVO;
import com.example.paoche.vo.resp.LoginRespVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    @RequestMapping("/index/main")
    public String index(){
        return "main";
    }
    @RequestMapping("/login")
    public String userlogin(){
        return "login";
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public DataResult login(@RequestBody LoginReqVO loginReqVO){
        System.out.println(11);
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

    public List<SysRole> getRoles(){
        return roleServiceImpl.findRolesByUserId("1");
    }


    public List<SysPermission> getPermissions(){
        return permissionServiceImpl.findPermissionsByRoleId("1");
    }


}
