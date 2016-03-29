package com.ssdi.POJO;

import java.sql.Date;
import java.sql.Time;

public class flightBean {
	
	private String source;
	private String destination;
	private String airlines;
	private String startDate;
	private String endDate;
	private double Price = 0;
	private int vacancies = 0, Stops;
	private Time departureTime;
	private Time arrivalTime, TravelTime, WaitTime;
	private Date DateOfDeparture, DateOfArrival;
	
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
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
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
	public Time getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}
	public Time getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
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
	public void setDateOfDeparture(Date dateOfDeparture) {
		DateOfDeparture = dateOfDeparture;
	}
	public Date getDateOfArrival() {
		return DateOfArrival;
	}
	public void setDateOfArrival(Date dateOfArrival) {
		DateOfArrival = dateOfArrival;
	}
}
