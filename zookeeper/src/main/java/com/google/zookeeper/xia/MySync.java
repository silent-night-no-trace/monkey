package com.google.zookeeper.xia;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liangz
 * @date 2018/6/27 15:54
 **/
public class MySync extends ReentrantLock {

	private final Sync sync;

	public MySync(int permits){
		sync= new Sync(permits);
	}
	public class Sync extends AbstractQueuedSynchronizer{

		public Sync(int count){
			setState(count);
		}

		@Override
		protected int tryAcquireShared(int acquires) {
			for(;;){
				//尝试获取
				int available = getState();
				int remain = available -acquires;
				if(remain<0||compareAndSetState(available,remain)){
					return remain;
				}
			}
		}


		@Override
		protected boolean tryReleaseShared(int release) {
			//尝试释放
			for (;;){
				int current = getState();
				int next = current + release;
				if(next<current){
					try {
						throw new Exception("float exception");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					if (compareAndSetState(current,next)) {
						return true;
					}
				}
			}
		}

	}

	@Override
	public void lock() {
		sync.tryAcquireShared(1);
	}

	@Override
	public void unlock() {

	}
}


