package com.tjli.test.exception;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>Title: TestException.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2014<／p>
 * <p>Company: TJLI<／p>
 * @author Tony Li
 * @date 22/04/2014  12:13:52 PM
 * @version 1.0
 */
public class TestException {

	
	ReadWriteLock lock = new ReentrantReadWriteLock();
	public static final void main(String[] argv)
	{
		
		TestException test = new TestException();
//		System.out.println(test.method3());
		
		System.out.print(test.m1());
	}
	
	private String m1()
	{
		try
		{
			System.out.print("A");
			int a = 1 / 0;
			
			return "D";
		}
		catch(Exception e)
		{
			System.out.print("B");
			
			return m2() + "Z";
		}
		finally
		{
			System.out.print("C");
		}
	}
	
	private String m2()
	{
		System.out.print("X");
		return "Y";
	}
	
	private int method2()
	{
		lock.writeLock();
		lock.readLock();
		try
		{
			throw new Exception();
		}
		catch(Exception e)
		{
			return 2;
		}
		finally
		{
			return 3;
		}
	}
	
	private int method3()
	{
		try
		{
			return 1;
		}
		catch(Exception e)
		{
		}
		finally
		{
			return 2;
		}
	}
	
	private int method()
	{
		System.out.println(1);
		try
		{
			try
			{
				System.out.println(8);
				throw new Exception();
			}
			catch(Exception e)
			{
				System.out.println(9);
				return 11;
			}
			finally
			{
				System.out.println(10);
				
				//return 12;
			}
			
			//throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(4);
			return 6;
		}
		finally
		{
			System.out.println(5);
			return 13;
		}
	}
}
