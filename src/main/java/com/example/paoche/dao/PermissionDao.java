package com.example.paoche.dao;

import com.example.paoche.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDao {

    List<Permission> findPermissionsByRoleId(String roleId);
}
