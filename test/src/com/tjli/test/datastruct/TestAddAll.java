package com.tjli.test.datastruct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TestAddAll {
//	public static void main(String[] args) {
//		List<String> l1 = new ArrayList<>();
////		l1.add("1");
////		l1.add("2");
//		Map<Integer, List<String>> map = new ConcurrentHashMap<>();
//		
//		map.put(1, l1);
//		
//		List<String> l2 = new ArrayList<>();
//		l2.clear();
//		//l2.add("3");
//		Iterator it = map.keySet().iterator();
//        
//        while(it.hasNext())
//        {
//        	Integer index = (Integer)it.next();
//        	l2.addAll(map.get(index));
//        	l2.add("4");
//        }
//		
//		System.out.println(map.get(1).size());
////		for(int i = 0; i < map.g.size(); i++)
////		{
////			System.out.println(l1.get(i));
////		}
//	}
	
	
	public void wordBreak(String s, Set<String> dict) {
        
        Map<Integer, List<String>> map = new ConcurrentHashMap<>();
        
        map.put(1, new ArrayList<String>());
        
        List<String> list = new ArrayList<>();
        
        Iterator it = map.keySet().iterator();
        
        while(it.hasNext())
        {
        	int index = (Integer)it.next();
        	list.clear();
        	list.addAll(map.get(index));
        
        	if(list.size() == 0)
        		list.add("a");
        }
        
        int a = map.get(1).size();
        
	}


	public static void main(String[] argv)
	{
		TestAddAll handler = new TestAddAll();
		String s = "aaaaaaa"; //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		
		Set<String> dict = new HashSet<>();
		String[] array = {"aaaa","aaa"};//{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		for(String one : array)
			dict.add(one);
		
		handler.wordBreak(s, dict);
		
	}
}
