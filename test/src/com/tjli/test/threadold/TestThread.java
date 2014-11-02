package com.tjli.test.threadold;


public class TestThread extends Thread {
	
	public final static Object lock = new Object();
	public TestThread(){};
	public String name = "";
	
	public Object getLock()
	{
		return lock;
	}
	
	public TestThread(String name)
	{
		this.name = name;
	}
	
	public void run()
	{
		synchronized (lock)
		{
			try
			{
				lock.wait();
				System.out.println("myName " + name + " get  " + TestThreadPool.member);
				Thread.sleep(100);
				System.out.println("myName " + name + " add it " + ++TestThreadPool.member);
				
				//int a = 1 / 0;
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
	
	public void start()
	{
		try
		{
			System.out.println("myName " + name + " get  " + TestThreadPool.member);
			Thread.sleep(100);
			System.out.println("myName " + name + " add it " + ++TestThreadPool.member);
		}
		catch(Exception e)
		{
			
		}
	}
	
//	public static void main(String[] args) {
//		TestThread t = new TestThread("self");
//		t.start();
//		t.run();
//		
//		
//		
//
//	}

}
