package com.tjli.test.thread;

public class ThreadInheritance extends Thread {
	private String threadName;
	private ThreadPool pool;
	
	public String object;
	
	public final Object  lock = new Object();
	
	public ThreadInheritance(String threadName, ThreadPool pool)
	{
		this.threadName = threadName;
		this.pool = pool;
	}
//	
	public void run()
	{
		pool.idlePool.add(this);
		
		synchronized(lock)
		{
			try
			{
				while(true)
				{
					lock.wait();
					
					if (object != null)
						handle();
					
					lock.notify();
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
	private void handle()
	{		
		System.out.println(threadName + " start to handle " + object);
		try
		{
			int ramdon = (int)(Math.random() * 10);
			
			//System.out.println(threadName + " sleep " + ramdon);
			Thread.sleep(ramdon);
		}
		catch(Exception e)
		{
			
		}
		
		System.out.println(threadName + " finished handling " + object);
		
		object = null;
		
		pool.idlePool.add(this);
		
	}

}
