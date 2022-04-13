package com.example.paoche.dao;


import com.example.paoche.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao {
    SysUser findUserByName(String username);

}
