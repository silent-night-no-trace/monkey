package com.google.zookeeper.utils;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

/**
 * ZkClient 客户端实现
 *
 * @author liangz
 * @date 2018/6/25 11:58
 **/
@Slf4j
@Component
public class ZkClientUtils {
	/**
	 * zk集群的地址
	 */
	private String ZKServers = "47.98.172.225:2181,47.98.172.225:2182,47.98.172.225:2183";

	private ZkClient zkClient;

	public ZkClientUtils() {
		/**
		 * 创建会话
		 * new SerializableSerializer() 创建序列化器接口，用来序列化和反序列化
		 */
		zkClient = new ZkClient(ZKServers, 10000, 10000, new SerializableSerializer());
		log.info("-----------------创建连接成功-----------------");
	}

	/**
	 * 节点创建
	 *
	 * @param nodeName nodeName
	 */
	public void createNode(String nodeName) {

		/**
		 * final String path(节点的地址), Object data(数据的对象), final CreateMode mode(创建的节点类型)
		 * CreateMode.PERSISTENT：
		 */
		String path = zkClient.create(nodeName, "", CreateMode.PERSISTENT);
		//输出创建节点的路径
		System.out.println("created path:" + path);
	}

	/**
	 * 获取节点数据
	 *
	 * @param nodeName
	 */
	public Object getData(String nodeName) {
		Stat stat = new Stat();
		//获取 节点中的对象
		Object object = zkClient.readData(nodeName, stat);
		System.out.println("获取数据:" + object);
		System.out.println(stat);
		return object;
	}

	/**
	 * 判断节点是否存在的
	 *
	 * @param nodeName nodeName
	 * @return boolean
	 */
	public boolean isExistNode(String nodeName) {

		boolean e = zkClient.exists(nodeName);
		//返回 true表示节点存在 ，false表示不存在
		System.out.println(e);
		return e;
	}

	public void deleteNode(String nodeName) {
		if (isExistNode(nodeName)) {
			//节点存在
			int i = zkClient.countChildren(nodeName);
			if (i > 0) {
				//删除含有子节点的节点
				Boolean deleteStatus = zkClient.deleteRecursive(nodeName);
				log.info("多节点删除:" + (deleteStatus == true ? "成功" : "失败"));
			} else {
				//删除单独一个节点，返回true表示成功
				Boolean deleteStatus = zkClient.delete(nodeName);
				log.info("单节点删除:" + (deleteStatus == true ? "成功" : "失败"));
			}
		} else {
			log.info("nodeName:" + nodeName + " 该节点不存在！");
		}

	}

	/**
	 * 节点数据更新
	 *
	 * @param nodeName nodeName
	 * @param object   object
	 */
	public void updateData(String nodeName, Object object) {

		/**
		 * String path(节点的路径), Object object (传入的数据对象)
		 */
		zkClient.writeData(nodeName, object);
	}

	/**
	 * 监听 字节点变化
	 *
	 * @param nodeName nodeName
	 */
	public void listenChildNodeChanged(String nodeName) {
		/**
		 *	handleChildChange： 用来处理服务器端发送过来的通知
		 *  parentPath：对应的父节点的路径
		 *  currentChilds：子节点的相对路径
		 *  nodeName 监听的节点，可以是现在存在的也可以是不存在的
		 */
		zkClient.subscribeChildChanges(nodeName, (parentPath, list) -> {
			log.info("父节点：" + parentPath);
			log.info("子节点列表：" + list.toString());
		});

	}


	/**
	 * 监听节点数据修改删除
	 *
	 * @param nodeName nodeName
	 */
	public void listenNodeDataChanged(String nodeName) {
		zkClient.subscribeDataChanges(nodeName, new IZkDataListener() {
			@Override
			public void handleDataChange(String s, Object o) throws Exception {
				log.info(s + " :路径数据更新");
				log.info("节点：" + s + " ,修改后的数据：" + o.toString());
			}

			@Override
			public void handleDataDeleted(String s) throws Exception {
				log.info(s + " :路径数据删除");
			}
		});

	}
}
