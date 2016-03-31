package com.ssdi.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.ssdi.POJO.taBean;

public class taModel {
	
	static Connection currentCon = null;
    static ArrayList<taBean> taList = new ArrayList<taBean>();
	static String locationType, locationID;
	
	public static ArrayList<taBean> search(String location) {
		
		Statement stmt = null;
    	ResultSet rs = null;
    	
    	String regionName, rDescription;
    	String attractionName, aDescription;
    	String locationName;
    	
    	locationName = location;
    	
    	String Query = "select * from location where location_name = \""+ locationName + "\";";
    	try {
			//connect to DB
	  		currentCon = DBConnection.getConnection();
	  		stmt = currentCon.createStatement();
	  		
			rs = stmt.executeQuery(Query);
			while(rs.next())
			{
				locationID = rs.getString("location_ID");
				locationType = rs.getString("location_type");
				System.out.println(locationID +""+ locationType);
	  		}
			if(locationType.equals("country")){
				
				System.out.println("qwerty");
				Query = "select * from region where location_ID = \""+ locationID +"\";";
				System.out.println(Query);
				rs = stmt.executeQuery(Query);
				while(rs.next())
				{
					taBean loc = new taBean();
					regionName = rs.getString("region_name");
					rDescription = rs.getString("description");
					System.out.println(regionName);
					loc.setRegionName(regionName);
					loc.setDescription(rDescription);
					taList.add(loc);
		  		}
			}
			if(locationType.equals("region")){
				
				System.out.println("qwerty");
				Query = "select * from attractions where location_ID = \""+ locationID +"\";";
				System.out.println(Query);
				rs = stmt.executeQuery(Query);
				while(rs.next())
				{
					taBean loc = new taBean();
					attractionName = rs.getString("attraction_name");
					aDescription = rs.getString("description");
					
					loc.setAttractionName(attractionName);
					loc.setDescription(aDescription);
					taList.add(loc);
					//System.out.println(loc.getAttractionName());
		  		}
			}
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	
    	for (int i = 0; i < taList.size(); i++) {
            //System.out.println(i+" "+taList.get(i));
            System.out.println(taList.get(i).getAttractionName());
            System.out.println(taList.get(i).getDescription());
            
    	}
		return taList;
	}

}
