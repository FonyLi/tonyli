package com.tjli.test.threadold;

public class TestThread1{
	 public static void main(String[] args){
	  Runner1 r=new Runner1();
	  //r.run();
	  Thread t=new Thread(r);
	  t.start();
	  
	  try
	  {
		  for(int i=0;i<100;i++){
			  //Thread.sleep(1);
		   System.out.println("����Main Thread����״̬");

		   System.out.println(i);

		  }
		  
	  }
	  catch(Exception e)
	  {
		  
	  }
	 }
	}
	class Runner1 implements Runnable{
	 public void run(){
	  
		 try
		 {
			 for(int i=0;i<100;i++){
				 // Thread.sleep(1);
			   System.out.println("����Runner1����״̬");

			   System.out.println(i);

			  }
		 }
		 catch(Exception e)
		  {
			  
		  }
	  
	 }
	}