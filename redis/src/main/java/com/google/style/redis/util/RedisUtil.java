package com.google.style.redis.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author liangz
 * @date 2018/3/30 15:06
 **/
@Service
@SuppressWarnings("unused")
public class RedisUtil {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 写入redis缓存
     * @param key key值
     * @param value value值
     * @return 返回结果
     */
    public boolean setString(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
            stringStringValueOperations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入redis缓存,并设置失效时间
     * @param key key值
     * @param value value值
     * @param expireTime 失效时间
     * @param timeUnit 时间类型
     * @return 返回类型
     */
    public boolean setAdditionTime(final String key, String value, Long expireTime, TimeUnit timeUnit) {
        boolean result = false;
        try {
            ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();
            stringValueOperations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除
     * @param keys key值
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key key值
     * @return 返回结果
     */
    public boolean exists(final String key) {
        String value = redisTemplate.opsForValue().get(key);
        return !StringUtils.isEmpty(value);
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key key值
     * @return 返回结果
     */
    public boolean exists(final byte [] key) {
        String value = redisTemplate.opsForValue().get(key);
        return !StringUtils.isEmpty(value);
    }

    /**
     * 读取缓存
     * @param key key值
     * @return 返回结果
     */
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 读取缓存
     * @param key key值
     * @return 返回结果
     */
    public String get(final byte [] key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 读取缓存
     * @param key key值
     * @return 返回结果
     */
    public byte [] getBytes(final byte [] key) {
        return redisTemplate.opsForValue().get(key).getBytes();
    }

}
