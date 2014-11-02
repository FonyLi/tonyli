package com.tjli.test.thread;

public class ThreadImplements implements Runnable{
	private String threadName;
	private ThreadPool pool;
	
	public static int constInt = 0;
	
	public ThreadImplements(String threadName, ThreadPool pool)
	{
		this.threadName = threadName;
		this.pool = pool;
	}
	
	public void run()
	{
		constInt++;
		System.out.println(threadName + " run " + "constInt is " + constInt);
		
	}
	public void start()
	{
		System.out.println(threadName + " start");
	}

}
