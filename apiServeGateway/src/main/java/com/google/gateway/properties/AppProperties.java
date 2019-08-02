package com.google.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 本应用的自定义参数
 *
 * @author liangz
 */
@Data
@ConfigurationProperties(prefix = "appConfig")
public class AppProperties {

	/**
	 * app名字
	 */
	private String appName;
	/**
	 * app 中文名
	 */
	private String appNameCn;
	/**
	 * Token加解密秘钥
	 */
	private String tokenKey;
	/**
	 * Token有效期，单位：天
	 */
	private Integer tokenDay;
}

