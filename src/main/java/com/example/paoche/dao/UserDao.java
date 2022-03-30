package com.example.paoche.dao;


import com.example.paoche.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao {
    User findUserByName(String username);

}
