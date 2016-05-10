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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.ssdi.util.ConnectionUtil;
import com.ssdi.POJO.HotelCostBean;
import com.ssdi.POJO.adminBean;
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

	public boolean checkEmail(String email) {

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

	public static int BookFlight(String userName, flightBean flightdetails) {

		int status = 2;
		int bookingID;
		Random rand = new Random();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		bookingID = rand.nextInt(Integer.MAX_VALUE) + 1;
		String today = dateFormat.format(date);
		String b1 = "" + bookingID;

		PreparedStatement preparedStmt = null;
		Connection currentConnection = null;

		String tripType = "oneWay";
		String source = flightdetails.getSource1();
		String destination = flightdetails.getDestination1();
		String price = flightdetails.getPrice();
		String dateofdep1 = flightdetails.getDateOfDeparture1();
		String dateofArr1 = flightdetails.getDateOfArrival1();
		String depTime1 = flightdetails.getDepartureTime1();
		String arrTime1 = flightdetails.getArrivalTime1();

		if (flightdetails.isRoundtrip()) {
			tripType = "roundTrip";
			String dateofReturn = flightdetails.getDateOfDeparture2();
			String dateofArr2 = flightdetails.getDateOfArrival2();
			String returnTime = flightdetails.getDepartureTime2();
			String arrTime2 = flightdetails.getArrivalTime2();

			String RoundTripQuery = "insert into flightbookings (booking_ID, user_ID, tripType, destination, "
					+ "source, totalCost, dateOfDeparture, dateOfArrival1, timeOfDeparture, timeOfArrival1, "
					+ "dateOfReturn, dateOfArrival2, timeOfReturn, timeOfArrival2, dateOfBooking) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				// connect to DB
				currentConnection = ConnectionUtil.getConnection(connectionData);

				// create the mysql insert preparedstatement
				preparedStmt = currentConnection.prepareStatement(RoundTripQuery);
				preparedStmt.setString(1, b1);
				preparedStmt.setString(2, userName);
				preparedStmt.setString(3, tripType);
				preparedStmt.setString(4, destination);
				preparedStmt.setString(5, source);
				preparedStmt.setString(6, price);
				preparedStmt.setString(7, dateofdep1);
				preparedStmt.setString(8, dateofArr1);
				preparedStmt.setString(9, depTime1);
				preparedStmt.setString(10, arrTime1);
				preparedStmt.setString(11, dateofReturn);
				preparedStmt.setString(12, dateofArr2);
				preparedStmt.setString(13, returnTime);
				preparedStmt.setString(14, arrTime2);
				preparedStmt.setString(15, today);

				// execute the preparedstatement
				status = preparedStmt.executeUpdate();
				System.out.println(status);
				currentConnection.close();
				return status;
			} catch (Exception ex) {
				System.out.println("Log In failed: An Exception has occurred! " + ex);
			}
			return status;
		}

		String OneWayQuery = "insert into flightbookings (booking_ID, user_ID, tripType, destination, "
				+ "source, totalCost, dateOfDeparture, dateOfArrival1, timeOfDeparture, timeOfArrival1, "
				+ "dateOfBooking) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);

			// create the mysql insert preparedstatement
			preparedStmt = currentConnection.prepareStatement(OneWayQuery);
			preparedStmt.setString(1, b1);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, tripType);
			preparedStmt.setString(4, destination);
			preparedStmt.setString(5, source);
			preparedStmt.setString(6, price);
			preparedStmt.setString(7, dateofdep1);
			preparedStmt.setString(8, dateofArr1);
			preparedStmt.setString(9, depTime1);
			preparedStmt.setString(10, arrTime1);
			preparedStmt.setString(11, today);

			// execute the preparedstatement
			status = preparedStmt.executeUpdate();
			System.out.println(status);
			currentConnection.close();
			return status;
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}
		return status;
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
		bookingID = rand.nextInt(Integer.MAX_VALUE) + 1;
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
			System.out.println(status);
			currentConnection.close();
			return status;
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

	public ArrayList<hotelBean> ViewUserBooking(String username) {

		ArrayList<hotelBean> hotelBookingList = new ArrayList<hotelBean>();
		Connection currentConnection = null;
		Statement stmt = null;
		ResultSet rs = null;

		hotelBean hotel;

		String Query = "select * from hotelBookings where user_ID = \"" + username + "\";";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);

			stmt = currentConnection.createStatement();

			System.out.println("insdide view == " +Query);
			
			rs = stmt.executeQuery(Query);
			while (rs.next()) {

				hotel = new hotelBean();

				String hotelBooking_ID = rs.getString("hotelBooking_ID");
				String hotelName = rs.getString("hotelName");
				int numberOfRooms = rs.getInt("numberOfRooms");
				int numberOfNights = rs.getInt("numberOfNights");
				String typeOfRoom = rs.getString("typeOfRooms");
				int hotelTotalCost = rs.getInt("hotelTotalCost");
				Date dateOfBooking = rs.getDate("dateOfBooking");

				hotel.setHotelName(hotelName);
				hotel.setHotelBooking_ID(hotelBooking_ID);
				hotel.setDateOfBooking(dateOfBooking);
				hotel.setNumberOfRooms(numberOfRooms);
				hotel.setNumberOfNights(numberOfNights);
				hotel.setHotelTotalCost(hotelTotalCost);
				hotel.setDateOfBooking(dateOfBooking);
				hotel.setTypeOfRoom(typeOfRoom);

				hotelBookingList.add(hotel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("List size: "+hotelBookingList.size());
		return hotelBookingList;
	}
	
	//flight view booking
	
	public ArrayList<flightBean> ViewFlightBookings(String username) {

		ArrayList<flightBean> flightBookingList = new ArrayList<flightBean>();
		Connection currentConnection = null;
		Statement stmt = null;
		ResultSet rs = null;

		flightBean flight;

		String Query = "select * from flightBookings where user_ID = \"" + username + "\";";

		try {
			// connect to DB
			currentConnection = ConnectionUtil.getConnection(connectionData);
			stmt = currentConnection.createStatement();
			
			rs = stmt.executeQuery(Query);
			while (rs.next()){

				flight = new flightBean();
				
				String flightBooking_ID = rs.getString("booking_ID");
				String tripType = rs.getString("tripType");
				String destination = rs.getString("destination");
				String source = rs.getString("source");
				String cost = rs.getString("totalCost");
				String dateOfDeparture = rs.getString("dateOfDeparture");
				String dateOfArrival1 = rs.getString("dateOfArrival1");
				String timeOfDeparture = rs.getString("timeOfDeparture");
				String timeOfArrival1 = rs.getString("timeOfArrival1");
//				String dateOfReturn = rs.getString("dateOfReturn");
//				String dateOfArrival2 = rs.getString("dateOfArrival2");
//				String timeOfReturn = rs.getString("timeOfReturn");
//				String timeOfArrival2 = rs.getString("timeOfArrival2");
				String dateOfBooking = rs.getString("dateOfBooking");

	//			if(tripType.equals("oneWay")){
					flight.setFlightID(flightBooking_ID);
					flight.setTripType(tripType);
					flight.setSource1(source);
					flight.setDestination1(destination);
					flight.setPrice(cost);
					flight.setDateOfDeparture1(dateOfDeparture);
					flight.setDateOfArrival1(dateOfArrival1);;
					flight.setDepartureTime1(timeOfDeparture);
					flight.setArrivalTime1(timeOfArrival1);
					flight.setDateOfBooking(dateOfBooking);
					
					flightBookingList.add(flight);
		//		}
			}
			System.out.println(flightBookingList.size());
			 Iterator iter = flightBookingList.iterator();
		      while (iter.hasNext()) {
		    	  flightBean f1 = (flightBean) iter.next();
		         System.out.println(f1.getSource1());
		      }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flightBookingList;
	}
	
	public int addHotel(hotelBean hotel) {

		Connection currentConnection = null;
		currentConnection = ConnectionUtil.getConnection(connectionData);

		try {
			PreparedStatement stmt = null;
			String query = "insert into hotel values(?,?,?,?,?)";

			stmt = currentConnection.prepareStatement(query);
			stmt.setString(1, hotel.getRegionID());
			stmt.setString(2, hotel.getHotelBooking_ID());
			stmt.setString(3, hotel.getHotelName());
			stmt.setString(4, hotel.getDescription());
			stmt.setDouble(5, hotel.getRating());
			stmt.executeUpdate();

			currentConnection.close();

			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception in add hotel method");
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteHotel(hotelBean hotel) {

		Connection currentConnection = null;
		currentConnection = ConnectionUtil.getConnection(connectionData);

		try {
			PreparedStatement stmt = null;

			String query = null;
			query = "delete from hotel where hotel_ID=?;";
			System.out.println("deleting hotel");
			stmt = currentConnection.prepareStatement(query);
			stmt.setString(1, hotel.getHotelBooking_ID());
			stmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			System.out.println("exception in delete hotel method");
		}
		return 0;
	}

	public int addHotelDetail(hotelBean hotel) {
		Connection currentConnection = null;
		currentConnection = ConnectionUtil.getConnection(connectionData);

		try {
			PreparedStatement stmt = null;

			String query = "insert into hoteldetails values(?,?,?,?)";
			stmt = currentConnection.prepareStatement(query);

			stmt.setString(1, hotel.getHotelBooking_ID());
			stmt.setString(2, hotel.getHotelDetailId());
			stmt.setDouble(3, hotel.getHoteldetailprice());
			stmt.setString(4, hotel.getTypeOfRoom());
			stmt.executeUpdate();

			return 1;
		} catch (Exception e) {
			System.out.println("exception in add hotel method");
		}
		return 0;
	}

	public boolean isValidAdmin(adminBean adminBean) {

		Connection currentConnection = null;
		currentConnection = ConnectionUtil.getConnection(connectionData);

		try {
			PreparedStatement stmt = null;

			String query = null;
			query = "select * from admin where Email=? and password=?";
			stmt = currentConnection.prepareStatement(query);
			stmt.setString(1, adminBean.getEmail());
			stmt.setString(2, adminBean.getPassword());

			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException ex) {
			System.out.println("Exception in add admin method");
		}
		return false;
	}
}