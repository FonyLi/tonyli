package com.tonyli.recipefinder.dao.ds;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * This class is used to describe the fridge in which many different kinds of food materials are stored.
 * @author Tony Li
 * @since 19 Aug, 2014
 * @version 1.0
 */
public class Fridge  extends CommonObject{
	
	private static Logger logger = Logger.getLogger(Fridge.class);
	
	//store all items
	private List<Material> items = new ArrayList<Material>();

	public List<Material> getItems() {
		return items;
	}

	public void setItems(List<Material> items) {
		this.items = items;
	}

	/**
	 * clear all items in fridge
	 * @return
	 */
	public void reset()
	{
		if (items != null)
			items.clear();
	}
}
