package com.ssdi.POJO;

import java.sql.Time;

public class flightBean {
	
	private String source;
	private String destination;
	private String airlines;
	private String startDate;
	
	private String endDate;
	private String FlightID;
	private String Price;
	private int vacancies = 0, Stops;
	private String departureTime;
	private String arrivalTime;
	private Time TravelTime, WaitTime;
	private String DateOfDeparture;
	private String DateOfArrival;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
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
	public String getAirlines() {
		return airlines;
	}
	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String string) {
		Price = string;
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
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String string) {
		this.departureTime = string;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
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
	public String getDateOfDeparture() {
		return DateOfDeparture.toString();
	}
	public void setDateOfDeparture(String string) {
		DateOfDeparture = string;
	}
	public String getDateOfArrival() {
		return DateOfArrival;
	}
	public void setDateOfArrival(String dateOfArrival) {
		DateOfArrival = dateOfArrival;
	}
	
	public void setFlightID(String flightID) {
		FlightID = flightID;
	}
	public String getFlightID() {
		return FlightID;
	}
}
