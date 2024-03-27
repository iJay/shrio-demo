package com.example.shirojsp01.config;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author hanbing
 * @date 2024-03-27
 */
public class RedisCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new RedisCache<>(cacheName);
    }
}
