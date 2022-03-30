package com.example.paoche.service;

import com.example.paoche.entity.User;

public interface UserService {
    User findUserByName(String username);
}
