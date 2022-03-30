package com.example.paoche.service;

import com.example.paoche.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findRolesByUserId(String id);
}
