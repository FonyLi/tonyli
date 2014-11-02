package com.tjli.test.inherit;

public class GrandSonClass extends ChildClass{
	public GrandSonClass()
	{
		System.out.println("gs");
	}
	
	void canUse()
	{
		System.out.println("can");
	}

	public static void main(String[] args) {
		ChildClass cc = new ChildClass();
		//cc.canUse();
	}
}
