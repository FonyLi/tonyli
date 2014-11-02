package com.tjli.test.function;

public class TestInternalClass {
	
	public static void main(String[] args) {
		
		//继承普通类也可以
		System.out.println(new TestAbstract()
		{
			private int cc = 35;

			public int getCc() {
				return cc;
			}

			public void setCc(int cc) {
				this.cc = cc;
			}
			
			public int getMoney(){ return 1;}
			
			public int getMoney(int a){ return 1;}
			
		}.getCc());
	}

}
