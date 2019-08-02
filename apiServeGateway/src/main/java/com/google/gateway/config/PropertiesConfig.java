package com.google.gateway.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Properties配置
 * 
 * @author leon
 *
 */
@EnableConfigurationProperties({com.google.gateway.properties.AppProperties.class})
@Configuration
public class PropertiesConfig {
	
}
