package com.example.paoche;

import com.example.paoche.entity.SysUser;
import com.example.paoche.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userServiceImpl;

    @Test
    public void set() throws InterruptedException {
        Long aLong = userServiceImpl.countUser();
        System.out.println(aLong);
        List<SysUser> userByPage = userServiceImpl.findUserByPage();
        for (SysUser sysUser : userByPage) {
            System.out.println(sysUser.toString());
        }
    }

}
