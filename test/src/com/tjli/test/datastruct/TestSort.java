package com.tjli.test.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestSort {

	public static void print(Integer[] ints)
	{
		for(int i = 0; i < ints.length; i++)
		{
			System.out.print(ints[i]);
			
			if(i != ints.length - 1)
				System.out.print(",");
		}
	}
	
	public static void main(String[] args) {
		if (args == null || args.length == 0)
			return;
		
		String numbers = args[0];
		
		String[] numberArray = numbers.split(",");
		
		if (numberArray == null || numberArray.length == 0)
			return;
		
		int count = numberArray.length;
		Integer[] ints = new Integer[count];
		
		for (int i = 0; i < count; i++)
		{
			String number = numberArray[i];
			
			try
			{
				ints[i] = Integer.valueOf(number);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		print(ints);
		System.out.println();
		Arrays.sort(ints);
		
		print(ints);
		System.out.println();
		List intList = Arrays.asList(ints);
		Collections.reverse(intList);
		
		intList.toArray(ints);
		
		print(ints);
		System.out.println();
		
		System.out.println(Math.pow(3, 5));
		System.out.println(Math.pow(5, 3));
		
	}
}
