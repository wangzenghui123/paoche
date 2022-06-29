package com.example.paoche.dao;


import com.example.paoche.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserDao {
    SysUser findUserByName(String username);
    Long countUser();
    List<SysUser> findUserByPage();
    Long deleteUserById(String id);

}
