package com.ssdi.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginModel {
	
	static boolean userExist = false;
	static Connection currentCon = null;
	
	public static boolean search(String username, String password) {
		
		Statement stmt = null;
    	ResultSet rs = null;
    	int number = 0;
    	currentCon = DBConnection.getConnection();
		
		String Query = "select * from CustomerDetails where Email = \""+ username +"\" and "
				+ "password = \""+ password +"\";";
		System.out.println(Query);
		try {
			//connect to DB
	  		currentCon = DBConnection.getConnection();
	  		
	  		stmt = currentCon.createStatement();
	  		
			rs = stmt.executeQuery(Query);
			while(rs.next()){
				if (rs.last())
					number = rs.getRow();
			}
			if(number == 1)
				userExist = true;
			
		} catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(userExist);
		return userExist;
	}
}
