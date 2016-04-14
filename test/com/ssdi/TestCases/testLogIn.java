package com.ssdi.TestCases;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.ssdi.util.TestConnection;

public class testLogIn {
	boolean userExist = false;
	 Connection currentCon = null;
	@Test
	public  void testLogin() {
		
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
			String em = rs.getString("Email");
			assertEquals(rs.getString("password"), "qwerty");
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
