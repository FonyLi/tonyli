package com.tjli.test.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
	public static final int THREAD_MAX_COUNT = 5;
	
	public List<Thread> pool = new ArrayList<Thread>(THREAD_MAX_COUNT);
	
	public List<Thread> idlePool = new ArrayList<Thread>();
	
	public ThreadPool()
	{
		for (int i = 0; i < THREAD_MAX_COUNT; i++)
		{
			//pool.add(new Thread(new com.tjli.test.thread.ThreadInheritance("T" + i, this)));
			pool.add(new ThreadInheritance("T" + i, this));
		}
	}
	
	public void init()
	{
		System.out.println("\n\n\nSTART");
		for (int i = 0; i < THREAD_MAX_COUNT; i++)
		{
			pool.get(i).start();
		}
	}
	
	public void handle(List<String> objects)
	{
		if (objects == null)
			return;
		
		for (int i = 0; i < objects.size(); i++)
		{
			
			//if no idle thread, wait.
			while(idlePool.size() == 0)
			{
				try
				{
					int ramdon = (int)Math.random() % 10;
					Thread.sleep(ramdon);
				}
				catch(Exception e)
				{
					
				}
			}
			
			//get a idle thread, start to handle one object
			ThreadInheritance threadInheritance = (ThreadInheritance)idlePool.get(0);
			threadInheritance.object = objects.get(i);
			
			
			synchronized(threadInheritance.lock)
			{

				idlePool.remove(threadInheritance);
				threadInheritance.lock.notify();
			}
		}
	}

}
