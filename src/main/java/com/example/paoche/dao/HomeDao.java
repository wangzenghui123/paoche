package com.example.paoche.dao;

import com.example.paoche.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeDao {
    SysPermission getHomeMenus();
}
