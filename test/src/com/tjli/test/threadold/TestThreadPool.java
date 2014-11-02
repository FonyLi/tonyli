package com.tjli.test.threadold;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool{
	
	public static int member = 1;
	
	public static void main(String[] args) {

		ExecutorService pool = Executors.newFixedThreadPool(2);
		//
//		Thread t1 = new Thread(new WithRun("name1"));
//		Thread t2 = new Thread(new WithRun("name2"));
//		Thread t3 = new Thread(new WithRun("name3"));
//		Thread t4 = new Thread(new WithRun("name4"));
//		Thread t5 = new Thread(new WithRun("name5"));
		
		Thread t1 = new TestThread("name1");
		
		TestThread t22 = new TestThread("name2");
		Thread t2 = t22;
		Thread t3 = new TestThread("name3");
		Thread t4 = new TestThread("name4");
		Thread t5 = new TestThread("name5");
		
		

		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);

		
		try
		{
			synchronized(t22.getLock())
			{
				t22.getLock().notifyAll();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		pool.shutdown();
	}


}
