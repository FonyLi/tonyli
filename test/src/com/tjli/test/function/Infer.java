package com.tjli.test.function;

import java.io.IOException;


public class Infer {
	public int a;
	private int b;
	private Integer c;
	private Integer d;
	private String e;
	
	public Infer()
	{};
	
	public Infer(String e)
	{ 
		this.e = e;
	};
	
	//The abstract method aaa in type Infer can only be defined by an abstract class
	//public abstract void aaa();
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public Integer getC() {
		return c;
	}
	public void setC(Integer c) {
		this.c = c;
	}
	public Integer getD() {
		return d;
	}
	public void setD(Integer d) {
		this.d = d;
	}
	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	public StringBuffer getF() {
		return f;
	}
	public void setF(StringBuffer f) {
		this.f = f;
	}
	@Override
	public String toString() {
		return "Infer [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e="
				+ e + ", f=" + f + "]";
	}
	private StringBuffer f;
	
	public boolean equals(Infer other)
	{
		return false;
	}
	
	public int hashCode()
    {
        return (int)System.currentTimeMillis();	
    }
	
	public void testException() throws IllegalArgumentException
	{
		try
		{
			IOException ioe = new IOException();
			
			if(true)
				throw ioe;
		}
		catch(IOException ioe)
		{
			
		}
		finally
		{
			IllegalArgumentException ie = new IllegalArgumentException();
			throw ie;
		}
	}

	public static void main(String[] args) {

		
		try
		{
			Infer infer = (Infer)Class.forName("com.tjli.test.function.Infer").getConstructor().newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


