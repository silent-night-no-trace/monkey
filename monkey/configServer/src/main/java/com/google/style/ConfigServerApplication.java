package com.google.style;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author liangz
 * 配置服务
 * @date 20180130
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@RefreshScope
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
