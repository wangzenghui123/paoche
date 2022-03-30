package com.example.paoche.serielizer;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MyRedisSerializer implements RedisSerializer<Object> {
    private final Charset charset;


    public MyRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public MyRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public String deserialize(@Nullable byte[] bytes) {
        return bytes == null ? null : new String(bytes, this.charset);
    }


    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if( o == null){
            return new byte[0];
        }
        if( o instanceof String){
            return o.toString().getBytes(charset);
        }else{
            return JSON.toJSONString(o).getBytes(charset);
        }

    }

}
