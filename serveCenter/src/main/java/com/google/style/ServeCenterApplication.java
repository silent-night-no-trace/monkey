package com.google.style;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * serve center 中心
 * @author liangz
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class ServeCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeCenterApplication.class, args);
	}
}
