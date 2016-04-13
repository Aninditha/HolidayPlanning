package com.ssdi.TestCases;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ssdi.util.TestConnection;

public class testLogIn {
	static boolean userExist = false;
	static Connection currentCon = null;
	
	public static void testLogin(String username, String password) {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		//connect to DB
  		currentCon = TestConnection.getConnection();
		String insertQuery = "insert into customerdetails values (\"ssdiproject\", \"project@ssdi.com\", \"qwerty\");";
		String selectQuery = "select * from CustomerDetails where Email = \"project@ssdi.com\" and password = \"qwerty\";";
		
		try {
	  		stmt = currentCon.createStatement();
	  		stmt.executeUpdate(insertQuery);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(selectQuery);
			rs.next();
			assertEquals(rs.getString("Email"), "project@ssdi.com");
			assertEquals(rs.getString("password"), "qwerty");
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
