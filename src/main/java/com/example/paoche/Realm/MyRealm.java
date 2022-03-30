package com.example.paoche.Realm;


import com.example.paoche.entity.Permission;
import com.example.paoche.entity.Role;
import com.example.paoche.entity.User;
import com.example.paoche.service.PermissionService;
import com.example.paoche.service.UserService;
import com.example.paoche.service.impl.RoleServiceImpl;
import com.example.paoche.shiro.MyUsernamePasswordToken;
import com.example.paoche.util.ApplicationContextUtil;
import com.example.paoche.util.ByteSourceUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyUsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        UserService userServiceImpl = (UserService) ApplicationContextUtil.getApplicationContext().getBean("userServiceImpl");
        RoleServiceImpl roleServiceImpl = (RoleServiceImpl) ApplicationContextUtil.getApplicationContext().getBean("roleServiceImpl");
        PermissionService permissionServiceImpl = (PermissionService) ApplicationContextUtil.getApplicationContext().getBean("permissionServiceImpl");
        User user = userServiceImpl.findUserByName(username);
        if(user != null){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            List<Role> roles = roleServiceImpl.findRolesByUserId(user.getId());
            roles.forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getRolename());
                List<Permission> permissionsByRoleId = permissionServiceImpl.findPermissionsByRoleId(role.getId());
                permissionsByRoleId.forEach(permission -> {
                    simpleAuthorizationInfo.addStringPermission(permission.getPermission_name());
                });
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String  username = (String) token.getPrincipal();
        if (username != null && username != ""){
            UserService userServiceImpl = (UserService) ApplicationContextUtil.getApplicationContext().getBean("userServiceImpl");
            User user = userServiceImpl.findUserByName(username);
            String password = user.getPassword();
            String salt = user.getSalt();
            return new SimpleAuthenticationInfo(username,password, ByteSourceUtil.getSimpleByteSource(salt),this.getName());
        }
        return null;
    }
}
