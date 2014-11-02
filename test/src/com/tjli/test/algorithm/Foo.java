package com.tjli.test.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Foo
{
    public static synchronized void bar()
    {
    	System.out.println("hehe");
        //do something
    	
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	
    	Set<Integer> set = map.keySet();
    	
    	List<Integer> list = new ArrayList<Integer>();

    }
}
