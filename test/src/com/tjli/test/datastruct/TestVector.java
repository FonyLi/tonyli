package com.tjli.test.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class TestVector {
	private Vector<Integer> a;
	private LinkedList<Integer> b;
	private ArrayList<Integer> c;
	
	private List<Integer> d;
	
	public List<Integer> getD() {
		return d;
	}
	public void setD(List<Integer> d) {
		this.d = d;
	}
	public Vector<Integer> getA() {
		return a;
	}
	public void setA(Vector<Integer> a) {
		this.a = a;
	}
	public LinkedList<Integer> getB() {
		return b;
	}
	public void setB(LinkedList<Integer> b) {
		this.b = b;
	}
	public ArrayList<Integer> getC() {
		return c;
	}
	public void setC(ArrayList<Integer> c) {
		this.c = c;
	}
	
	public static void main (String[] argv)
	{
//		TestVector vector = new TestVector();
//		
//		Vector<Integer> a = new Vector<Integer>();
//		a.add(51);
//		a.add(52);
//		a.add(53);
//		a.add(541);
//		a.add(542);
//		
//		System.out.println(a.indexOf(53));
		//System.out.println(a.get(3));
		
		//System.out.println(a.elementAt(3));
		
		
//		Vector<Infer> a = new Vector<Infer>();
//		a.add(new Infer());
//		a.add(new Infer());
//		a.add(new Infer());
//		a.add(new Infer());
//		Infer b = new Infer();
//		b.setA(333);
//		
//		a.add(b);
//		
//		System.out.println(a.get(3).toString());
//		
//		System.out.println(a.elementAt(3).toString());
//		
//		Infer c = new Infer();
//		c.setA(333);
//		
//		System.out.println(a.indexOf(c));
		
		
//		Vector<String> a = new Vector<String>();
//		a.add("11");
//		a.add("22");
//		
//		String b = "3333";
//		a.add(b);
//		b = "44444";
//		a.add(b);
//		
//		a.add("33");
//		a.add("44");
//		a.add("55");
//		a.insertElementAt("7777", 2);
//		
//		System.out.println(a.toString());
//		System.out.println(a.indexOf("3333"));
//		System.out.println(a.indexOf("44444"));
		
		
		
		
//		Vector<StringBuffer> a = new Vector<StringBuffer>();
//		a.add(new StringBuffer("11"));
//		a.add(new StringBuffer("22"));
//		
//		StringBuffer b = new StringBuffer("33");
//		a.add(b);
//		b.setLength(0);
//		b.append("6");
//		a.add(b);
//		a.add(new StringBuffer("44"));
//		a.add(new StringBuffer("55"));
//		
//		System.out.println(b.toString());
//		System.out.println(a.toString());
//		System.out.println(a.indexOf(b));
//		System.out.println(a.get(2));
//		System.out.println(a.get(3));
		
		//List<String> a = Collections.synchronizedList(new ArrayList<String>());
		
//		List<String> a =new ArrayList<String>();
//		a.add("11");
//		a.add("22");
//		
//		String b = "3333";
//		a.add(b);
//		b = "44444";
//		a.add(b);
//		
//		a.add("33");
//		a.add("44");
//		a.add("55");
//		a.set(5, "7777888");
//		//a.add(1, "插进来的");
//		
//		
//		//System.Collections.ArrayList aa = new System.util.Collections.ArrayList<String>();
//		
//		System.out.println(a.toString());
//		System.out.println(a.indexOf("3333"));
//		System.out.println(a.indexOf("44444"));
		
		
//		List<String> a =new LinkedList<String>();
//		a.add("11");
//		a.add("22");
//		
//		String b = "3333";
//		a.add(b);
//		b = "44444";
//		a.add(b);
//		
//		a.add("33");
//		a.add("44");
//		a.add("55");
//		a.set(5, "7777888");
//		a.add(1, "插进来的");
//		
//		
//		//System.Collections.ArrayList aa = new System.util.Collections.ArrayList<String>();
//		
//		System.out.println(a.toString());
//		System.out.println(a.indexOf("3333"));
//		System.out.println(a.indexOf("44444"));
//		
//		Object[] aaa = (Object[])a.toArray();
//		System.out.println(aaa.toString());
//		Arrays.sort(aaa);
//		
//		System.out.println(aaa.toString());
		
		
		
		List<Integer> a =new LinkedList<Integer>();
		a.add(11);
		a.add(22);
		
		int b = 3333;
		a.add(b);
		b = 44444;
		a.add(b);
		
		a.add(33);
		a.add(44);
		a.add(55);
		a.set(5, 7777888);
		a.add(1, 314159);
		
		
		//System.Collections.ArrayList aa = new System.util.Collections.ArrayList<String>();
		
		System.out.println(a.toString());
		System.out.println(a.indexOf(3333));
		System.out.println(a.indexOf(44444));
		System.out.println(Collections.max(a));
		System.out.println(Collections.min(a));
		
		
		
		Collections.sort(a);
		Collections.reverse(a);
		System.out.println(a.toString());
		Collections.rotate(a, 3);
		System.out.println(a.toString());
		
		Collections.sort(a);
		System.out.println(a.toString());
		Collections.swap(a,  3, 5);
		System.out.println(a.toString());
		
		int ccccc = 0;
//		Object[] aaa = (Object[])a.toArray();
//		System.out.println(aaa.toString());
//		Arrays.sort(aaa);
//		
//		System.out.println(aaa.toString());
//		a = new ArrayList( Arrays.asList(aaa));
//		
//		System.out.println(aaa.toString());


	}

	

}
