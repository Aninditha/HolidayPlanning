package com.ssdi.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import com.ssdi.POJO.flightBean;

public class FlightModel {
	
	static Connection currentCon = null;
    static ArrayList<flightBean> flight = new ArrayList<flightBean>();
	
	public static ArrayList<flightBean> search(String givenSource, String givenDestination, String startDate, String endDate) {
		
		Statement stmt = null;
    	ResultSet rs = null;

    	double Price = 0;
    	int vacancies = 0, Stops;
    	String airlines, source, destination;
    	Time departureTime;
		Time arrivalTime, TravelTime, WaitTime;
    	Date DateOfDeparture, DateOfArrival;
		String StartDate;
    	flightBean bean = new flightBean();
    	source = bean.getSource();
    	
    	String flightSearch = "select * from flight where Source = \""+ givenSource +"\" and"
    			+ " Destination = \""+ givenDestination +"\" and DateOfDeparture = \'" + startDate+ "\';";
    	System.out.println(flightSearch);
    	try{
    		//connect to DB
	  		currentCon = DBConnection.getConnection();
	  		
	  		stmt = currentCon.createStatement();
	  		
	  		rs = stmt.executeQuery(flightSearch);
	  		while (rs.next()) {
	  			System.out.println("qwerty");
		    	flightBean flightData = new flightBean();
		    	
		    	airlines = rs.getString("AirlineName");
		    	Price = rs.getInt("Price");
		    	vacancies = rs.getInt("NumberOfVacantSeats");
		    	departureTime = rs.getTime("DepartureTime");
		    	arrivalTime = rs.getTime("ArrivalTime");
		    	source = rs.getString("Source");
		    	destination = rs.getString("Destination");
		    	DateOfDeparture = rs.getDate("DateOfDeparture");
		    	DateOfArrival = rs.getDate("DateOfArrival");
		    	TravelTime = rs.getTime("TravelTime");
		    	WaitTime = rs.getTime("WaitTime");
		    	Stops = rs.getInt("Stops");
		    	
		    	flightData.setSource(source);
		    	flightData.setDestination(destination);
		    	flightData.setPrice(Price);
		    	flightData.setVacancies(vacancies);
		    	flightData.setStops(Stops);
		    	flightData.setAirlines(airlines);
		    	flightData.setDepartureTime(departureTime);
		    	flightData.setArrivalTime(arrivalTime);
		    	flightData.setTravelTime(TravelTime);
		    	flightData.setWaitTime(WaitTime);
		    	flightData.setDateOfDeparture(DateOfDeparture);
		    	flightData.setDateOfArrival(DateOfArrival);
		    	
		    	System.out.println("" + DateOfDeparture + departureTime);
		    	flight.add(flightData);
	  		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally{
			System.out.println(flight.size());
			for (int i = 0; i < flight.size(); i++) {
	            System.out.println(i+" "+flight.get(i));
	            System.out.println(flight.get(i).getSource());
	        }
		}
    	return flight;
	}
}
