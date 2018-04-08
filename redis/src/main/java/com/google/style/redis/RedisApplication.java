package com.google.style.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * redis通用服务配置
 * @author liangz
 * @data 2018/02/28 14:41
 *
 */
@ComponentScan(basePackages={"com.google.*"})
@SpringBootApplication
public class RedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}
}
