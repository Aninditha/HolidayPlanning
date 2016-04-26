package com.ssdi.api;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ssdi.api.flightDetails;

public class ConnectFlightAPI {
	
	
	public static Map<String, ArrayList<String>> flightAPI(String source, String destination1, String startDate,
			String endDate, int capacity, boolean roundtrip) {


		String origin = source;
		String destination = destination1;
		String travelDate = startDate;
		
		
		String travelDateReturn = endDate;
		
		
		JSONObject jsonObjectResponse = null;

		int adultCount = capacity;
		
		
		try {
			JSONObject jsonObject = new JSONObject();
			JSONObject requestObject = new JSONObject();
			JSONObject passengersObject = new JSONObject();
			JSONObject sliceZeroObject = new JSONObject();
			JSONObject sliceOneObject = new JSONObject();
			sliceZeroObject.put("origin", origin);
			sliceZeroObject.put("destination", destination);
			sliceZeroObject.put("date", travelDate);
			
			JSONArray sliceArray = new JSONArray();
			sliceArray.put(sliceZeroObject);
			
			if(roundtrip == true)
			{
				sliceOneObject.put("origin", destination);
				sliceOneObject.put("destination", origin);
				sliceOneObject.put("date", travelDateReturn);	
				
				sliceArray.put(sliceOneObject);
			}
			
			requestObject.put("slice", sliceArray);
			passengersObject.put("adultCount", adultCount);

			requestObject.put("passengers", passengersObject);
			requestObject.put("solutions", 20);
			requestObject.put("refundable", true);
			jsonObject.put("request", requestObject);

			URL url = new URL(
					"https://www.googleapis.com/qpxExpress/v1/trips/search?key=AIzaSyAJPNclO1mkYqESFZvlcxbgEpg9N7lByFk");
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.connect();
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(jsonObject.toString());
			out.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = br.readLine()) != null)
				sb.append(line);
			br.close();

			jsonObjectResponse = new JSONObject(sb.toString());
		
			} catch (JSONException e) {
			 e.printStackTrace();
			
			System.out.println("error");
		} catch (IOException e) {
		
			e.printStackTrace();
		}

		return flightDetails.JsonToMAp(jsonObjectResponse,roundtrip);

	}
}
