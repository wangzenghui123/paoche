package com.example.paoche;


import com.example.paoche.entity.User;

import com.example.paoche.service.impl.UserServiceImpl;
import com.example.paoche.util.ApplicationContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaocheApplicationTests {

//	@Autowired
//	private RedisService redisService;

	@Test
	public void contextLoads() {

	}
//	@Test
//	public void testRedis(){
//		boolean a = redisService.exists("a");
//		System.out.println(a);
//	}

}
