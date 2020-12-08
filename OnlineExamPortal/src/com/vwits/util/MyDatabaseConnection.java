package com.vwits.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyDatabaseConnection {
	private static Connection con;

	public static Connection connectToDatabase() {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "divyank","da2308");
			if (con != null) {
				System.out.println("Successfully connected to database");
				return con;
			} else {
				System.out.println("Error connecting to databasee");
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}

}
