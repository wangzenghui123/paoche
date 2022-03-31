package com.example.paoche.service.impl;

import com.example.paoche.dao.UserDao;
import com.example.paoche.entity.User;
import com.example.paoche.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao;

    @Override
    public User findUserByName(String username) {
        User user = userdao.findUserByName(username);
        return user;
    }
}
