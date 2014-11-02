package com.tjli.test.threadold;


public class WithRun extends Thread//implements Runnable
{
	public String name = "";
	
	public WithRun(String name)
	{
		this.name = name;
	}
	public void run()
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
}