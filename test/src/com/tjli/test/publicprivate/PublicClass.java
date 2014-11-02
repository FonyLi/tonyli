package com.tjli.test.publicprivate;
/**
 * <p>Title: PublicClass.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2014<／p>
 * <p>Company: TJLI<／p>
 * @author Tony Li
 * @date 10/04/2014  12:52:32 PM
 * @version 1.0
 */
public class PublicClass {
	
	private String mp()
	{
		return "private method";
	}
	
	public static void hehe(String[] argv)
	{
		PublicClass pc = new PublicClass();
		pc.mp();
	}

}
