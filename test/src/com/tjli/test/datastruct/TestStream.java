package com.tjli.test.datastruct;

import java.io.InputStream;
import java.util.Properties;

public class TestStream {
	
	private final static String configFile = "/config/ShootPlaneServer.properties";
	
	private static Properties properties = new Properties();
	
	private static String SERVER_PORT_KEY = "port";
	private static int serverPort;
	
	private static String SERVER_IP_KEY = "ip";
	private static String serverIp;

	public static void main(String[] args) {
		new TestStream();
	}

	static
	{
		InputStream is = null;
		
		try
		{
			is = TestStream.class.getResourceAsStream(configFile);
			
			if (is != null)
			{
				properties.load(is);
				
				//print then when server starts.
				properties.list(System.out);
				
				for (Object key : properties.keySet())
				{
					String stringKey = (String)key;
					if (stringKey.equalsIgnoreCase(SERVER_IP_KEY))
					{
						serverIp = properties.getProperty(SERVER_IP_KEY);
					}					
					else if (stringKey.equalsIgnoreCase(SERVER_PORT_KEY))
					{
						serverPort = Integer.valueOf(properties.getProperty(SERVER_PORT_KEY));
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}