package com.tjli.test.inherit;

public class GrandFatherClass {
	public GrandFatherClass()
	{
		System.out.println("gf");
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
