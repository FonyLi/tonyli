package com.tjli.test.function;

import com.tjli.test.threadold.TestThread;

public abstract class TestAbstract extends TestThread implements Runnable {
	
	public void TestAbstract(){};
	
	private String a = "hehe";

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
	
	public abstract int getMoney();
	
	public abstract int getMoney(int aa) ;

//	{
//		this.a = "haha";
//		//return 1;
//	}
	
	public static void main(String[] args) {
		
		class FatherClass {
			  public FatherClass() {
			    System.out.println("FatherClass Create");
			  }
			}

			class  ChildClass extends FatherClass {
			  public ChildClass() {
			    System.out.println("ChildClass Create");
			  }   
			  public void main(String[] args) {
			    FatherClass fc = new FatherClass();
			    ChildClass cc = new ChildClass();
			  }
			}

			new ChildClass().main(null);
			
		class A{
			public A(int a){};
		};
		abstract class B extends A{
			public B(){
				super(1);
			};
		};
		
		class C extends B
		{
			
		}
		
		C c = new C();
		
	}

}
