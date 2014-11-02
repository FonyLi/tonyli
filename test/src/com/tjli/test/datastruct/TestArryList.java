package com.tjli.test.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class TestArryList {

	public static void main(String[] args) {
		ArrayList alist = new ArrayList();
//		alist.set(index, element)
//		alist.add(index, element);
		alist.add(1);
		alist.add("33");
		alist.add(Boolean.TRUE);
		
		System.out.println(alist.toString());
		
		Iterator it = alist.iterator();
		
		System.out.println(Collections.max(alist));
		
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		Integer[] aa = new Integer[5];
		int length = aa.length;
		ArrayList aalist = new ArrayList<Integer>();
		aalist.toArray(aa);
		aalist = (ArrayList)Arrays.asList(aa);
		
		//apathe��һ������еĺ���
		//int[] iobjs = ArrayUtils.toPrimitive(aa);

		
		String bb = new String();
		length = bb.length();
		
		
		
	}
}
