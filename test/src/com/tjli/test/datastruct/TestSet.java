package com.tjli.test.datastruct;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.tjli.test.function.Infer;

public class TestSet {//extends Infer implements TestInterface, TestInterface2{

	public int hashCode()
	{
		return 1;
	}

	public boolean equals(Object other)
	{
//		if (other == null)
//			return false;
		
		try
		{
			if (! (other instanceof TestSet))
				return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	public static void main(String[] args) {
		
		List<String> ss = new LinkedList<String>();
		ss.add("1");
		ss.add("2");
		ss.add("3");
		ss.add("4");
		
		for (int i = 0; i < ss.size(); i++)
		{
			ss.remove(i);
		}
		
		for (int i = 0; i < ss.size(); i++)
		{
			System.out.println(ss.get(i));
		}
		
		TestSet TestSet1 = new TestSet();
		TestSet1.equals(null);
		Set<Infer> set = new HashSet<Infer>();
		
		
		Infer aa = new Infer("aa");
		set.add(aa);
		
		try
		{
			Thread.sleep(20);
		}
		catch(Exception e)
		{
			
		}
		
		set.add(aa);
		set.add(new Infer("bb"));
		set.add(new Infer("cc"));
		
		Iterator<Infer> it = set.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next().toString());
		}
		
		System.out.println(set.size());
		
		
//		set.add(null);
//		
//		set.add(1);
//		
//		set.contains(1);
//		
//		System.out.println(set.contains(1));
//		System.out.println(set.size());
//		
//		TestSet ts = new TestSet();
//		//System.out.println(ts.a);
//		
//		//接口的属性默认隐含为 public static final
//		//ts.a = "gg";
	}
}
