package com.ssdi.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.ssdi.POJO.hotelBean;

public class HotelModel {
	
	static Connection currentCon = null;
	
    public static ArrayList<hotelBean> search(hotelBean bean) throws SQLException {
    	
    	ArrayList<hotelBean> hotel = new ArrayList<hotelBean>();
    	Statement stmt = null;
    	ResultSet rs = null;
    	String hotelName = null;
    	String description = "";
    	double price = 0.0;
    	float rating = 0;
    	String typeOfRoom = "";
    	int numberOfVacancies = 0;

    	String region = bean.getRegion();
    	String regionID = null;
		currentCon = DBConnection.getConnection();
		stmt = currentCon.createStatement();
		
		String regionQuery = "select region_ID from region where region_name = \""+ region +"\";";
		try {
			//connect to DB
	  		currentCon = DBConnection.getConnection();
	  		
	  		stmt = currentCon.createStatement();
	  		
			rs = stmt.executeQuery(regionQuery);
			while(rs.next())
			{
				regionID = rs.getString("region_ID");
				System.out.println("region_ID" + regionID);
	  		}
			
			String hotelQuery = "select * from hotel where region_ID = \""+ regionID +"\";";
			rs = stmt.executeQuery(hotelQuery);
		    System.out.println(hotelQuery);
		    while (rs.next()) {
		    	System.out.println("qwerty");
		    	hotelBean hotelData = new hotelBean();
		        
		    	hotelName = rs.getString("hotel_name");
				description = rs.getString("description");
				price = (double) rs.getInt("price");
				rating = rs.getFloat("rating");
				typeOfRoom = rs.getString("type_of_room");
				numberOfVacancies = rs.getInt("number_of_vacancies");
		    	
				hotelData.setDescription(description);
		    	hotelData.setHotelName(hotelName);
		    	hotelData.setNumberOfVacancies(numberOfVacancies);
		    	hotelData.setPrice(price);
		    	hotelData.setRating(rating);
		    	hotelData.setTypeOfRoom(typeOfRoom);
		    	
		    	hotel.add(hotelData);
		    }
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			System.out.println(hotel.size());
			for (int i = 0; i < hotel.size(); i++) {
	            System.out.println(i+" "+hotel.get(i));
	            System.out.println(hotel.get(i).getHotelName());
	        }
		}
	return hotel;
	}
}
