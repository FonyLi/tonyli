package com.tjli.test.inherit;


public class ChildClass extends FatherClass {
	public ChildClass()
	{
		System.out.println("c");
	}

	public static void main(String[] args) {
		
		FatherClass.method(3);
		ChildClass.method(4);
		
		new GrandSonClass();
	}
	
	public static void method(int a)
	{
		System.out.println("ChildClass method");
	}
}
