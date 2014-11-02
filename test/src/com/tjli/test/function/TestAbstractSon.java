package com.tjli.test.function;

public class TestAbstractSon extends TestAbstract {
	
	public int getMoney()
	{
		setA("getMoney()");
		
		return 11;
	}
	
	public int getMoney(int aa)
	{
		setA("haha");
		return 1;
	}

}
