package com.tonyli.recipefinder.dao.ds;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import com.tonyli.recipefinder.RecipeFinder;

/**
 * This class is used to describe the kind of items stored in fridge.
 * @author Tony Li
 * @since 19 Aug, 2014
 * @version 1.0
 */
public class Material extends CommonObject{

	private static Logger logger = Logger.getLogger(Material.class);
	
	private static final String SEPARATOR = ",";
	private static final String defaultDatePattern = "dd/MM/yyyy";
	private static final SimpleDateFormat defaultDateFormater = new SimpleDateFormat(defaultDatePattern);
	private static final int UNAVAILABLE_AMOUNT = -1;
	
	private String item;
	private int amount;
	
	@Enumerated(EnumType.STRING)
	private Unit unit;
	
	@Temporal(TemporalType.DATE)
	@Column(name="useBy")
	private Date useBy = RecipeFinder.INIT_USEBY;
	
	public Material()
	{}
	
	public Material(String oneItem)
	{
		String[] itemStrings = oneItem.split(SEPARATOR);
		
		if (itemStrings == null || (itemStrings.length != 4 && itemStrings.length != 3)) //it is not in correct format
		{
			logger.error(oneItem + " is not in corret format.");
			return;
		}
		
		if (itemStrings.length == 3) //for recipe, it does not include date
			init(itemStrings[0], itemStrings[1], itemStrings[2]);
		else   //for fridge
			init(itemStrings[0], itemStrings[1], itemStrings[2], itemStrings[3]);
	}
	
	private int isAvailable(String item, String amount)
	{
		//check null
		if (item == null || amount == null)
		{
			logger.error("any part of input: " + item + ", " + amount + " shoule NOT be null"); //notice that unit can be null
			return UNAVAILABLE_AMOUNT;
		}
		
		//check if the amount is an int
		int intAmount = 0;
		try 
		{
			intAmount = Integer.parseInt(amount);
		} 
		catch (NumberFormatException e)
		{			
			logger.error("amount: " + amount + " shoule be an Integer");
			return UNAVAILABLE_AMOUNT;
		}
		
		return intAmount;
	}
	
	private void init(String item, String amount, String unit)
	{
		
		int intAmount = isAvailable(item, amount);
		if (intAmount == UNAVAILABLE_AMOUNT)
			return;
		
		//convert unit from string to enum
		Unit enumUnit = string2unit(unit);
		
		init(item, intAmount, enumUnit, null);
	}
	
	private void init(String item, String amount, String unit, String useBy)
	{
		int intAmount = isAvailable(item, amount);
		if (intAmount == UNAVAILABLE_AMOUNT)
			return;
		
		//convert unit from string to enum
		Unit enumUnit = string2unit(unit);
		
		Date dateUseBy = null;
		try
		{
			dateUseBy = new java.sql.Date(defaultDateFormater.parse(useBy).getTime());
		}
		catch (ParseException e)
		{
			logger.error("date: " + useBy + " is not in format: " + defaultDatePattern);
			return;
		}
		
		init(item, intAmount, enumUnit, dateUseBy);
	}
	
	private void init(String item, int amount, Unit unit, Date useBy)
	{
		this.item = item;
		
		this.amount = amount;
		
		if (unit != null)
			this.unit = unit;
		
		if (useBy != null)
			this.useBy = useBy;
	}
	
	/**
	 * convert unit from string to enum
	 * @param unit the input string
	 * @return the UNIT
	 */
	private Unit string2unit(String unit)
	{
		Unit enumUnit = null;
		
		if (unit == null || unit.length() == 0)
		{
			enumUnit = Unit.of;
		}
		else
		{
			String lowerCaseUnit = unit.toLowerCase();
			try
			{
				enumUnit = Unit.valueOf(lowerCaseUnit);
			}
			catch(Exception e)
			{
				enumUnit = Unit.of;
			}
		}
		
		return enumUnit;
	}
	
	/**
	 * convert unit from unit to string
	 * @param unit the unit
	 * @return the string
	 */
	private String unit2String()
	{
		if (unit == null)
			return null;
		
		return unit.toString();
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Enumerated(EnumType.STRING)
	public Unit getUnit() {
		return unit;
	}	
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public Date getUseBy() {
		return useBy;
	}
	public void setUseBy(Date useBy) {
		this.useBy = useBy;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(item).append(SEPARATOR)
		.append(amount).append(SEPARATOR)
		.append(unit2String()).append(SEPARATOR)
		.append(defaultDateFormater.format(useBy)); //do not need to check null, useBy won't be null.
		
		return sb.toString();
	}

}
