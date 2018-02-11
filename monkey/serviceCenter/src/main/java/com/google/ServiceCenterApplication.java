package com.google;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 *
 * 注册中心
 * @author liangz
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class ServiceCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCenterApplication.class, args);
	}
}
