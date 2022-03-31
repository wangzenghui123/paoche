package com.example.paoche.cache;

import com.example.paoche.util.ApplicationContextUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;


public class RedisCache<K,V> implements Cache<K,V> {

    @Override
    public V get(K k) throws CacheException {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtil.getApplicationContext().getBean("redisTemplate");

        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
