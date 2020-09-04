package com.google.server.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * zk提供注册服务中心
 *
 * @author leon
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperServerCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperServerCenterApplication.class, args);
	}

}
