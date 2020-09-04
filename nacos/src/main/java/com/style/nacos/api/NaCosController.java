package com.style.nacos.api;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leon
 * @date 2019-11-11 14:47:07
 */
@RestController
@RequestMapping("/naCos")
@NacosPropertySource(dataId = "app:autoUpdate",autoRefreshed = true)
public class NaCosController implements ApplicationContextAware {

	@NacosValue(value = "${autoUpdate:false}", autoRefreshed = true)
	private Boolean autoUpdate;

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@RequestMapping("/get")
	public Boolean getConfig() {
		return autoUpdate;
	}

	@RequestMapping("/getProperties")
	public DruidProperties druidProperties(){
		return applicationContext.getBean(DruidProperties.class);
	}

	public static void main(String[] args) {
		System.out.println((10497*3600)/18);
	}
}
