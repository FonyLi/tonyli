package com.tjli.test.function;


public class TestFinal {
	
	public static int aa = 3;
	public void addOne(final int x) {
		//return x++; //can't compile it

		}

	public void addOne(final Infer x) {
		x.setA(333);
		aa = 55;
		}
	
	public void addOne1(final Infer x) {
		//x = new Infer(); //can't compile it.
		}
	
	public void finalize()
	{
		aa = 4;
	}
	
	public static void main(String[] args) {
		Infer infer = new Infer();
		
		System.out.println(infer.getA());
		infer.setA(444);
		System.out.println(infer.getA());
		new TestFinal().addOne(infer);
		System.out.println(infer.getA());
	}
}
