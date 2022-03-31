package com.example.paoche.service;

import com.example.paoche.exception.BusinessException;
import com.example.paoche.exception.code.BaseResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

//    @Autowired
//    private JedisPool jedisPool;
        @Autowired
        private RedisTemplate redisTemplate;

        public boolean hasKey(String key){
            if(key == null || key.equals("")) return  false;
            return redisTemplate.hasKey(key);
        }
        public long getExpire(String key,TimeUnit unit){
            return  redisTemplate.getExpire(key,unit);
        }

//    public boolean exists(String key){
//        Jedis jedis = null;
//        boolean result;
//        try{
//            jedis = jedisPool.getResource();
//            result = jedis.exists(key);
//            return result;
//        }finally {
//            if(jedis != null){
//                jedis.close();
//            }
//        }
//
//    }
//
//    public Long del(String... keys) {
//        Jedis jedis = null;
//        Long result;
//        try{
//            jedis = jedisPool.getResource();
//            result = jedis.del(keys);
//        }finally {
//            if(jedis != null){
//                jedis.close();
//            }
//        }
//        return result;
//    }
//
//    public String set(String key, String value) {
//        Jedis jedis = null;
//        String result;
//        try{
//            jedis = jedisPool.getResource();
//            result = jedis.set(key, value);
//        }finally {
//            if(jedis != null){
//                jedis.close();
//            }
//        }
//        return result;
//    }
//
//    public String get(String key) {
//        Jedis jedis = null;
//        String result;
//        try{
//            jedis = jedisPool.getResource();
//            result = jedis.get(key);
//        }finally {
//            if(jedis != null){
//                jedis.close();
//            }
//        }
//        return result;
//    }
}
