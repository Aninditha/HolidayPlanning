package com.ssdi.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class flightDetails {

	public static Map<String, ArrayList<String>> JsonToMAp(JSONObject jsonObjectResponse1, boolean roundtrip) {

		JSONObject jsonObjectResponse = jsonObjectResponse1;

		Map<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
		Map<String, String> flightcode = new HashMap<String, String>();

		try {

			flightcode = GetFlightsListCode(jsonObjectResponse);

			JSONArray tripOption = new JSONArray();

			StringBuffer stopPlaces = new StringBuffer();

			tripOption = jsonObjectResponse.getJSONObject("trips").getJSONArray("tripOption");

			String Source1 = null, departureTime = null, DepatureDate = null, Destination1 = null, arrivalTime = null,
					arrivalDate = null;
			
			String Source2 = null, departureTime2 = null, DepatureDate2 = null, Destination2 = null,
					arrivalTime2 = null, arrivalDate2 = null;
			

			for (int i = 0; i < tripOption.length(); i++) {
				
				Source1 = null;
				departureTime = null; DepatureDate = null; Destination1 = null; arrivalTime = null;
						arrivalDate = null;

				List<String> flightArray = new ArrayList<String>();

				JSONArray segment1 = tripOption.getJSONObject(i).getJSONArray("slice").getJSONObject(0)
						.getJSONArray("segment");

				for (int j = 0; j < segment1.length(); j++)

				{
					// showing error at here...
					JSONObject leg1 = segment1.getJSONObject(j).getJSONArray("leg").getJSONObject(0);

					if (j == 0) {
						Source1 = leg1.getString("origin");
						String departureDateTime = leg1.getString("departureTime");

						departureTime = parseADTime(departureDateTime);

						DepatureDate = parseADDate(departureDateTime);
					}

					if (j == 1) {
						Destination1 = leg1.getString("destination");

						String arrivalTimeDate = leg1.getString("arrivalTime");

						arrivalTime = parseADTime(arrivalTimeDate);

						arrivalDate = parseADDate(arrivalTimeDate);

					}

				}

				// For roundtrip values...
				if(roundtrip == true)
				{

				JSONArray segment2 = tripOption.getJSONObject(i).getJSONArray("slice").getJSONObject(1)
						.getJSONArray("segment");

				for (int j = 0; j < segment2.length(); j++)

				{

					
					int legLength = segment2.getJSONObject(j).getJSONArray("leg").length();
					
					
					JSONObject leg2 = segment2.getJSONObject(j).getJSONArray("leg").getJSONObject(legLength-1);

					if (j == 0) {
						Source2 = leg2.getString("origin");
						String departureDateTime = leg2.getString("departureTime");

						departureTime2 = parseADTime(departureDateTime);

						DepatureDate2 = parseADDate(departureDateTime);
					}

					if (j == 1) {
						Destination2 = leg2.getString("destination");

						String arrivalTimeDate = leg2.getString("arrivalTime");

						arrivalTime2 = parseADTime(arrivalTimeDate);

						arrivalDate2 = parseADDate(arrivalTimeDate);

					}

				}
				}

				String flightID = tripOption.getJSONObject(i).getString("id");

				String Price = tripOption.getJSONObject(i).getString("saleTotal");
				
				System.out.println(" Inside f detailsPrice==" + Price);

				flightArray.add(Source1);
				flightArray.add(Destination1);
				flightArray.add(departureTime);
				flightArray.add(arrivalTime);
				flightArray.add(DepatureDate);
				flightArray.add(arrivalDate);
				
				flightArray.add(Price);
				
				if(roundtrip == true)
				{
					  flightArray.add(Source2); 
					  flightArray.add(Destination2);
					  flightArray.add(departureTime2);
					  flightArray.add(arrivalTime2);
					  flightArray.add(DepatureDate2);
					  flightArray.add(arrivalDate2);
				}

				

				/*//System.out.println("source2 = " + Source2 + "Dest2 = " + Destination2 + "depttime2 = " + departureTime2
						+ "arrtime2 = " + arrivalTime2 + "deptdate=" + DepatureDate2 + "");*/

				

				hm.put(flightID, (ArrayList<String>) flightArray);

			}

		} catch (JSONException e) {

			e.printStackTrace();
		}

		return hm;
	}

	private static String CheckFlightCode(String string, Map map) {

		String temp = null;

		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {

			Entry<String, String> entry = iterator.next();

			if (entry.getKey().equals(string)) {

				System.out.println("inside CheckFlightCode = " + entry.getKey() + " value is  " + entry.getValue());

				temp = new String(entry.getValue().toString());
				return temp;
			}
		}

		return null;
	}

	private static Map<String, String> GetFlightsListCode(JSONObject jsonObjectResponse) {

		Map<String, String> flightcode1 = new HashMap<String, String>();

		JSONObject a = new JSONObject();
		JSONArray b = new JSONArray();

		System.out.println("b length = " + b.length());

		a = jsonObjectResponse;

		try {
			a = a.getJSONObject("trips").getJSONObject("data");
			b = a.getJSONArray("city");

			for (int i = 0; i < b.length(); i++) {
				String cityCode = b.getJSONObject(i).getString("code");
				String cityName = b.getJSONObject(i).getString("name");

				flightcode1.put(cityCode, cityName);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flightcode1;

	}

	public static String parseADTime(String time) {
		String[] date = time.split("T");
		String departureTime = date[1];
		return departureTime;
	}

	public static String parseADDate(String date) {
		String[] date1 = date.split("T");
		String departureTime = date1[0];
		return departureTime;
	}

}