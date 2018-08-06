package com.google.zookeeper.controller;

import com.google.zookeeper.utils.ZkClientUtils;
import com.google.zookeeper.utils.ZooKeeperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangz
 * @date 2018/6/25 16:45
 **/
@RestController
public class TestController {
	@Autowired
	private ZkClientUtils zkClientUtils;

	@RequestMapping("/test")
	public void test() {
		boolean b = zkClientUtils.isExistNode("/config/testone");
		System.out.println("节点是否存在：" + (b == true ? "是" : "否"));
		zkClientUtils.listenNodeDataChanged("/config/testone");
		if (!b) {
			zkClientUtils.createNode("/config/testone");
			System.out.println("节点创建完成！！！");
		}else{
			//节点删除
			zkClientUtils.deleteNode("/config/testone");
			zkClientUtils.createNode("/config/testone");
			System.out.println("节点创建完成！！！");
			zkClientUtils.listenNodeDataChanged("/config/testone");
		}
		zkClientUtils.updateData("/config/testone", "lalala");
		System.out.println("设置完成 ！！！");
	}
}
