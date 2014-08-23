package com.tonyli.recipefinder;

import junit.framework.TestCase;

import org.apache.log4j.PropertyConfigurator;

public class TestFridge extends TestCase{
	
	private static Fridge fridge = new Fridge();

	static
	{
		PropertyConfigurator.configure("config/log4j.properties");
	}
	
	public void testAdd()
	{
		String inputFileName = "fridge.csv";
		fridge.addItems(inputFileName);
	}
	
	
	public void testDump()
	{
		String outputFileName = "fridge_dump01.csv";
		fridge.dump(outputFileName);
		//then compare fridge.csv and fridge_dump01.csv, the content should be same
	}

}