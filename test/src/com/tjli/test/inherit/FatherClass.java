package com.tjli.test.inherit;


public class FatherClass extends GrandFatherClass{
	public FatherClass()
	{
		System.out.println("f");
	}
	
	public FatherClass( int a)
	{
		System.out.println("f");
	}
	
	void canUse()
	{
		System.out.println("can");
	}

	public static void main(String[] args) {
		FatherClass cc = new ChildClass();
		//cc.canUse();
	}
	
	public static void method(int a)
	{
		System.out.println("FatherClass method");
	}
}
