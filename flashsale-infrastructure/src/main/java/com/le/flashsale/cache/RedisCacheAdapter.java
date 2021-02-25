
package com.le.flashsale.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import com.le.flashsale.adapter.CacheAdapter;
import com.le.flashsale.common.gson.GsonUtils;

/**
 * 基于Redis的缓存实现
 * <p>
 * Date 2020/11/17 11:28 上午
 * Author le
 */
@Service
public class RedisCacheAdapter implements CacheAdapter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private DefaultRedisScript<List<String>> defaultRedisScript;

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public void setnx(String key, String value) {
        stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public void setex(String key, String value) {
        stringRedisTemplate.opsForValue().setIfPresent(key, value);
    }

    @Override
    public Boolean del(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T getJson(String key, Class<T> type) {
        String json = get(key);
        return GsonUtils.fromJson(json, type);
    }

    @Override
    public Long decrementOne(String key) {
        return stringRedisTemplate.opsForValue().decrement(key);
    }

    @Override
    public Long decrement(String key, long nums) {
        return stringRedisTemplate.opsForValue().decrement(key, nums);
    }

    @Override
    public Long increaseOne(String key) {
        return stringRedisTemplate.opsForValue().increment(key);
    }

    @Override
    public Long increase(String key, long nums) {
        return stringRedisTemplate.opsForValue().increment(key, nums);
    }

    @Override
    public String execute(List<String> keys, Object... args) {
        return stringRedisTemplate.execute(defaultRedisScript, keys, args).get(0);
    }
}
