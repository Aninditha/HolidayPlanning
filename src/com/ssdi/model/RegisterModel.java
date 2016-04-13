package com.ssdi.model;

import java.sql.*;
import com.ssdi.POJO.userbean;

public class RegisterModel {
	
	static Connection currentCon = null;
	
	public static userbean login(userbean bean) {
		
		PreparedStatement preparedStmt = null;
		
	  	String un = bean.getUsername();
	    String pwd = bean.getPassword();
	    String em = bean.getEmail();
	    	  
	    String Query = "insert into CustomerDetails (Name, Email, password)"
        + " values (?, ?, ?)";
	  	      
	  	System.out.println("Your user name is " + un);
	  	System.out.println("Your password is " + pwd);
	  	System.out.println("Your email is " + em);
	  	System.out.println("Query: "+Query);
	  	
	  	try{
	  		//connect to DB
	  		currentCon = DBConnection.getConnection();
	  	
	  		// create the mysql insert preparedstatement
	  		preparedStmt = currentCon.prepareStatement(Query);
	  		preparedStmt.setString (1, un);
	  		preparedStmt.setString (2, em);
	  		preparedStmt.setString (3, pwd);
	 
	  		//execute the preparedstatement
	  		preparedStmt.execute();
	      
	  		currentCon.close();
	  		// if user does not exist set the isValid variable to false
	  	}
	  	catch (Exception ex) {
	  		System.out.println("Log In failed: An Exception has occurred! " + ex);
	  	}
	  	return bean;
	}

	public static boolean check(String email) {
		boolean exist = false;
		
		Statement stmt = null;
    	ResultSet rs = null;
    	int number = 0;
    	currentCon = DBConnection.getConnection();
		
		String Query = "select * from CustomerDetails where Email = \""+ email +"\";";
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
				exist = true;
			
		} catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(exist);
		return exist;
	}
}