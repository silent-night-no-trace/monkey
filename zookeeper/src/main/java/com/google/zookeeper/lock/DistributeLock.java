package com.google.zookeeper.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * 基于zk 实现分布式锁
 *
 * @author leon
 * @date 2019-09-16 17:31:43
 */
@Slf4j
public class DistributeLock {

	public static void main(String[] args) {
		//初始化
		CuratorFramework framework =
				CuratorFrameworkFactory.builder().connectString("47.98.172.225:2182")
						.connectionTimeoutMs(5000)
						.retryPolicy(new ExponentialBackoffRetry(3000, 3))
						.build();
		framework.start();

		InterProcessLock interProcessLock = new InterProcessMutex(framework, "/locks");
		for (int i = 0; i < 10; i++) {
			int finalI = i;
			new Thread(() -> {
				try {
					System.out.println("thread->" + finalI + "尝试获取锁");
					interProcessLock.acquire();
					System.out.println("thread->" + finalI + "获取锁成功");
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					TimeUnit.SECONDS.sleep(2);
					interProcessLock.release();
					System.out.println("thread->" + finalI + "释放锁成功");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}


			}, "thread->" + i).start();


		}
	}
}


