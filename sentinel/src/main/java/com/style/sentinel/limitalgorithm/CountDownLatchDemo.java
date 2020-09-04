package com.style.sentinel.limitalgorithm;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author leon
 * @date 2019-11-15 10:57:44
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			new Thread(() -> {
				try {
					Thread.sleep(new Random().nextInt(3000));
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				long count = countDownLatch.getCount();
				System.out.println("count:" + count);

				System.out.println("执行成功"+ Thread.currentThread().getName());


			},"thread-> "+i).start();

		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
