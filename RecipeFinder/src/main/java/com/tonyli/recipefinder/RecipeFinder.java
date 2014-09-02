package com.tonyli.recipefinder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tonyli.recipefinder.dao.FridgeDAO;
import com.tonyli.recipefinder.dao.ds.Fridge;
import com.tonyli.recipefinder.dao.ds.Material;
import com.tonyli.recipefinder.dao.ds.Recipe;
import com.tonyli.recipefinder.dao.ds.Unit;
import com.tonyli.recipefinder.server.ConsoleServer;

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
	
	public static final Date INIT_USEBY = new java.sql.Date(futureCalendar.getTime().getTime());
	public static final Date UNAVAILABLE_USEBY = new java.sql.Date(unvailableCalendar.getTime().getTime());
	
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
		BufferedReader input = null;
		
		Session session = ConsoleServer.getSessionFactory().openSession();
		
		if (session == null)
			return false;
		try
		{
			input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			
			String oneItem = null;
			
			FridgeDAO fridgeDAO = new FridgeDAO(session);
			while((oneItem = input.readLine()) != null)
			{
				fridgeDAO.addItem(new Material(oneItem));
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
			
			if (session != null)
				session.close();
		}
		
		return true;
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
		List<Material> items = null;
		
		Session session = ConsoleServer.getSessionFactory().openSession();
		
		FridgeDAO fridgeDAO = new FridgeDAO(session);
		
		long current = System.currentTimeMillis();
		
		for (int i = 0; i < recipe.getIngredients().size(); i++)
		{
			ingredient = recipe.getIngredients().get(i);
			
			items = fridgeDAO.getItemWithSameNameAndUnit(ingredient.getItem(), ingredient.getUnit());
			
			if (items == null || items.size() == 0)//there isn't this ingredient in fridge	
			{
				recipe.setClosestUseBy(UNAVAILABLE_USEBY);
				return;
			}
			
			int totalAmount = 0;
			Date closestUseBy = INIT_USEBY;
			for (Material material : items)
			{
				if (material.getUseBy().getTime() < current)
				{
					//it is a expired one
					continue;
				}
				
				if (material.getUseBy().before(closestUseBy))
				{
					closestUseBy = material.getUseBy();
				}
				
				totalAmount += material.getAmount();
			}
			
			if(totalAmount < ingredient.getAmount()) //there is not enough ingredient in fridge
			{
				recipe.setClosestUseBy(UNAVAILABLE_USEBY);
				return;
			}
			
			//set closet useby to recipe's closest
			recipe.setClosestUseBy(closestUseBy);
		}
	}
}
