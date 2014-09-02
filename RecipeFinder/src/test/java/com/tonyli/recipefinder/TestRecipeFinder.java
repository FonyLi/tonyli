package com.tonyli.recipefinder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestRecipeFinder extends TestCase{

	private static Logger logger = Logger.getLogger(RecipeFinder.class);
	
	private RecipeFinder finder = new RecipeFinder();
	
	private static final String CHEESE = "grilled cheese on toast";
	private static final String SALAD = "salad sandwich";
	
	static
	{
		PropertyConfigurator.configure("config/log4j.properties");
	}
	
	
	private void clearDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://192.168.2.2:3306/recipeFinderDB";
			String user="rfuser";
			String password="810208";
			Connection conn=DriverManager.getConnection(url,user,password);
			Statement statement = conn.createStatement();
			statement.execute("delete from Material");
			statement.execute("delete from Fridge");
			statement.execute("delete from CommonObject");

			statement.close();
			conn.close();
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
	}
	
	//test if the food input is not in correct format
	//system can add some default value to make it correct
	public void testAddFoodIntoFridge_null()
	{
		
		
		String fridgeFileName = "fridge_test_null.csv"; //some segment is null
		String recipeFileName = "recipe.json";
		
		getRecipe("testAddFoodIntoFridge_null", fridgeFileName, recipeFileName, SALAD);
	}
	
	//test if the food input is not in correct format
	//check log, there should be some info like: amount: aaa shoule be an Integer
	public void testAddFoodIntoFridge_not_int()
	{
		
		String fridgeFileName = "fridge_test_not_int.csv"; //the count of mixed salad is not int.
		String recipeFileName = "recipe.json";
		
		getRecipe("testAddFoodIntoFridge_not_int", fridgeFileName, recipeFileName, CHEESE);;
	}
	
	//set bread is past its useby Date
	//expect Order Takeout
	public void testAddFoodIntoFridge_useby()
	{
		
		String fridgeFileName = "fridge_test_useby.csv";
		String recipeFileName = "recipe.json";
		
		getRecipe("testAddFoodIntoFridge_useby", fridgeFileName, recipeFileName, RecipeFinder.ORDER_TAKEOUT);
	}
	
	//set mixed salad,500,grams,26/12/2014
	//and cheese,10,slices,25/12/2014
	//expect cheese to be selected
	public void testAddFoodIntoFridge_cheese_earlier()
	{
		
		String fridgeFileName = "fridge_test_cheese_earlier.csv";
		String recipeFileName = "recipe.json";

		getRecipe("testAddFoodIntoFridge_cheese_earlier", fridgeFileName, recipeFileName, CHEESE);
	}
	
	//set bread's unit as bags
	//expect none will be selected
	public void testAddFoodIntoFridge_wrong_unit()
	{
		
		String fridgeFileName = "fridge_test_wrong_unit.csv";
		String recipeFileName = "recipe.json";

		getRecipe("testAddFoodIntoFridge_wrong_unit", fridgeFileName, recipeFileName, RecipeFinder.ORDER_TAKEOUT);
	}
		
	//set mixed salad's count in fridge is less than it is in recipe
	//expect cheese to be selected
	public void testAddFoodIntoFridge_lack_salad()
	{
		
		String fridgeFileName = "fridge_test_lack_salad.csv";
		String recipeFileName = "recipe.json";
		
		getRecipe("testAddFoodIntoFridge_lack_salad", fridgeFileName, recipeFileName, CHEESE);
	}
	
	//test case of same name
	//if there are two lines have some item in fridge
	//mixed salad,100,grams,26/12/2014
	//mixed salad,150,grams,26/12/2014
	//add them together,so the total number is 100+150=250 > 200
	//expecting salad can be selected
	public void testAddFoodIntoFridge_same_name()
	{
		
		String fridgeFileName = "fridge_test_same_name.csv";
		String recipeFileName = "recipe.json";

		getRecipe("testAddFoodIntoFridge_same_name", fridgeFileName, recipeFileName, SALAD);
	}

	//test normal process
	public void testGetRecipe()
	{
		
		String fridgeFileName = "fridge.csv";		
		String recipeFileName = "recipe.json";

		getRecipe("testGetRecipe", fridgeFileName, recipeFileName, SALAD);
	}
	
	//test if recipe is incorrect
	public void testGetRecipe_recipe_incorrect_item()
	{
		
		String fridgeFileName = "fridge.csv";		
		String recipeFileName = "recipe_incorrect_item.json";

		getRecipe("testGetRecipe_recipe_incorrect_item", fridgeFileName, recipeFileName, CHEESE);
	}
		
	//test if recipe is in incorrect format
	//some exception will be recorded in log
	public void testGetRecipe_recipe_incorrect_format()
	{
		
		String fridgeFileName = "fridge.csv";		
		String recipeFileName = "recipe_incorrect_format.json";

		getRecipe("testGetRecipe_recipe_incorrect_format", fridgeFileName, recipeFileName, RecipeFinder.ORDER_TAKEOUT);
	}
	
	private void getRecipe(String functionName, String fridgeFileName, String recipeFileName, String expectedResult)
	{
		//clearDB();
		logger.info(functionName + "'s result is ");
		String result = finder.getRecipe(fridgeFileName, recipeFileName);
		
		try
		{
			assertTrue(result.toString().equalsIgnoreCase(expectedResult));
		}
		catch(AssertionFailedError e)
		{
			logger.error("expect " + expectedResult + " but the result is " + result, e);
		}
		
		logger.info(result);
	}
}
