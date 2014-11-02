package com.tjli.test.threadold;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue {
	private static Queue<String> queue = new ConcurrentLinkedQueue<String>();
	private static final int threadNum = 3;
 	
	public static void main(String[] args) throws Exception {
		InsertThread it = new InsertThread();
		it.start();
		ConcumerThread pt = new ConcumerThread();
		for (int i = 0; i < threadNum; i++) {
			pt = new ConcumerThread();
			pt.start();
		}
	}

	public static boolean insert(String str) throws Exception {
		System.out.println("insert="+str+" , queue.size="+queue.size());
		queue.offer(str);
		
		if (queue.size() > 20000) {
		 // do some thing
		}
		return true;
	}
	
	public synchronized static String get(){
		if(queue.isEmpty()){
			return null;
		}
		return queue.poll();
	}
}

class ConcumerThread extends Thread {
	public ConcumerThread() {

	}

	public void run() {
		String str = "";
		while (true) {
			str = MessageQueue.get();
			if(str == null){
				System.out.println("ÈÎÎñ³ØÎª¿Õ£¡");
				try {
					Thread.sleep(5*1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println(Thread.currentThread().getName()+"="+str);
			}
		}
	}
}

class InsertThread extends Thread {
	public InsertThread() {

	}

	public void run() {
		int i = 0;
		while (true) {
			try {
				MessageQueue.insert("insert" + (i++));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

