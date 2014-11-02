package com.tjli.test.function;

import java.util.HashMap;
import java.util.Hashtable;


/**
 * @author Administrator
 *
 */
public class TestFunctions {
	
	/**
	 * @param infer
	 * @return
	 */
	public static Infer changeInfer(Infer infer)
	{
//		infer = new Infer();
		infer.setA(3);
		infer.setE("dd");
		return infer;
	}
	
	/**
	 * @param argc
	 */
//	public static void main(String[] argc) {
//		
//		Infer infer = new Infer();
//		infer.setA(2);
//		infer.setE("hehe");
//		changeInfer(infer);
//		System.out.print(infer.toString());
//		
//		
//		HashMap map = new HashMap();
//		Hashtable table = new Hashtable();
//		
//		
//		class AAA extends TestAbstract
//		{
//			public int getMoney()
//			{
//				return 1;
//			}
//			
//			public int getMoney(int a)
//			{
//				return 1;
//			}
//		}
//		
//		//Cannot instantiate the type TestAbstract
//		//TestAbstract aaa = new TestAbstract();
//		
//		TestAbstract aaa = new AAA();
//		
//	}
//	
	
	public static class TestFather
	{
		public int member = 1;
		public TestFather() {
			System.out.println("TestFather start");
			System.out.println(member);
			member = 2;
			System.out.println(member);
			
			System.out.println("TestFather end");
			// TODO Auto-generated constructor stub
			
		}
	}
	
	public static class TestSon extends TestFather
	{
		public int member = 3;
		
		public void TestFather()
		{
			System.out.println("TestFather son start");
			System.out.println(member);
			member = 4;
			System.out.println(member);
			System.out.println("TestFather son end");
		}
		
		public TestSon()
		{
			super();
			System.out.println(" son start");
			System.out.println(member);
			member = 5;
			super.member = 7;
			System.out.println(member);
			System.out.println("super" + super.member);
			System.out.println(" son end");
		}
		
		
		
	}
	
	public static void main(String[] argv)
	{
		TestSon son = new TestSon();
		int a = 0;
	}


}
