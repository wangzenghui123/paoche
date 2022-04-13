package com.example.paoche.service.impl;

import com.example.paoche.dao.PermissionDao;
import com.example.paoche.entity.SysPermission;
import com.example.paoche.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<SysPermission> findPermissionsByRoleId(String roleId) {
        List<SysPermission> sysPermissionList = new ArrayList<>();
        sysPermissionList = permissionDao.findPermissionsByRoleId(roleId);
        return sysPermissionList;
    }
}
