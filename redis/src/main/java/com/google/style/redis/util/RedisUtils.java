package com.google.style.redis.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * redis工具 (jedisPool)
 *
 * @author liangz
 */
@Data
@AllArgsConstructor
public class RedisUtils {
    /**
     * redis连接池
     */
    private JedisPool jedisPool;

    /**
     * set string
     *
     * @param key   key
     * @param value value
     */
    public void set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        }
    }

    /**
     * get string
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    /**
     * delete string
     *
     * @param key key
     */
    public void del(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        }
    }

    /**
     * set map
     *
     * @param key   key
     * @param field field
     * @param value value
     */
    public void hset(String key, String field, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(key, field, value);
        }
    }

    /**
     * set map
     *
     * @param key   key
     * @param field field
     * @param value value
     */
    public void hset(byte[] key, byte[] field, byte[] value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(key, field, value);
        }
    }

    /**
     * get map
     *
     * @param key   key
     * @param field field
     * @return value
     */
    public String hget(String key, String field) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hget(key, field);
        }
    }

    /**
     * get map
     *
     * @param key   key
     * @param field field
     * @return value
     */
    public byte[] hget(byte[] key, byte[] field) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hget(key, field);
        }
    }

    /**
     * delete map
     *
     * @param key   key
     * @param field field
     */
    public void hdel(String key, String field) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel(key, field);
        }
    }

    /**
     * get whole map
     *
     * @param key key
     */
    public Map<String, String> hgetAll(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hgetAll(key);
        }
    }

    /**
     * set value with expires
     *
     * @param key     key
     * @param timeout expires time
     * @param value   value
     */
    public void psetex(byte[] key, Long timeout, byte[] value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.psetex(key, timeout, value);
        }
    }

    /**
     * set value with expires
     *
     * @param key     key
     * @param timeout expires time
     * @param value   value
     */
    public void psetex(String key, Long timeout, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.psetex(key, timeout, value);
        }
    }

    /**
     * get bytes
     *
     * @param key key
     * @return value
     */
    public byte[] get(byte[] key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    /**
     * check exists
     *
     * @param key key
     * @return exists
     */
    public Boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    /**
     * check exists
     *
     * @param key key
     * @return exists
     */
    public Boolean exists(byte[] key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }
}
