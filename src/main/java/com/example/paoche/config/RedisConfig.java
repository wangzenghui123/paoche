package com.example.paoche.config;

import com.example.paoche.serielizer.MyRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig{

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new MyRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new MyRedisSerializer());
        return redisTemplate;
    }
}
//package com.example.paoche.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//public class RedisConfig {
//    @Value("${redis.maxIdle}")
//    private Integer maxIdle;
//
//    @Value("${redis.minIdle}")
//    private Integer minIdle;
//
//    @Value("${redis.maxTotal}")
//    private Integer maxTotal;
//
//    @Value("${redis.maxWait}")
//    private Integer maxWait;
//
//    @Value("${redis.timeout}")
//    private Integer timeout;
//
//    @Value("${redis.host}")
//    private String host;
//
//    @Value("${redis.port}")
//    private Integer port;
//
//    @Bean
//    public JedisPool getJedisPool(){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMinIdle(minIdle);
//        jedisPoolConfig.setMaxTotal(maxTotal);
//        jedisPoolConfig.setMaxWaitMillis(maxWait);
//        jedisPoolConfig.setEvictorShutdownTimeoutMillis(timeout);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout);
//        return jedisPool;
//
//    }
//}
