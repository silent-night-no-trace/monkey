package com.style.nacos.api;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosProperty;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author leon
 * @date 2019-11-11 17:37:29
 */

@NacosConfigurationProperties(prefix = "nacos.config", dataId = "nacos-config", type = ConfigType.YAML, autoRefreshed = true)
public class DruidProperties implements InitializingBean {
	@NacosProperty("server-addr")
	private String serverAddr;
	@NacosProperty("access-key")
	private String accessKey;
	@NacosProperty("namespace")
	private String namespace;
	@NacosProperty("encode")
	private String encode;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("accessKey:"+accessKey);
		System.out.println("namespace:"+namespace);
		System.out.println("encode:"+encode);
	}
}
