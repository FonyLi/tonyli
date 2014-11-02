package com.tjli.test.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestControl {
	public static void main(String[] args) {
		
		try
		{
			while(true)
			{
				BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
				
				String sl = line.readLine();
				
				if (sl.contains("END"))
				{
					return;
				}
			}
		}
		catch(Exception e)
		{
			
		}
		
	}

}
