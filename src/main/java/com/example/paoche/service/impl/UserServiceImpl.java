package com.example.paoche.service.impl;

import com.example.paoche.constants.Constant;
import com.example.paoche.dao.PermissionDao;
import com.example.paoche.dao.RoleDao;
import com.example.paoche.dao.UserDao;
import com.example.paoche.entity.SysPermission;
import com.example.paoche.entity.SysRole;
import com.example.paoche.entity.SysUser;
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
    public SysUser findUserByName(String username) {
        SysUser sysUser = userdao.findUserByName(username);
        return sysUser;
    }

    @Override
    public LoginRespVO login(LoginReqVO loginReqVO) {
        SysUser sysUser = userdao.findUserByName(loginReqVO.getUsername());
        if(sysUser == null){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(sysUser.getStatus().equals("2")){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        if(!sysUser.getPassword().equals(Md5HashUtil.hashPassword(loginReqVO.getPassword(), sysUser.getSalt(),1024))){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        LoginRespVO loginRespVO = new LoginRespVO();
        Map<String,Object> claims = new HashMap<>();
        claims.put(Constant.JWT_ROLES_KEY,roleDao.findRolesByUserId(sysUser.getId()));
        Set permissionSet = new HashSet();
        for (SysRole sysRole : roleDao.findRolesByUserId(sysUser.getId())) {
            List<SysPermission> permissionsByRoleId = permissionDao.findPermissionsByRoleId(sysRole.getId());
            permissionSet.addAll(permissionsByRoleId);
        }
        claims.put(Constant.JWT_PERMISSIONS_KEY,permissionSet);
        claims.put(Constant.JWT_USER_NAME, sysUser.getUsername());
        String accessToken = JwtTokenUtil.getAccessToken(sysUser.getId(), claims);
        String refreshToken = JwtTokenUtil.getRefreshToken(sysUser.getId(),claims);
        loginRespVO.setId(sysUser.getId());
        loginRespVO.setAccess_token(accessToken);
        loginRespVO.setRefresh_token(refreshToken);
        loginRespVO.setUsername(sysUser.getUsername());
        loginRespVO.setId(sysUser.getId());
        return loginRespVO;
    }
}
