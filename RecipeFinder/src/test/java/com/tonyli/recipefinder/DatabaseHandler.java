package com.tonyli.recipefinder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
	
	public static void main(String args[]) throws SQLException,Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://192.168.2.2:3306/recipeFinderDB";
		String user="rfuser";
		String password="810208";
		Connection conn=DriverManager.getConnection(url,user,password);
		Statement statement = conn.createStatement();
		statement.execute("drop table Material");
		statement.execute("drop table Fridge");
		statement.execute("drop table CommonObject");
		ResultSet rs = statement.executeQuery("select * from student");

		while (rs.next()) {
		System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
		+ rs.getObject(3) + "\t" + rs.getObject(4));
		}
		rs.close();
		statement.close();
		conn.close();

		}

}
