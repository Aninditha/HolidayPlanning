package com.ssdi.util;

public class TestConnection implements IConnectionData {
	
	// Driver
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// Database URL
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ssdi_test?autoReconnect=true&useSSL=false";
	// Database credentials
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	@Override
	public String getDriver() {
		return JDBC_DRIVER;
	}

	@Override
	public String getDBURL() {
		return DB_URL;
	}

	@Override
	public String getUserName() {
		return USERNAME;
	}

	@Override
	public String getPassword() {
		return PASSWORD;
	}
}
