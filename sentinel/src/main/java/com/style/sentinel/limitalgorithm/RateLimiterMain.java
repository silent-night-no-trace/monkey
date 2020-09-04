package com.style.sentinel.limitalgorithm;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author leon
 * @date 2019-11-15 10:04:33
 */
public class RateLimiterMain {

	/**
	 * 令牌桶实现
	 */
	private static RateLimiter rateLimiter = RateLimiter.create(1000);

	private void test() {
		if (rateLimiter.tryAcquire()) {
			System.out.println("----执行-----");
		} else {
			System.out.println("执行失败");
		}
	}

	public static void main(String[] args) throws IOException {
		RateLimiterMain rateLimiterMain=new RateLimiterMain();
//		CountDownLatch countDownLatch=new CountDownLatch(1);
		Random random=new Random();
		for(int i=0;i<20;i++){
			new Thread(()->{
				try {
//					countDownLatch.await();
					Thread.sleep(random.nextInt(1000));
					rateLimiterMain.test();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
//		countDownLatch.countDown();
	}

}

