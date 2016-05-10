package com.ssdi.POJO;

import java.sql.Time;

public class flightBean {

	private String source1;
	private String destination1;
	private String departureTime1;
	private String arrivalTime1;
	private String DateOfDeparture1;
	private String DateOfArrival1;

	private String source2;
	private String destination2;
	private String departureTime2;
	private String arrivalTime2;
	private String DateOfDeparture2;
	private String DateOfArrival2;
	private String dateOfBooking;
	private String tripType;

	private boolean roundtrip;
	private String startDate;
	private String endDate;
	private String FlightID;
	private String Price;
	private int vacancies = 0, Stops;

	private Time TravelTime, WaitTime;

	public boolean isRoundtrip() {
		return roundtrip;
	}

	public void setRoundtrip(boolean roundtrip) {
		this.roundtrip = roundtrip;
	}

	public String getSource2() {
		return source2;
	}

	public void setSource2(String source2) {
		this.source2 = source2;
	}

	public String getDestination2() {
		return destination2;
	}

	public void setDestination2(String destination2) {
		this.destination2 = destination2;
	}

	public String getDepartureTime2() {
		return departureTime2;
	}

	public void setDepartureTime2(String departureTime2) {
		this.departureTime2 = departureTime2;
	}

	public String getArrivalTime2() {
		return arrivalTime2;
	}

	public void setArrivalTime2(String arrivalTime2) {
		this.arrivalTime2 = arrivalTime2;
	}

	public String getDateOfDeparture2() {
		return DateOfDeparture2;
	}

	public void setDateOfDeparture2(String dateOfDeparture2) {
		DateOfDeparture2 = dateOfDeparture2;
	}

	public String getDateOfArrival2() {
		return DateOfArrival2;
	}

	public void setDateOfArrival2(String dateOfArrival2) {
		DateOfArrival2 = dateOfArrival2;
	}

	public String getSource1() {
		return source1;
	}

	public void setSource1(String source) {
		this.source1 = source;
	}

	public String getDestination1() {
		return destination1;
	}

	public void setDestination1(String destination) {
		this.destination1 = destination;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/*
	 * public String getAirlines() { return airlines; } public void
	 * setAirlines(String airlines) { this.airlines = airlines; }
	 */
	public String getPrice() {
		return Price;
	}

	public void setPrice(String Price) {
		this.Price = Price;
	}

	public int getVacancies() {
		return vacancies;
	}

	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}

	public int getStops() {
		return Stops;
	}

	public void setStops(int stops) {
		Stops = stops;
	}

	public String getDepartureTime1() {
		return departureTime1;
	}

	public void setDepartureTime1(String departureTime1) {
		this.departureTime1 = departureTime1;
	}

	public String getArrivalTime1() {
		return arrivalTime1;
	}

	public void setArrivalTime1(String arrivalTime) {
		this.arrivalTime1 = arrivalTime;
	}

	public Time getTravelTime() {
		return TravelTime;
	}

	public void setTravelTime(Time travelTime) {
		TravelTime = travelTime;
	}

	public Time getWaitTime() {
		return WaitTime;
	}

	public void setWaitTime(Time waitTime) {
		WaitTime = waitTime;
	}

	public String getDateOfDeparture1() {
		return DateOfDeparture1.toString();
	}

	public void setDateOfDeparture1(String string) {
		DateOfDeparture1 = string;
	}

	public String getDateOfArrival1() {
		return DateOfArrival1;
	}

	public void setDateOfArrival1(String dateOfArrival) {
		DateOfArrival1 = dateOfArrival;
	}

	public void setFlightID(String flightID) {
		FlightID = flightID;
	}

	public String getFlightID() {
		return FlightID;
	}

	public String getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(String dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

}
