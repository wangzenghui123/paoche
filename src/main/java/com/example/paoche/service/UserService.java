package com.example.paoche.service;

import com.example.paoche.entity.User;
import com.example.paoche.vo.req.LoginReqVO;
import com.example.paoche.vo.resp.LoginRespVO;

public interface UserService {
    User findUserByName(String username);
    LoginRespVO login(LoginReqVO loginReqVO);
}
