package com.example.paoche.dao;

import com.example.paoche.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {

    List<SysRole> findRolesByUserId(String id);
}
