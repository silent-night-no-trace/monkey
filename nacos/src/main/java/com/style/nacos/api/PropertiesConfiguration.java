package com.style.nacos.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leon
 * @date 2019-11-11 17:46:56
 */
@Configuration
public class PropertiesConfiguration {

	@Bean
	public DruidProperties druidProperties(){
		return new DruidProperties();
	}
}
