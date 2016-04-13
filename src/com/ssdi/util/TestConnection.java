package com.ssdi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
	static Connection con;
	static String url;
         
	public static Connection getConnection() {
		try {
			// create a mysql database connection
			String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/ssdi_test?autoReconnect=true&useSSL=false";
			Class.forName(myDriver);
			con = DriverManager.getConnection(myUrl,"root","root");
		}
		catch(ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
