package com.tjli.test.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
	
	public static int b = 0;
	
	public static void main(String[] argv)
	{
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 0, 1,  //may throw exception
				TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(),
				new ThreadPoolExecutor.CallerRunsPolicy());
		
		
		 
		 
		 for (int i = 0; i < 10; i++) {    
			 System.out.print("for " + i + ", ");
	         executor.execute(new Runnable() {    
	      
	             public void run() {    
	                 try {    
	                     //Thread.sleep((long)(Math.random() * 1000));    
	                 } catch (Exception e) {    
	                     e.printStackTrace();    
	                 }    
	                 System.out.println(String.format("thread %d %d finished", b++, this.hashCode()));    
	             }    
	         });    
	     }    
	     executor.shutdown(); 

	}

}
