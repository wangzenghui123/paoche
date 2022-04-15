package com.example.paoche.dao;

import com.example.paoche.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDao {

    List<SysPermission> findPermissionsByRoleId(String roleId);
    List<SysPermission> findAllPermissions();
}
