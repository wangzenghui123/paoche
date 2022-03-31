package com.example.paoche.dao;

import com.example.paoche.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {

    List<Role> findRolesByUserId(String id);
}
