package com.tjli.test.datastruct;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {
	
	public static void main(String[] argv)
	{
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		
		map.put("a", "a");
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		
		System.out.println(map.toString());
		
		Set<Map.Entry<String,String>> set = map.entrySet();
		
		//Map.Entry<String,String> entry = new Map.Entry<String,String>("a", "a");
		
		System.out.println(set.toString());
		
		Iterator it = set.iterator();
		
		if(it.hasNext())
		{
			Object o = it.next();
			System.out.println(o.toString());
			
			System.out.println(((Map.Entry<String,String>)(o)).getKey());
			System.out.println(((Map.Entry<String,String>)(o)).getValue());
			set.remove(it.next());
		}
			
		
		System.out.println(map.toString());
		
		
//		HashMap map = new HashMap<Integer, String>();
//		
//		map.put(1, 4);
//		map.put(2, 5);
//		map.put(3, 6);
//		map.put(null, 55);
//		map.put(null, 77);
//		map.put(9, null);
//		System.out.println(map.containsKey(3));
//		System.out.println(map.containsKey(null));
//		System.out.println(map.containsKey(5));
//		System.out.println(map.containsValue(5));
//		System.out.println(map.containsValue(null));
//		
//		System.out.println(map.get(1));
//		System.out.println(map.get(2));
//		System.out.println(map.get(3));
//		System.out.println(map.get(null));
		
		
//		Hashtable table = new Hashtable<Integer, String>();
//		
//		table.put(1, 4);
//		table.put(2, 5);
//		table.put(3, 6);
//		//table.put(null, 55);
//		//table.put(null, 77);
//		//table.put(9, null);
//		System.out.println(table.containsKey(3));
//		//System.out.println(table.containsKey(null));
//		System.out.println(table.containsKey(5));
//		System.out.println(table.containsValue(5));
//		//System.out.println(table.containsValue(null));
//		
//		System.out.println(table.get(1));
//		System.out.println(table.get(2));
//		System.out.println(table.get(3));
//		//System.out.println(table.get(null));
//		
//		Set set = table.keySet();
//		
//		int aa = 333;
//		aa = 11 ^ 33;
//		
//		System.out.println(aa);
//		
//		ConcurrentHashMap map = new ConcurrentHashMap();
//		
//		Map map2 = new HashMap();
//		
//		
//		
//		Object object = new Object();
//		
//		try
//		{
//			
//			Map map1 = Collections.synchronizedMap(map2);
//			
//			synchronized(object)
//			{
//				object.wait();
//				map.put(1, 4);
//				
//				object.notify();
//			}
//			
//		}
//		catch(Exception e)
//		{
//			
//		}
			
	}

}
