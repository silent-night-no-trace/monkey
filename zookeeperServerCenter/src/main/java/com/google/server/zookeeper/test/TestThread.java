package com.google.server.zookeeper.test;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leon
 * @date 2019-08-14 14:59:26
 */
public class TestThread {
	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		new ThreadPoolExecutor(3,5,60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
//		es.execute(()->{
//			System.out.println(1);
//		});
		Executors.newCachedThreadPool();
		Executors.newSingleThreadExecutor();
		Executors.newScheduledThreadPool(5);
		Executors.newWorkStealingPool();

		System.out.println(111|000);
		System.out.println(-1<<29);
		System.out.println(0<<29);
		//0001 0000 0000 0000 0000 0000 0000 00000
		System.out.println(1<<29);
		//0100 0000 0000 0000 0000 0000 0000 0000
		System.out.println(2<<29);
		//0110 0000 0000 0000 0000 0000 0000 0000
		System.out.println(3<<29);

		Lock lock = new ReentrantLock();

		ExecutorService es = Executors.newFixedThreadPool(3);

		Future<String> submit = es.submit(() -> "111");
		try {
			System.out.println(submit.get());
			es.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		FutureTask task = new FutureTask(()->"1");
		Thread thread = new Thread(task);
		thread.start();
		System.out.println(task.get());
	}
}
