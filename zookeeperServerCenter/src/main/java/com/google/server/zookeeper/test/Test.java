package com.google.server.zookeeper.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leon
 * @date 2019-08-20 16:37:56
 */
public class Test {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		AwaitThread awaitThread = new AwaitThread(lock,condition);
		awaitThread.setName("thread await");
		awaitThread.start();
		SignalThread signalThread = new SignalThread(lock,condition);
		signalThread.setName("thread signal");
		signalThread.start();
	}

	static class AwaitThread extends Thread{
		private Lock lock;
		private Condition condition;
		AwaitThread(Lock lock, Condition condition){
			this.condition = condition;
			this.lock = lock;
		}

		@Override
		public void run() {
			System.out.println("-------await-----");
			try{
				lock.lock();
				condition.await();
				System.out.println("--end--await-----");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	static class SignalThread extends Thread{
		private Lock lock;
		private Condition condition;
		SignalThread(Lock lock, Condition condition){
			this.condition = condition;
			this.lock = lock;
		}

		@Override
		public void run() {
			System.out.println("-------signal-----");
			try{
				lock.lock();
				condition.signal();
				System.out.println("--end--signal-----");
			} finally {
				lock.unlock();
			}
		}
	}
}
