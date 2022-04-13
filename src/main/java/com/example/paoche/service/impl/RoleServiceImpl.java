package com.example.paoche.service.impl;

import com.example.paoche.dao.RoleDao;
import com.example.paoche.entity.SysRole;
import com.example.paoche.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<SysRole> findRolesByUserId(String id) {
        List<SysRole> sysRoleList = new ArrayList<>();
        System.out.println(">>>>>>>>>>>"+id);
        sysRoleList = roleDao.findRolesByUserId(id);
        return sysRoleList;
    }
}
