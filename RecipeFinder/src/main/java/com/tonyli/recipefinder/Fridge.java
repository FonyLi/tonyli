package com.tonyli.recipefinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * This class is used to describe the fridge in which many different kinds of food materials are stored.
 * @author Tony Li
 * @since 19 Aug, 2014
 * @version 1.0
 */
public class Fridge {
	
	private static Logger logger = Logger.getLogger(Fridge.class);
	
	//store all items
	private Map<String, Material> items = new HashMap<String, Material>();

	/**
	 * clear all items in fridge
	 * @return
	 */
	public void reset()
	{
		if (items != null)
			items.clear();
	}
	
	/**
	 * read items from a csv file, and add items into the existing ones
	 * @return
	 */
	public boolean addItems(String fileName)
	{
		BufferedReader input = null;
		try
		{
			input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			
			String oneItem = null;
			
			while((oneItem = input.readLine()) != null)
			{
				addItem(new Material(oneItem));
			}
		}
		catch (Exception e)
		{
			logger.error("add items exception", e);
			return false;
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch(Exception closeE)
				{
					logger.error("failed to close file", closeE);
				}
			}
		}
		
		return true;
	}
	
	/**
	 * add one item into storage.
	 * if the unit or useby is not as before, just ignore this item.
	 * @param item
	 */
	private void addItem(Material item)
	{		
		if (item == null)
			return;

		String itemName = item.getItem();
		
		//if the item initialised unsuccessfully
		if (itemName == null)
		{
			//do not need to record into log, as we've recorded it in the constructor of Material
			return;
		}
		
		//An ingredient that is past its use-by date cannot be used for cooking
		if (item.getUseBy().getTime() < System.currentTimeMillis())
		{
			logger.info(itemName + " is past its useby date, do not put it into fridge");
			return;
		}
		
		if (items.containsKey(itemName))
		{
			Material existingItem = items.get(itemName);
			
			//if the unit is not same
			if (!existingItem.getUnit().equals(item.getUnit()))
			{
				logger.error(itemName + "'s existing unit: " + existingItem.getUnit()
							+ " is not same as input one: " + item.getUnit() +  ". Ignore this input.");
				return;
			}
			
			//if the useby is not same
			if (!existingItem.getUseBy().equals(item.getUseBy()))
			{
				logger.error(itemName + "'s existing useby: " + existingItem.getUseBy()
							+ " is not same as input one: " + item.getUseBy() +  ". Ignore this input.");
				return;
			}
			
			existingItem.setAmount(existingItem.getAmount() + item.getAmount());
		}
		else
		{
			items.put(itemName, item);
		}
	}

	/**
	 * dump existing items list into a csv file
	 * @param outputFile
	 */
	public void dump(String outputFile)
	{
		BufferedWriter output = null;
		
		try
		{
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
			
			if (items != null)
			{
				Set<Entry<String, Material>> entry = items.entrySet();
				
				Iterator<Entry<String, Material>> it = entry.iterator();
				
				while(it.hasNext())
				{
					output.write(it.next().getValue().toString() + "\n");
				}
			}
		}
		catch (Exception e)
		{
			logger.error("dump items exception", e);
			return;
		}
		finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				}
				catch(Exception closeE)
				{
					logger.error("failed to close file", closeE);
				}
			}
		}
	}

	public Map<String, Material> getItems() {
		return items;
	}
	
}
