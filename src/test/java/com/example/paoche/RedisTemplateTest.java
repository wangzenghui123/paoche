package com.example.paoche;

import com.example.paoche.util.ApplicationContextUtil;
import com.example.paoche.util.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void set() throws InterruptedException {
//        redisTemplate.opsForValue().set("k2","123",10, TimeUnit.MINUTES);
//        while(true){
//            Thread.sleep(500);
//            Long k2 = redisTemplate.getExpire("k2");
//            System.out.println(k2);

        //RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtil.getApplicationContext().getBean("redisTemplate");
        System.out.println(redisTemplate.getClass());

    }

}
