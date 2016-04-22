package com.ssdi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ssdi.util.ConnectionUtil;
import com.ssdi.POJO.flightBean;
import com.ssdi.POJO.hotelBean;
import com.ssdi.POJO.taBean;
import com.ssdi.POJO.userbean;
import com.ssdi.api.ConnectFlightAPI;
import com.ssdi.util.IConnectionData;

public class ServicesDao {

	static IConnectionData connectionData;

	@SuppressWarnings("static-access")
	ServicesDao(IConnectionData connectionData) {
		this.connectionData = connectionData;
	}

	public userbean registerUser(userbean bean) {

		PreparedStatement preparedStmt = null;
		Connection currentConnection = null;

		String un = bean.getUsername();
		String pwd = bean.getPassword();
		String em = bean.getEmail();

		String Query = "insert into CustomerDetails (Name, Email, password)" + " values (?, ?, ?)";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);

			// create the mysql insert preparedstatement
			preparedStmt = currentConnection.prepareStatement(Query);
			preparedStmt.setString(1, un);
			preparedStmt.setString(2, em);
			preparedStmt.setString(3, pwd);

			// execute the preparedstatement
			preparedStmt.execute();

			currentConnection.close();
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}
		return bean;
	}

	public static boolean checkEmail(String email) {

		boolean exist = false;
		Statement stmt = null;
		ResultSet rs = null;
		int number = 0;
		Connection currentConnection = null;

		String Query = "select * from CustomerDetails where Email = \"" + email + "\";";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);

			stmt = currentConnection.createStatement();

			rs = stmt.executeQuery(Query);
			while (rs.next()) {
				if (rs.last())
					number = rs.getRow();
			}
			if (number == 1)
				exist = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(exist);
		return exist;
	}
	
	public boolean logIn(String username, String password) {
		
		Statement stmt = null;
    	ResultSet rs = null;
    	int number = 0;
    	Connection currentConnection = null;
    	boolean userExist = false;
		
		String Query = "select * from CustomerDetails where Email = \""+ username +"\" and "
				+ "password = \""+ password +"\";";
		
		try {
			//connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);
	  		
	  		stmt = currentConnection.createStatement();
	  		
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
		return userExist;
	}
	
	public ArrayList<hotelBean> searchHotels(hotelBean bean) throws SQLException {
    	
    	ArrayList<hotelBean> hotel = new ArrayList<hotelBean>();
    	Connection currentConnection = null;
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
		
		String regionQuery = "select region_ID from region where region_name = \""+ region +"\";";
		
		try {
			//connect to DB
	  		currentConnection = ConnectionUtil.getConnection(connectionData);
	  		
	  		stmt = currentConnection.createStatement();
	  		
			rs = stmt.executeQuery(regionQuery);
			while(rs.next())
			{
				regionID = rs.getString("region_ID");
				System.out.println("region_ID" + regionID);
	  		}
			
			String hotelQuery = "select * from hotel where region_ID = \""+ regionID +"\";";
			rs = stmt.executeQuery(hotelQuery);
		    
		    while (rs.next()) {
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
		}
		return hotel;
	}
    
    public boolean checkHotelRegion(String region) {
		boolean exist = false;
		
		Statement stmt = null;
    	ResultSet rs = null;
    	int number = 0;
    	Connection currentConnection = null;
		
		String Query = "select * from region inner join hotel on "
				+ "region.region_ID = hotel.region_ID where region_name =\""+ region + "\";";
		
		try {
			//connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);
	  		
	  		stmt = currentConnection.createStatement();
	  		
			rs = stmt.executeQuery(Query);
			while(rs.next()){
				if (rs.last())
					number = rs.getRow();
			}
			if(number > 1)
				exist = true;
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return exist;
	}
	
    public List<flightBean> searchFlights(String source, String destination, String startDate,
			String endDate, int capacity) {

		Map<String, ArrayList<String>> hm = ConnectFlightAPI.flightAPI(source, destination, startDate, endDate, capacity);
		
		for (Entry<String, ArrayList<String>> entry : hm.entrySet()) {
			System.out.println("Inside model flight");
			System.out.println("key:" + entry.getKey() + "Value of array list = " + entry.getValue());
		}

		ArrayList<flightBean> flight = new ArrayList<flightBean>();
		flightBean flightData;

		for (Entry<String, ArrayList<String>> entry : hm.entrySet()) {

			flightData = new flightBean();
			List<String> list = new ArrayList<String>();
			list = entry.getValue();
			
			flightData.setFlightID(entry.getKey());
			flightData.setSource(list.get(0));
			flightData.setDestination(list.get(1));
			flightData.setDepartureTime(list.get(2));
			flightData.setArrivalTime(list.get(3));
			flightData.setPrice(list.get(4));
			flightData.setDateOfDeparture(list.get(5));
			flightData.setDateOfArrival(list.get(6));
			
			flight.add(flightData);
		}
		return flight;
	}
    
    public ArrayList<taBean> searchRegions(String location) {
		ArrayList<taBean> taList = new ArrayList<taBean>();
		Statement stmt = null;
    	ResultSet rs = null;
    	Connection currentConnection = null;
    	String regionName, description;
    	
    	String Query = "select * from country inner join region on "
    			+ "country.Country_ID = region.Country_ID where Country_name =\""+ location + "\";";
    	try {
			//connect to DB
	  		currentConnection = ConnectionUtil.getConnection(connectionData);
	  		stmt = currentConnection.createStatement();
	  		
			rs = stmt.executeQuery(Query);
			while(rs.next())
			{
				taBean loc = new taBean();
				regionName = rs.getString("region_name");
				description = rs.getString("description");
				loc.setRegionName(regionName);
				loc.setDescription(description);
				taList.add(loc);
			}
    	} catch (Exception e){
    		e.printStackTrace();
    	}
		return taList;
	}
	
	public ArrayList<taBean> searchAttractions(String location) {
		ArrayList<taBean> taList = new ArrayList<taBean>();
		Statement stmt = null;
    	ResultSet rs = null;
    	Connection currentConnection = null;
    	String attractionName, description;
    	
    	String Query = "select * from region inner join attractions on "
    			+ "region.region_ID = attractions.region_ID where region_name =\""+ location + "\";";
    	try {
			//connect to DB
	  		currentConnection = ConnectionUtil.getConnection(connectionData);
	  		stmt = currentConnection.createStatement();
	  		
			rs = stmt.executeQuery(Query);
			while(rs.next())
			{
				taBean loc = new taBean();
				attractionName = rs.getString("attraction_name");
				description = rs.getString("attractions.description");
				loc.setAttractionName(attractionName);
				loc.setDescription(description);
				taList.add(loc);
			}
    	} catch (Exception e){
    		e.printStackTrace();
    	}
		return taList;
	}
	
	public boolean checkCountry(String country) {
		
		boolean exist = false;
		Connection currentConnection = null;
		Statement stmt = null;
    	ResultSet rs = null;
    	int number = 0;
    	currentConnection = ConnectionUtil.getConnection(connectionData);
		
		String Query = "select * from country where country_name = \""+ country +"\";";
		
		try {
			//connect to DB
	  		currentConnection = ConnectionUtil.getConnection(connectionData);
	  		
	  		stmt = currentConnection.createStatement();
	  		
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
		return exist;
	}
	
	public boolean checkRegion(String region) {
		
		boolean exist = false;
		Connection currentConnection = null;
		Statement stmt = null;
    	ResultSet rs = null;
    	int number = 0;
		
		String Query = "select * from region where region_name = \""+ region +"\";";
		
		try {
			//connect to DB
	  		currentConnection = ConnectionUtil.getConnection(connectionData);
	  		
	  		stmt = currentConnection.createStatement();
	  		
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
		return exist;
	}
}