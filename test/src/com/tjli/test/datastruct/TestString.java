package com.tjli.test.datastruct;

import java.util.ArrayList;
import java.util.List;

public class TestString {

//	public static void main(String[] argv) {
//		
//		String ABCD = "ABCD";
//		String abcd = ABCD.toLowerCase();
//		
//		String cccc;
//		abcd = abcd.replace('a', 'd');
//		abcd = abcd.replace('b', 'c');
//		System.out.println(abcd);
//		
//		String ss = abcd.intern();
//
//		
//		//��Ϊ����new�����Դ��ڶ��У�����a==b ����false�ˣ�һ���ڶ���һ����ջ��
////		String a = new String("tjli");
////		String b = "tjli";
////		System.out.println(a.equals(b));
////		System.out.println(a==b);
//
//		//��������ջ��
//		String a = "tjli";
//		String b = "tjli";
//		System.out.println(a.equals(b));
//		System.out.println(a==b);
//		
//		char char3 = a.charAt(2);
//		
//		Integer i = 100;
//		String si = String.valueOf(i);
//		System.out.println(si);
//		
//		System.out.println(Double.MAX_VALUE);
//		System.out.println(Double.MIN_VALUE);
//		
//		double f = 33314.159;	
//		System.out.println((int)(f / 100) * 100);
//		
//		System.out.println((double)(33314.159-(double)33300));
//		double f100 = (double)(33314.159 - (double)((int)(f / 100) * 100)); // (double)(f - (double)((int)(f / 100) * 100));		
//		double f1000 = (double)(f - (int)(f / 1000) * 1000);		
//		f = (double)(f + f100 - f1000);		
//		System.out.println(new DecimalFormat("#.000").format(f));
//		
//		
//		f = 33314.159;
//		
//
//		int beforeDot = (int)f;
//		
//		beforeDot = beforeDot - beforeDot / 100 * 100;
//		
//		f -= beforeDot;
//		System.out.println(f);	
////		double afterDot = f - beforeDot;
////	
////		f = (double)(f - f1 + f100);		
//		
//		
//		
//	}
	
	public static int testReturn()
	{
		int a = 3;
		try {
	         return a;
	       } finally {
	    	   a = 4;
	         //return a;
	       }
	}
	
	public static int testReturn2()
	{
		int a = 3;
		try {
	         throw new Exception();
	       }
		catch (Exception e)
		{
			return a;
		}
		finally 
		{
			a = 4;
	         //return a;
		}
	}
	public static void main(String[] args) {
		
		System.out.println(testReturn());
		
		System.out.println(testReturn2());
		
		String fds = "fewafsdffssf";
		fds.matches(".*[0-9].*");
		
		
		List<String> sss = new ArrayList<String>();
		
		if(sss instanceof ArrayList<?>)
		{
			int aaaaa = 0;
		}
		String a = "foo"; 
		String b = "food"; 
		
		String c = b.substring(0,3);
		
		c = c.intern();
		
		System.out.println( a == c );
		
		Integer i = null;
		int j = i; //java.lang.NullPointerException
		System.out.println(j);
		
//		String s5=new String("kvill");
//		String s1=s5; 
//		
//		//String s1="kvill"; 
//		String s2=s1.intern(); 
//		System.out.println( s1==s1.intern() ); 
//		System.out.println( s1+" "+s2 ); 
//		System.out.println( s2==s1.intern() ); 
		
//		String str1 = "fly";
//        String str2 = "weight";
//        String str3 = "flyweight";
//        String str4 = null;
//        str4 = str1 + str2;
//        System.out.println(str3 == str4);
//        str4 = (str1 + str2).intern();
//        System.out.println(str3 == str4);
	}
}
