package com.ssdi.POJO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class hotelBean {

	private String region;
	private String hotelName;
	private String description;
	private float rating;
	private int numberOfVacancies;
	private String regionID;
	private Map<String, Double> roomPrice = new HashMap<String, Double>();
	
	
	private String hotelBooking_ID;
	private int numberOfRooms;
	private int numberOfNights;
	private String typeOfRooms;
	private int hotelTotalCost;
	private Date dateOfBooking;
	
	
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

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
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
}