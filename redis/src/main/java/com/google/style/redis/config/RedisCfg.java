package com.google.style.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * @author liangz
 * @date 2018/2/28 14:52
 * redis 配置
 **/
@Slf4j
@Configuration
public class RedisCfg {

    // Redis服务器地址
    @Value("${redis.host}")
    private String host;
    // Redis服务器连接端口
    @Value("${redis.port}")
    private int port;
    // Redis服务器连接密码（默认为空）
    @Value("${redis.password}")
    private String password;
    // 连接超时时间（毫秒）
    @Value("${redis.timeout}")
    private int timeout;
    // 连接超时时间（毫秒）
    @Value("${redis.database}")
    private int database;
    // 连接池最大连接数（使用负值表示没有限制）
    @Value("${redis.pool.max-active}")
    private int maxTotal;
    // 连接池最大阻塞等待时间（使用负值表示没有限制）
    @Value("${redis.pool.max-wait}")
    private int maxWaitMillis;
    // 连接池中的最大空闲连接
    @Value("${redis.pool.max-idle}")
    private int maxIdle;
    // 连接池中的最小空闲连接
    @Value("${redis.pool.min-idle}")
    private int minIdle;

    /**
     * 配置JedisPoolConfig
     * @return JedisPoolConfig实体
     */
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig() {
        log.info("---------初始化JedisPoolConfig--------------");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //  连接池最大连接数（使用负值表示没有限制）
        jedisPoolConfig.setMaxTotal(this.maxTotal);
        // 连接池最大阻塞等待时间（使用负值表示没有限制）
        jedisPoolConfig.setMaxWaitMillis(this.maxWaitMillis);
        // 连接池中的最大空闲连接
        jedisPoolConfig.setMaxIdle(this.maxIdle);
        // 连接池中的最小空闲连接
        jedisPoolConfig.setMinIdle(this.minIdle);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnCreate(true);
        jedisPoolConfig.setTestWhileIdle(true);
        return jedisPoolConfig;
    }

    /**
     * 实例化 RedisConnectionFactory 对象
     * @param poolConfig
     * @return
     */
    @Bean(name = "jedisConnectionFactory")
    public RedisConnectionFactory jedisConnectionFactory(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig poolConfig) {
        log.info("-------------初始化RedisConnectionFactory----------------------");
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(this.host);
        jedisConnectionFactory.setPort(this.port);
        jedisConnectionFactory.setDatabase(this.database);
        return jedisConnectionFactory;
    }

    /**
     *  实例化 RedisTemplate 对象 操作 String 类型数据
     *  StringRedisTemplate
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String,String> functionDomainRedisTemplate(@Qualifier(value = "jedisConnectionFactory") RedisConnectionFactory factory) {
        log.info("------初始化RedisTemplate----------");
        StringRedisTemplate template = new StringRedisTemplate(factory);
        //定义value的序列化方式
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
