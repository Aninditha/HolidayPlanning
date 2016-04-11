package com.ssdi.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ssdi.api.ConnectFlightAPI;
import com.ssdi.POJO.flightBean;

public class FlightModel {

	static Connection currentCon = null;

	public static List<flightBean> call_Flight_model(String source, String destination, String startDate,
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
			
			

			/*
			 * flightData.setVacancies(vacancies); flightData.setStops(Stops);
			 * flightData.setAirlines(airlines);
			 */

			flight.add(flightData);
		}

		return flight;

	}
}
