package com.ssdi.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class flightDetails {
	
	public static Map<String, ArrayList<String>> JsonToMAp(JSONObject jsonObjectResponse1) {

		
		JSONObject jsonObjectResponse = jsonObjectResponse1;
		
		Map<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();

		
		try {
		
		
		for (int i = 0; i < jsonObjectResponse.getJSONObject("trips").getJSONArray("tripOption").length(); i++)
		
		{
			List<String> flightArray = new ArrayList<String>();
			String Source = jsonObjectResponse.getJSONObject("trips").getJSONArray("tripOption").getJSONObject(i)
					.getJSONArray("slice").getJSONObject(0).getJSONArray("segment").getJSONObject(0)
					.getJSONArray("leg").getJSONObject(0).getString("origin");

			String flightID = jsonObjectResponse.getJSONObject("trips").getJSONArray("tripOption").getJSONObject(i)
					.getString("id");

			String Destination = jsonObjectResponse.getJSONObject("trips").getJSONArray("tripOption")
					.getJSONObject(i).getJSONArray("slice").getJSONObject(0).getJSONArray("segment")
					.getJSONObject(0).getJSONArray("leg").getJSONObject(0).getString("destination");

			String departureDateTime = jsonObjectResponse.getJSONObject("trips").getJSONArray("tripOption")
					.getJSONObject(i).getJSONArray("slice").getJSONObject(0).getJSONArray("segment")
					.getJSONObject(0).getJSONArray("leg").getJSONObject(0).getString("departureTime"); 
			
			String departureTime = parseADTime(departureDateTime);
			
			String DepatureDate = parseADDate(departureDateTime);
          
			String arrivalTimeDate = jsonObjectResponse.getJSONObject("trips").getJSONArray("tripOption")
					.getJSONObject(i).getJSONArray("slice").getJSONObject(0).getJSONArray("segment")
					.getJSONObject(0).getJSONArray("leg").getJSONObject(0).getString("arrivalTime");
			
			String arrivalTime = parseADTime(arrivalTimeDate);
			
			String arrivalDate = parseADDate(arrivalTimeDate);
			
			String Price = null;
			
			Price = jsonObjectResponse.getJSONObject("trips").getJSONArray("tripOption").getJSONObject(0).getString("saleTotal");
			
			flightArray.add(Source);
			flightArray.add(Destination);
			flightArray.add(departureTime);
			flightArray.add(arrivalTime);
			flightArray.add(Price);
			flightArray.add(DepatureDate);
			flightArray.add(arrivalDate);
			
			hm.put(flightID, (ArrayList<String>) flightArray);
						
		}
		}
		catch (JSONException e) {
			
			e.printStackTrace();
		}

		for (Entry<String, ArrayList<String>> entry : hm.entrySet()) {
			System.out.println( "Inside flight Details , Key : " + entry.getKey() + " Value : " + entry.getValue());
			
		}

		return hm;
				
	}
	
	
	public static String parseADTime(String time)
	{
		  String[] date = time.split("T");
        String departureTime = date[1];
        return departureTime;
	}
	
	public static String parseADDate(String date)
	{
		  String[] date1 = date.split("T");
        String departureTime = date1[0];
        return departureTime;
	}
	
		
}