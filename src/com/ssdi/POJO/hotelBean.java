package com.ssdi.POJO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class hotelBean {

	private String region;
	private String hotelName;
	private String description;
	private double rating;
	private int numberOfVacancies;
	private String regionID;
	private Map<String, Double> roomPrice = new HashMap<String, Double>();
	
	
	private String hotelBooking_ID;
	private int numberOfRooms;
	private int numberOfNights;
	private String typeOfRooms;
	private int hotelTotalCost;
	private Date dateOfBooking;
	
	
	private double hoteldetailprice;
	private String typeOfRoom;
	private String HotelDetailId;
	
	public String getHotelBooking_ID() {
		return hotelBooking_ID;
	}

	public void setHotelBooking_ID(String hotelBooking_ID) {
		this.hotelBooking_ID = hotelBooking_ID;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(int numberOfNights) {
		this.numberOfNights = numberOfNights;
	}

	public String getTypeOfRooms() {
		return typeOfRooms;
	}

	public void setTypeOfRooms(String typeOfRooms) {
		this.typeOfRooms = typeOfRooms;
	}

	public int getHotelTotalCost() {
		return hotelTotalCost;
	}

	public void setHotelTotalCost(int hotelTotalCost) {
		this.hotelTotalCost = hotelTotalCost;
	}

	public Date getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getNumberOfVacancies() {
		return numberOfVacancies;
	}

	public void setNumberOfVacancies(int numberOfVacancies) {
		this.numberOfVacancies = numberOfVacancies;
	}

	public hotelBean() {
		// TODO Auto-generated constructor stub
	}

	public String getRegionID() {
		return regionID;
	}

	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}

	public Map<String, Double> getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Map<String, Double> roomPrice) {
		this.roomPrice = roomPrice;
	}

	public double getHoteldetailprice() {
		return hoteldetailprice;
	}

	public void setHoteldetailprice(double hoteldetailprice) {
		this.hoteldetailprice = hoteldetailprice;
	}

	public String getTypeOfRoom() {
		return typeOfRoom;
	}

	public void setTypeOfRoom(String typeOfRoom) {
		this.typeOfRoom = typeOfRoom;
	}

	public String getHotelDetailId() {
		return HotelDetailId;
	}

	public void setHotelDetailId(String hotelDetailId) {
		HotelDetailId = hotelDetailId;
	}

}