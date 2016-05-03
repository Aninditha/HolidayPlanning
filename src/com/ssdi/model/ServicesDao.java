package com.ssdi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.ssdi.util.ConnectionUtil;
import com.ssdi.POJO.HotelCostBean;
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
		return exist;
	}

	public boolean logIn(String username, String password) {

		Statement stmt = null;
		ResultSet rs = null;
		int number = 0;
		Connection currentConnection = null;
		boolean userExist = false;

		String Query = "select * from CustomerDetails where Email = \"" + username + "\" and " + "password = \""
				+ password + "\";";

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
				userExist = true;

		} catch (Exception e) {
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
		float rating = 0;

		String region = bean.getRegion();
		String regionID = null;

		String hotelQuery = "select * from region inner join hotel on "
				+ "region.region_ID = hotel.region_ID where region_name =\"" + region + "\";";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);

			stmt = currentConnection.createStatement();
			rs = stmt.executeQuery(hotelQuery);

			while (rs.next()) {
				hotelBean hotelData = new hotelBean();

				regionID = rs.getString("region_ID");
				hotelName = rs.getString("hotel_name");
				description = rs.getString("hotel.description");
				rating = rs.getFloat("rating");

				hotelData.setRegionID(regionID);
				hotelData.setDescription(description);
				hotelData.setHotelName(hotelName);
				hotelData.setRating(rating);

				hotel.add(hotelData);
			}
		} catch (Exception e) {
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

		String Query = "select region_ID from region where region_name = \"" + region + "\";";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);

			stmt = currentConnection.createStatement();

			rs = stmt.executeQuery(Query);
			while (rs.next()) {
				if (rs.last())
					number = rs.getRow();
			}
			if (number > 0)
				exist = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exist;
	}

	public Map<String, flightBean> searchFlights(String source, String destination, String startDate, String endDate,
			int capacity, boolean roundtrip) {

		Map<String, ArrayList<String>> hm = ConnectFlightAPI.flightAPI(source, destination, startDate, endDate,
				capacity, roundtrip);

		Map<String, flightBean> flightDetails1 = new HashMap<String, flightBean>();

		ArrayList<String> flight = new ArrayList<>();
		flightBean flightData;

		for (Entry<String, ArrayList<String>> entry : hm.entrySet()) {

			flightData = new flightBean();
			List<String> list = new ArrayList<String>();
			list = entry.getValue();

			flightData.setFlightID(entry.getKey());
			flightData.setSource1(list.get(0));
			flightData.setDestination1(list.get(1));
			flightData.setDepartureTime1(list.get(2));
			flightData.setArrivalTime1(list.get(3));
			flightData.setDateOfDeparture1(list.get(4));
			flightData.setDateOfArrival1(list.get(5));

			flightData.setPrice(list.get(6));

			flight.add(flightData.getSource1());
			flight.add(flightData.getDestination1());
			flight.add(flightData.getDepartureTime1());
			flight.add(flightData.getArrivalTime1());
			flight.add(flightData.getDateOfDeparture1());
			flight.add(flightData.getDateOfArrival1());
			flight.add(flightData.getPrice());

			if (roundtrip == true) {

				flightData.setRoundtrip(true);
				flightData.setSource2(list.get(7));
				flightData.setDestination2(list.get(8));
				flightData.setDepartureTime2(list.get(9));
				flightData.setArrivalTime2(list.get(10));
				flightData.setDateOfDeparture2(list.get(11));
				flightData.setDateOfArrival2(list.get(12));

				flight.add(flightData.getSource2());
				flight.add(flightData.getDestination2());
				flight.add(flightData.getDepartureTime2());
				flight.add(flightData.getArrivalTime2());
				flight.add(flightData.getDateOfDeparture2());
				flight.add(flightData.getDateOfArrival2());

			}

			flightDetails1.put(flightData.getFlightID(), flightData);

		}
		return flightDetails1;
	}

	public static int BookFlight(flightBean flightdetails) {

		PreparedStatement preparedStmt = null;
		Connection currentConnection = null;

		// String em = us.getEmail();

		String F_ID = flightdetails.getFlightID();

		String source1 = flightdetails.getSource1();
		String destination1 = flightdetails.getDestination1();
		String price = flightdetails.getPrice();
		String dateofdep1 = flightdetails.getDateOfDeparture1();
		String dateofArr1 = flightdetails.getDateOfArrival1();
		String depTime1 = flightdetails.getDepartureTime1();
		String arrTime1 = flightdetails.getArrivalTime2();

		if (flightdetails.isRoundtrip()) {

			// query for roundtrip insert...

		}

		// CHANGE THE QUERY TO FLIGHT BOOKING....

		String Query = "insert into flightbookings (hotelBooking_ID, user_ID, hotelName, numberOfRooms, numberOfNights,"
				+ "typeOfRooms, hotelTotalCost, dateOfBooking) values (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);

			// create the mysql insert preparedstatement
			preparedStmt = currentConnection.prepareStatement(Query);
			/*
			 * preparedStmt.setString(1, b1); preparedStmt.setString(2, em);
			 * preparedStmt.setString(3, hn);
			 * 
			 * // execute the preparedstatement status =
			 * preparedStmt.executeUpdate();
			 */
			currentConnection.close();
			return 1;
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}
		// return false;
		// return status;
		return 0;
	}

	public ArrayList<taBean> searchRegions(String location) {
		ArrayList<taBean> taList = new ArrayList<taBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection currentConnection = null;
		String regionName, description;

		String Query = "select * from country inner join region on "
				+ "country.Country_ID = region.Country_ID where Country_name =\"" + location + "\";";
		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);
			stmt = currentConnection.createStatement();

			rs = stmt.executeQuery(Query);
			while (rs.next()) {
				taBean loc = new taBean();
				regionName = rs.getString("region_name");
				description = rs.getString("description");
				loc.setRegionName(regionName);
				loc.setDescription(description);
				taList.add(loc);
			}
		} catch (Exception e) {
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
				+ "region.region_ID = attractions.region_ID where region_name =\"" + location + "\";";
		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);
			stmt = currentConnection.createStatement();

			rs = stmt.executeQuery(Query);
			while (rs.next()) {
				taBean loc = new taBean();
				attractionName = rs.getString("attraction_name");
				description = rs.getString("attractions.description");
				loc.setAttractionName(attractionName);
				loc.setDescription(description);
				taList.add(loc);
			}
		} catch (Exception e) {
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

		String Query = "select * from country where country_name = \"" + country + "\";";

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
		return exist;
	}

	public boolean checkRegion(String region) {

		boolean exist = false;
		Connection currentConnection = null;
		Statement stmt = null;
		ResultSet rs = null;
		int number = 0;

		String Query = "select * from region where region_name = \"" + region + "\";";

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
		return exist;
	}

	public hotelBean searchHotelDetails(String name, String regionID) {

		hotelBean hotel = new hotelBean();
		Map<String, Double> roomPriceList = new HashMap<String, Double>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection currentConnection = null;
		String hotelName, description, typeOfRoom;
		double price;

		String Query = "select * from hotel inner join hotelDetails on "
				+ "hotel.hotel_ID = hotelDetails.hotel_ID where hotel_name =\"" + name + "\" and " + "region_ID =\""
				+ regionID + "\";";
		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);
			stmt = currentConnection.createStatement();

			rs = stmt.executeQuery(Query);
			while (rs.next()) {
				hotelName = rs.getString("hotel_name");
				description = rs.getString("description");
				typeOfRoom = rs.getString("type_of_room");
				price = rs.getInt("price");
				hotel.setHotelName(hotelName);
				hotel.setDescription(description);
				roomPriceList.put(typeOfRoom, price);
			}
			hotel.setRoomPrice(roomPriceList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hotel;
	}

	public double calculateCost(HotelCostBean cost, String regionID) {
		@SuppressWarnings("unused")
		HotelCostBean hotel = new HotelCostBean();

		Statement stmt = null;
		ResultSet rs = null;
		Connection currentConnection = null;
		double finalPrice;

		String Query = "select price from hotel inner join hotelDetails on "
				+ "hotel.hotel_ID = hotelDetails.hotel_ID where hotel_name =\"" + cost.getHotelName() + "\" and "
				+ "region_ID =\"" + regionID + "\" and " + "type_of_room =\"" + cost.getRoomType() + "\";";
		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);
			stmt = currentConnection.createStatement();

			rs = stmt.executeQuery(Query);
			while (rs.next())
				cost.setPrice(rs.getDouble("price"));
			finalPrice = cost.getPrice() * cost.getNumberOfNights() * cost.getNumberOfRooms();
			return finalPrice;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int bookHotel(userbean user, HotelCostBean cost) {

		int status = 2;
		int bookingID;
		Random rand = new Random();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		bookingID = rand.nextInt();
		String today = dateFormat.format(date);
		String b1 = "" + bookingID;

		PreparedStatement preparedStmt = null;
		Connection currentConnection = null;

		String em = user.getEmail();
		String hn = cost.getHotelName();
		int nr = cost.getNumberOfRooms();
		int nn = cost.getNumberOfNights();
		String rt = cost.getRoomType();
		int price = (int) cost.getPrice();
		today = today.substring(0, 10);

		String Query = "insert into hotelbookings (hotelBooking_ID, user_ID, hotelName, numberOfRooms, numberOfNights,"
				+ "typeOfRooms, hotelTotalCost, dateOfBooking) values (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);

			// create the mysql insert preparedstatement
			preparedStmt = currentConnection.prepareStatement(Query);
			preparedStmt.setString(1, b1);
			preparedStmt.setString(2, em);
			preparedStmt.setString(3, hn);
			preparedStmt.setInt(4, nr);
			preparedStmt.setInt(5, nn);
			preparedStmt.setString(6, rt);
			preparedStmt.setInt(7, price);
			preparedStmt.setString(8, today);

			// execute the preparedstatement
			status = preparedStmt.executeUpdate();
			currentConnection.close();
			return 1;
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}
		// return false;
		return status;
	}

	public int LoyalityPoints(String username) {
		Connection currentConnection = null;
		Statement stmt = null;
		ResultSet rs = null;
		int points = 0;

		String Query = "select * from loyaltypoints where user_ID = \"" + username + "\";";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);

			stmt = currentConnection.createStatement();

			rs = stmt.executeQuery(Query);
			while (rs.next()) {
				points = rs.getInt("points");
			}
			System.out.println(points);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return points;
	}
}