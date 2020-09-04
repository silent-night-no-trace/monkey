package com.google.zookeeper.leaderselect;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.leader.Participant;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;

/**
 * zk 实现leader选举
 *
 * @author leon
 * @date 2019-09-17 15:19:42
 */
public class LeaderSelectorDemo extends LeaderSelectorListenerAdapter implements Closeable {

	private String name;
	private LeaderSelector leaderSelector;


	public LeaderSelectorDemo(CuratorFramework curatorFramework,String name,String path) {
		// 在大多数情况下，我们会希望一个 selector 放弃 leader 后还要重新参与 leader 选举
		this.leaderSelector = new LeaderSelector(curatorFramework,path,this);
		leaderSelector.autoRequeue();
		this.name = name;
		LeaderLatch leaderLatch = new LeaderLatch(curatorFramework,path);

	}

	public void start(){
		leaderSelector.start();
	}

	@Override
	public void takeLeadership(CuratorFramework client) throws Exception {
		System.out.println(name + " 现在是 leader 了，持续成为 leader ");
		//选举为 master，
		System.in.read();//阻塞，让当前获得 leader 权限的节点一直持有，直到该进程关闭
	}

	@Override
	public void close() throws IOException {
		leaderSelector.close();
	}

	public static void main(String[] args) {
		//初始化
		CuratorFramework framework =
				CuratorFrameworkFactory.builder().connectString("47.98.172.225:2182")
						.connectionTimeoutMs(5000)
						.retryPolicy(new ExponentialBackoffRetry(3000, 3))
						.build();
		framework.start();
		LeaderSelectorDemo selectorDemo = new LeaderSelectorDemo(framework,"server->"+1,"/leader");
		selectorDemo.start();
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}