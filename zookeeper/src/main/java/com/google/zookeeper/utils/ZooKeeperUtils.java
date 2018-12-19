package com.google.zookeeper.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * ZookeeperUtils
 *
 * @author
 * @date 2018/5/14 15:26
 **/
@Slf4j
@Component
public class ZooKeeperUtils {

	/**
	 * session超时时间 ms
	 */
	private static final int SESSION_OUT_TIME = 2000;
	/**
	 * 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号
	 */
	private static final CountDownLatch connectedSemaphore = new CountDownLatch(3);
	/**
	 * zookeeper地址
	 */
	private static final String connectString = "47.98.172.225:2181,47.98.172.225:2182,47.98.172.225:2183";
	/**
	 * zookeeper 实例
	 */
	private static ZooKeeper zooKeeper;

	/**
	 * 获取 zookeeper 实例
	 */
	static {
		try {
			System.out.println("connect String : " + connectString);
			zooKeeper = new ZooKeeper(connectString,
					SESSION_OUT_TIME, event -> {
				//定义监听
				KeeperState state = event.getState();
				EventType type = event.getType();
				if (KeeperState.SyncConnected == state) {
					//建立连接事件
					if (EventType.None == type) {
						// 如果建立连接成功，则发送信号量，让后续阻塞程序向下执行
						log.info("----zk 实例创建-----当前连接：" + event.getPath());
						connectedSemaphore.countDown();
					}
				}
			});
		} catch (IOException e) {
			log.error("zookeeper 连接失败");
			e.printStackTrace();
		}
	}

	/**
	 * 创建节点
	 *
	 * @param nodePath 节点路径
	 * @param data     数据
	 */
	public static void createNode(String nodePath, String data) {
		log.info("创建节点[{}] 并设值", nodePath, data);
		try {
			zooKeeper.create(nodePath, StringUtils.isBlank(data) ? null : data.getBytes(),
					Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * zookeeper 节点设值
	 *
	 * @param nodePath 节点路径
	 * @param data     节点数据
	 */
	public void setData(String nodePath, String data) {
		try {
			zooKeeper.setData(nodePath, data.getBytes(), -1);
			log.info("节点[{}] 赋值{}", nodePath, data);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 节点是否存在
	 *
	 * @param nodePath 节点路径
	 * @return 存在/不存在
	 */
	public boolean nodeExists(String nodePath) {
		Stat exists = null;
		try {
			exists = zooKeeper.exists(nodePath, false);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			log.info("节点[{}] 是否存在：{}", nodePath, exists != null);
		}
		return exists != null;
	}

	/**
	 * 递归创建节点
	 *
	 * @param nodePath 节点路径
	 */
	public void createNodeCircle(String nodePath) {
		log.info("循环创建zk节点{}，节点不赋值", nodePath);
		String[] paths = nodePath.split("/");
		if (paths.length >= 2) {
			for (int i = 2; i <= paths.length; i++) {
				String path = String.join("/", Arrays.copyOf(paths, i));
				// 节点不存在，创建节点
				if (!nodeExists(path)) {
					createNode(path, null);
				}
			}
		}

	}

	/**
	 * 删除节点
	 *
	 * @param nodePath zk 节点路径
	 */
	public void delete(String nodePath) {
		log.info("删除节点{}", nodePath);
		if (nodeExists(nodePath)) {
			try {
				zooKeeper.delete(nodePath, -1);
			} catch (InterruptedException | KeeperException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取节点的值，节点不存在获取节点数据为空或空字符串，返回null
	 *
	 * @param nodePath zk 节点路径
	 * @return 节点数据
	 */
	public String getData(String nodePath) {
		// 节点不存在，返回null
		if (!nodeExists(nodePath)) {
			return null;
		}
		byte[] data = null;
		try {
			data = zooKeeper.getData(nodePath, false, null);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		log.info("获取节点[{}] 的值{}", nodePath, data == null ? null : new String(data));
		if (data == null) {
			return null;
		} else {
			String result = new String(data);
			return StringUtils.isNotBlank(result) ? result : null;
		}
	}

}
