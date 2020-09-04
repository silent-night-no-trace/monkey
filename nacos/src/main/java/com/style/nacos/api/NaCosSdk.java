package com.style.nacos.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.client.config.impl.HttpSimpleClient;
import com.alibaba.nacos.client.utils.ParamUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author leon
 * @date 2019-11-11 15:30:02
 */
public class NaCosSdk {
	public static void main(String[] args) throws Exception {
		String serverAddress = "localhost:8848";
		String groupId = Constants.DEFAULT_GROUP;
		String dataId = "nacos-config";
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, serverAddress);
		ConfigService configService =
				NacosFactory.createConfigService(properties);
		//获取配置
		String config = configService.getConfig(dataId, groupId, 5000);
		System.out.println("config: "+config);
		//添加监听
		Listener customListener = new Listener() {
			@Override
			public Executor getExecutor() {
				return null;
			}

			@Override
			public void receiveConfigInfo(String configInfo) {
				System.out.println("配置信息:" + configInfo);
			}
		};
		configService.addListener(dataId, groupId,customListener );
		//添加配置
		//configService.publishConfig(dataId,groupId,"server.port=8818");
		//移除配置
		//configService.removeConfig(dataId,groupId);
		//configService.removeListener(dataId,groupId,customListener);
		System.in.read();
	}

	public static void main1(String[] args) throws Exception {
//		HttpSimpleClient.HttpResult httpResult = HttpSimpleClient.httpGet("http://localhost:8848/nacos/serverlist", null, null, null, 3000);
//		System.out.println(JSON.toJSONString(httpResult));

	}
}
