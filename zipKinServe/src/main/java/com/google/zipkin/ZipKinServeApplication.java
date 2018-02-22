package com.google.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

/**
 * @author liangz
 * @date  2018/01/30
 *  链路追踪 serve
 */

@EnableZipkinServer
@EnableEurekaClient
@SpringBootApplication
public class ZipKinServeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipKinServeApplication.class, args);
	}
}
