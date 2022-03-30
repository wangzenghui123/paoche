package com.example.paoche.service;

import com.example.paoche.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findPermissionsByRoleId(String roleId);
}
