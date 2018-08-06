package com.google.zookeeper.http;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liangz
 * @date 2018/6/26 17:19
 **/
public class Client{
	private Lock lock = new ReentrantLock();
	Semaphore semaphore = new Semaphore(3);
	private static String DEFAULT_HOST = "127.0.0.1";
	private static int DEFAULT_PORT = 12345;
	private static ClientHandle clientHandle;
	public static void start(){
		start(DEFAULT_HOST,DEFAULT_PORT);
	}
	public static synchronized void start(String ip,int port){
		if(clientHandle!=null)
			clientHandle.stop();
		clientHandle = new ClientHandle(ip,port);
		new Thread(clientHandle,"Server").start();
	}
	/**
	 * 向服务器发送消息
	 */
	public static boolean sendMsg(String msg) throws Exception{
		if(msg.equals("q")){
			return false;
		}
		clientHandle.sendMsg(msg);
		return true;
	}
	public static void main(String[] args){
		start();
	}
}
