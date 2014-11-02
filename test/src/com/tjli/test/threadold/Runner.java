package com.tjli.test.threadold;

public 	class Runner implements Runnable
{
	public void run()
	{
		System.out.println("run");
	}
	
	public void start()
	{
		System.out.println("start");
	}
}
