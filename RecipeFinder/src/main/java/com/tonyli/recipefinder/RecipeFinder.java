package com.tonyli.recipefinder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * This class is  main class of the whole project.
 * It handles the process of reading csv file and Json request
 * @author Tony Li
 * @since 19 Aug, 2014
 * @version 1.0
 */
public class RecipeFinder{

	private static Logger logger = Logger.getLogger(RecipeFinder.class);

	public static final String ORDER_TAKEOUT = "Order Takeout";
	
	private static final String DEFAULT_DATA_FOLDER = "data/";
	
	private Fridge fridge = new Fridge();
	
	private static Calendar futureCalendar;
	private static Calendar unvailableCalendar;
	
	static 
	{
		//load log4j config
		PropertyConfigurator.configure("config/log4j.properties");
		
		futureCalendar = new GregorianCalendar();
		futureCalendar.set(3000, 1, 1); //initialise it in the future
		
		unvailableCalendar = new GregorianCalendar();
		unvailableCalendar.set(2000, 1, 1); //set it in the past
	}
	
	public static final Date INIT_USEBY = futureCalendar.getTime();
	public static final Date UNAVAILABLE_USEBY = unvailableCalendar.getTime();
	
	public RecipeFinder() {}
	
	public String getRecipe(String fridgeFileName, String recipeFileName)
	{
		
		fridgeFileName = DEFAULT_DATA_FOLDER + fridgeFileName;
		recipeFileName = DEFAULT_DATA_FOLDER + recipeFileName;
		
		logger.info("request is " + fridgeFileName + ", " + recipeFileName);
		
		StringBuffer sb = new StringBuffer();
		BufferedReader input = null;
		
		String recipe;
		try
		{
			input = new BufferedReader(new InputStreamReader(new FileInputStream(recipeFileName)));
			
			//get the whole json string
			String line;
			while((line = input.readLine()) != null)
			{
				sb.append(line);
			}
			
			recipe = getJsonRecipe(fridgeFileName, sb.toString());
		}
		catch (Exception e)
		{
			logger.error("", e);
			recipe = ORDER_TAKEOUT;
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
					logger.error("", e);
				}
			}
		}
		
		logger.info("the result is " + recipe);
		return recipe;
	}
	
	/**
	 * find the recipe 
	 * @param fridgeFileName the food in fridge
	 * @param recipeJson the recipe list
	 * @return
	 */
	public String getJsonRecipe(String fridgeFileName, String recipeJson)
	{
		fridge.reset();
		
		if (!addFoodIntoFridge(fridgeFileName))
			return ORDER_TAKEOUT;
		
		return getRecipe(recipeJson);
	}
	
	private boolean addFoodIntoFridge(String fileName)
	{		
		return fridge.addItems(fileName);
	}
	
	private String getRecipe(String options)
	{
		try
		{
			List<Recipe> recipes = new ArrayList<Recipe>();
			
			GsonBuilder gsonBuilder = new GsonBuilder();  
	        gsonBuilder.registerTypeAdapter(Unit.class, new EnumSerialiser());  
	        Gson gson = gsonBuilder.create();  
			JsonParser parser = new JsonParser();
			JsonArray jsonArray = parser.parse(options).getAsJsonArray();
			
			for (int i = 0; i < jsonArray.size(); i++)
			{
				JsonElement element = jsonArray.get(i);
				recipes.add(gson.fromJson(element, Recipe.class));
			}
			
			if (recipes.size() == 0)
				return ORDER_TAKEOUT;
			
			Recipe recipe = null;
			Recipe closestRecipe = null;
			
			//get the Recipe with closest closestUseBy
			for (int i = 0; i < recipes.size(); i++)
			{
				recipe = recipes.get(i);
				calcClosestUseBy(recipe);
				
				if (!recipe.getClosestUseBy().equals(INIT_USEBY) &&
						!recipe.getClosestUseBy().equals(UNAVAILABLE_USEBY))
				{
					//select the most closest one
					if (closestRecipe == null || recipe.getClosestUseBy().before(closestRecipe.getClosestUseBy()))
					{
						closestRecipe = recipe;
					}
					else
					{
						//this recipe is not the most closest one.
					}
				}
			}
			
			if(closestRecipe == null ||
					closestRecipe.getClosestUseBy().equals(INIT_USEBY) ||
					closestRecipe.getClosestUseBy().equals(UNAVAILABLE_USEBY))
			{
				return ORDER_TAKEOUT;
			}
			else
			{
				return closestRecipe.getName();
			}
		}
		catch(Exception e)
		{
			logger.error(options.trim() + " is in incorrect format.");
			return ORDER_TAKEOUT;
		}
	}
	
	private void calcClosestUseBy(Recipe recipe)
	{
		if (recipe == null || recipe.getIngredients() == null)
			return;
		
		Material ingredient = null;
		Material item = null;
		Map<String, Material> items = fridge.getItems();
		
		for (int i = 0; i < recipe.getIngredients().size(); i++)
		{
			ingredient = recipe.getIngredients().get(i);
			
			if (!items.containsKey(ingredient.getItem())) //there is no this ingredient in fridge					
			{
				recipe.setClosestUseBy(UNAVAILABLE_USEBY);
				return;
			}
			
			item = items.get(ingredient.getItem());
			if(item.getAmount() < ingredient.getAmount() //there is not enough ingredient in fridge
					|| !item.getUnit().equals(ingredient.getUnit())) //the units are not same.
			{
				recipe.setClosestUseBy(UNAVAILABLE_USEBY);
				return;
			}
			
			//check the closet useby and set it to recipe's closest
			if(item.getUseBy().before(recipe.getClosestUseBy()))
			{
				recipe.setClosestUseBy(item.getUseBy());
			}
		}
	}
}
