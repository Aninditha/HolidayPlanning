package com.ssdi.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.ssdi.POJO.taBean;

public class taModel {
	
	static Connection currentCon = null;
	
	public static ArrayList<taBean> searchRegions(String location) {
		ArrayList<taBean> taList = new ArrayList<taBean>();
		Statement stmt = null;
    	ResultSet rs = null;
    	
    	String regionName, description;
    	
    	String Query = "select * from country inner join region on "
    			+ "country.Country_ID = region.Country_ID where Country_name =\""+ location + "\";";
    	try {
			//connect to DB
	  		currentCon = DBConnection.getConnection();
	  		stmt = currentCon.createStatement();
	  		
			rs = stmt.executeQuery(Query);
			while(rs.next())
			{
				System.out.println(Query);
				taBean loc = new taBean();
				regionName = rs.getString("region_name");
				description = rs.getString("description");
				System.out.println(regionName + " " + description);
				System.out.println(regionName);
				loc.setRegionName(regionName);
				loc.setDescription(description);
				taList.add(loc);
			}
    	} catch (Exception e){
    		e.printStackTrace();
    	}
		return taList;
	}
	
	public static ArrayList<taBean> searchAttractions(String location) {
		ArrayList<taBean> taList = new ArrayList<taBean>();
		Statement stmt = null;
    	ResultSet rs = null;
    	
    	String attractionName, description;
    	
    	String Query = "select * from region inner join attractions on "
    			+ "region.region_ID = attractions.region_ID where region_name =\""+ location + "\";";
    	try {
			//connect to DB
	  		currentCon = DBConnection.getConnection();
	  		stmt = currentCon.createStatement();
	  		
			rs = stmt.executeQuery(Query);
			while(rs.next())
			{
				System.out.println(Query);
				taBean loc = new taBean();
				attractionName = rs.getString("attraction_name");
				description = rs.getString("attractions.description");
				System.out.println(attractionName + " "+ description);
				loc.setAttractionName(attractionName);
				loc.setDescription(description);
				taList.add(loc);
			}
    	} catch (Exception e){
    		e.printStackTrace();
    	}
		return taList;
	}
	
	public static boolean checkCountry(String country) {
		boolean exist = false;
		
		Statement stmt = null;
    	ResultSet rs = null;
    	int number = 0;
    	currentCon = DBConnection.getConnection();
		
		String Query = "select * from country where country_name = \""+ country +"\";";
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
	
	public static boolean checkRegion(String region) {
		boolean exist = false;
		
		Statement stmt = null;
    	ResultSet rs = null;
    	int number = 0;
    	currentCon = DBConnection.getConnection();
		
		String Query = "select * from region where region_name = \""+ region +"\";";
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
