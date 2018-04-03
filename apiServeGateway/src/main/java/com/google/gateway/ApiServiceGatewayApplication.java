package com.google.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author liangz
 * @date 2018/04/03 11:22
 *  网关配置
 */

@EnableZuulProxy
@EnableHystrix
@EnableCircuitBreaker
@SpringBootApplication
public class ApiServiceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceGatewayApplication.class, args);
	}
}
