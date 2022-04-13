package com.example.paoche.service;

import com.example.paoche.entity.SysRole;

import java.util.List;

public interface RoleService {
    List<SysRole> findRolesByUserId(String id);
}
