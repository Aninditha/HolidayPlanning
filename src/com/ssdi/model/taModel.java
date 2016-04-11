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
}
