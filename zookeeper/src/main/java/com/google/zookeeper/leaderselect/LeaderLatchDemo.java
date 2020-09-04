package com.google.zookeeper.leaderselect;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author leon
 * @date 2019-09-17 15:59:08
 */
public class LeaderLatchDemo implements Closeable, LeaderLatchListener {

	private LeaderLatch leaderLatch;
	private String name;

	public LeaderLatchDemo(LeaderLatch leaderLatch, String name) {
		this.leaderLatch = leaderLatch;
		leaderLatch.addListener(this);
		this.name = name;
	}
	public void start(){
		try {
			leaderLatch.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		leaderLatch.close();
	}

	@Override
	public void isLeader() {
		System.out.println(name+"-is leader");
	}

	@Override
	public void notLeader() {
		System.out.println(name+"-is not a leader");

	}

	public static void main(String[] args) {
		//初始化
		CuratorFramework framework =
				CuratorFrameworkFactory.builder().connectString("47.98.172.225:2182")
						.connectionTimeoutMs(5000)
						.retryPolicy(new ExponentialBackoffRetry(3000, 3))
						.build();
		framework.start();
		LeaderLatch leaderLatch = new LeaderLatch(framework,"/leader");

		LeaderLatchDemo latchDemo = new LeaderLatchDemo(leaderLatch,"server->1");
		latchDemo.start();

		LeaderLatchDemo latchDemo1 = new LeaderLatchDemo(leaderLatch,"server->2");
		latchDemo1.start();

		LeaderLatchDemo latchDemo2 = new LeaderLatchDemo(leaderLatch,"server->3");
		latchDemo2.start();
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
