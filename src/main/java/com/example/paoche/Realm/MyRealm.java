package com.example.paoche.Realm;


import com.example.paoche.constants.Constant;
import com.example.paoche.service.RedisService;
import com.example.paoche.service.RoleService;
import com.example.paoche.shiro.MyUsernamePasswordToken;
import com.example.paoche.util.ApplicationContextUtil;
import com.example.paoche.util.ByteSourceUtil;
import com.example.paoche.entity.Permission;
import com.example.paoche.entity.Role;
import com.example.paoche.entity.User;
import com.example.paoche.service.PermissionService;
import com.example.paoche.service.UserService;
import com.example.paoche.service.impl.RoleServiceImpl;
import com.example.paoche.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private RoleService roleServiceImpl;

    @Autowired
    private PermissionService permissionServiceImpl;

    @Autowired
    private RedisService redisService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyUsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        String userId = JwtTokenUtil.getUserId(token);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)&&JwtTokenUtil.getRemainingTime(token)< redisService.getExpire(Constant.JWT_REFRESH_KEY+token, TimeUnit.MILLISECONDS)){
            List<Role> rolesByUserId = roleServiceImpl.findRolesByUserId(userId);
            rolesByUserId.forEach(role -> {
                info.addRole(role.getRolename());
                List<Permission> permissionsByRoleId = permissionServiceImpl.findPermissionsByRoleId(role.getId());
                permissionsByRoleId.forEach(permission -> {
                    info.addStringPermission(permission.getPermission_name());
                });
            });
        }else{
            Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken(token);
            if(claimsFromToken.get(Constant.JWT_ROLES_KEY)!= null){
                info.setRoles((Set<String>) claimsFromToken.get(Constant.JWT_ROLES_KEY));
            }
            if(claimsFromToken.get(Constant.JWT_PERMISSIONS_KEY)!=null){
                info.setRoles((Set<String>) claimsFromToken.get(Constant.JWT_PERMISSIONS_KEY));
            }
        }
        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),this.getName());
    }
}
