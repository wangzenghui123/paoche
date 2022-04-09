package com.example.paoche.service.impl;

import com.example.paoche.constants.Constant;
import com.example.paoche.dao.PermissionDao;
import com.example.paoche.dao.RoleDao;
import com.example.paoche.dao.UserDao;
import com.example.paoche.entity.Permission;
import com.example.paoche.entity.Role;
import com.example.paoche.entity.User;
import com.example.paoche.exception.BusinessException;
import com.example.paoche.exception.code.BaseResponseCode;
import com.example.paoche.service.RedisService;
import com.example.paoche.service.UserService;
import com.example.paoche.util.JwtTokenUtil;
import com.example.paoche.util.Md5HashUtil;
import com.example.paoche.vo.req.LoginReqVO;
import com.example.paoche.vo.resp.LoginRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RedisService redisService;

    @Override
    public User findUserByName(String username) {
        User user = userdao.findUserByName(username);
        return user;
    }

    @Override
    public LoginRespVO login(LoginReqVO loginReqVO) {
        User user = userdao.findUserByName(loginReqVO.getUsername());
        if(user == null){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(user.getStatus().equals("2")){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        if(!user.getPassword().equals(Md5HashUtil.hashPassword(loginReqVO.getPassword(),user.getSalt(),1024))){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        LoginRespVO loginRespVO = new LoginRespVO();
        Map<String,Object> claims = new HashMap<>();
        claims.put(Constant.JWT_ROLES_KEY,roleDao.findRolesByUserId(user.getId()));
        Set permissionSet = new HashSet();
        for (Role role : roleDao.findRolesByUserId(user.getId())) {
            List<Permission> permissionsByRoleId = permissionDao.findPermissionsByRoleId(role.getId());
            permissionSet.addAll(permissionsByRoleId);
        }
        claims.put(Constant.JWT_PERMISSIONS_KEY,permissionSet);
        claims.put(Constant.JWT_USER_NAME,user.getUsername());
        String accessToken = JwtTokenUtil.getAccessToken(user.getId(), claims);
        String refreshToken = JwtTokenUtil.getRefreshToken(user.getId(),claims);
        loginRespVO.setId(user.getId());
        loginRespVO.setAccess_token(accessToken);
        loginRespVO.setRefresh_token(refreshToken);
        loginRespVO.setUsername(user.getUsername());
        loginRespVO.setId(user.getId());
        return loginRespVO;
    }
}
