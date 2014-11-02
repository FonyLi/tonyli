package com.tjli.test.threadold;

public class Runner2 extends Thread{
	public void run()
	{
		System.out.println("run");
	}
	
	public void start()
	{
		System.out.println("start");
	}

}
