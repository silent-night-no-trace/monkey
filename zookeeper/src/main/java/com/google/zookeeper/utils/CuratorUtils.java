package com.google.zookeeper.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

/**
 * Curator 操作 API
 * @version 1.0
 * @author: liangz
 * @date: 2018/12/18 17:13
 */
@Slf4j
@Component
public class CuratorUtils {

	private CuratorFramework curatorFramework;

	public CuratorUtils(){
		try {
			curatorFramework = CuratorFrameworkFactory.builder()
					.connectString("47.98.172.225:2181,47.98.172.225:2182,47.98.172.225:2183")
					.connectionTimeoutMs(5000)
					.retryPolicy(new ExponentialBackoffRetry(3000,3))
					.namespace("Curator")
					.build();
			//开启
			curatorFramework.start();
		}catch (Exception e){
			log.error("curatorFramework 初始化异常！！！");
		}

	}

	public void createNode(String node,byte [] data){
		try {
			curatorFramework.create().forPath(node,data);
		} catch (Exception e) {
			log.error("创建-> "+node+" 节点异常！！！");
			e.printStackTrace();
		}
	}

	public Stat setData(String node,byte [] data){
		Stat stat = new Stat();
		try {
			 stat = curatorFramework.setData().forPath(node,data);
		} catch (Exception e) {
			log.error("更新 -> "+node+" 节点数据异常！！！");
			e.printStackTrace();
		}
		return stat;
	}

	public void deleteNode(String node){
		try {
			 curatorFramework.delete().forPath(node);
		} catch (Exception e) {
			log.error("删除 -> "+node+" 节点数据异常");
			e.printStackTrace();
		}
	}

	public byte [] getData(String node){
		try {
			return  curatorFramework.getData().forPath(node);
		} catch (Exception e) {
			log.error("获取 -> "+node+" 节点数据异常");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *	add 节点监听
	 * NodeCache  监听一个节点的更新和创建事件
	 * @param node  node
	 */
	public void addNodeListener(String node){
		try {
			NodeCache nodeCache = new NodeCache(curatorFramework,node);
			NodeCacheListener nodeCacheListener = () -> log.info("receive -> "+node+" node data change");
			nodeCache.getListenable().addListener(nodeCacheListener);
			nodeCache.start();
		} catch (Exception e) {
			log.info("添加 -> "+node+" node 监听异常");
			e.printStackTrace();
		}
	}

	/**
	 * add 节点 listener
	 * PathChildCache 监听一个节点下子节点的创建、删除、更新
	 * @param node node
	 */
	public  void addPathChildListener(String node){
		try {
			PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,node,true);
			PathChildrenCacheListener pathChildrenCacheListener = (curatorFramework, pathChildrenCacheEvent) -> log.info(node+"child event change and event type is : "+pathChildrenCacheEvent.getType());
			pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
			pathChildrenCache.start();
		} catch (Exception e) {
			log.error(node+" -> 添加child path 监听异常");
			e.printStackTrace();
		}
	}

	/**
	 * 添加节点listener
	 * TreeCache  综合PatchChildCache和NodeCache的特性
	 * @param node node
	 */
	public void addTreeListener(String node){
		try{
			TreeCache treeCache = new TreeCache(curatorFramework,node);
			TreeCacheListener treeCacheListener = new TreeCacheListener() {
				@Override
				public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
						log.info(node +" node receive event  and event type - >" +treeCacheEvent.getType());
				}
			};
			treeCache.getListenable().addListener(treeCacheListener);
			treeCache.start();
		}catch (Exception e){
			log.error(node+" 添加节点监听异常");
			e.printStackTrace();
		}
	}
}
