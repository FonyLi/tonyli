package com.tonyli.recipefinder.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.tonyli.recipefinder.RecipeFinder;

/* 
 *	run it as a thread, so user can try several times if they want.
 */
public class JsonServer extends Thread{
	
	private static Logger logger = Logger.getLogger(JsonServer.class);
	
	private static final String FINISH_SIGN = "finish";
	
	RecipeFinder recipeFinder;
	
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public JsonServer()
	{
		recipeFinder = new RecipeFinder();
		
		Configuration conf = new AnnotationConfiguration();
		//conf.configure("../../../config/hibernate.cfg.xml"); //TODO: why doesn't it work?
		conf.configure();
		sessionFactory = conf.buildSessionFactory();
	}
	
	public void run()
	{
		logger.info("server starts.");
		
		System.out.println("This is RecipeFinderServer");
		System.out.println("you can find recipe as many times as you want");
		System.out.println("csv and json data files are in data folder");
		System.out.println("if you want to finish this app, just type \" " + FINISH_SIGN + " \". \n\n");
		
		BufferedReader input = null;
		
		try
		{
			while(true)
			{
				System.out.println("");
				System.out.println("what do you want to cook tonight? Let's see what we have");
				
				try
				{
					input = new BufferedReader(new InputStreamReader(System.in));
					
					System.out.println("please input fridge csv file name(e.g fridge.csv), or type finish.");
					String fridge = input.readLine();
					
					if (fridge.equalsIgnoreCase(FINISH_SIGN))
						break;
					
					System.out.println("please input recipe json file name(e.g recipe.json)");
					String recipe = input.readLine();
					
					String dinner = recipeFinder.getRecipe(fridge, recipe);
					
					if (!dinner.equalsIgnoreCase(RecipeFinder.ORDER_TAKEOUT))
					{
						System.out.println("You dinner will be " + dinner);
					}
					else
					{
						System.out.println("No recipe found for your current material. Let's " + dinner);
					}
				}
				catch(Exception e)
				{
					logger.error("", e);
				}
			}
			
			System.out.println("enjoy it, bye");
		}
		catch (Exception e)
		{
			logger.error("thread exception", e);
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
					logger.error("failed to close BufferedReader", closeE);
				}
			}
		}
		
		logger.info("server ends.");
	}
	
	public static void main(String[] argv)
	{
		JsonServer server = new JsonServer();
		server.start();
	}

}
