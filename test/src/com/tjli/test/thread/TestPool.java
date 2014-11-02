package com.tjli.test.thread;

import java.util.ArrayList;
import java.util.List;

public class TestPool {
	public static void main(String[] agvr)
	{
		com.tjli.test.thread.ThreadPool pool = new com.tjli.test.thread.ThreadPool();
		
		pool.init();
		
		try
		{
			Thread.sleep(100);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		List<String> objects = new ArrayList<String>(10);
		for (int i = 0; i < 10; i++)
		{
			objects.add("Object" + i);
		}
		
		pool.handle(objects);
	}

}
