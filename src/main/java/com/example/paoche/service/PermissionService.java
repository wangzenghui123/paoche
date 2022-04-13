package com.example.paoche.service;

import com.example.paoche.entity.SysPermission;

import java.util.List;

public interface PermissionService {
    List<SysPermission> findPermissionsByRoleId(String roleId);
}
