package com.ssdi.TestCases;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;
import com.ssdi.util.ConnectionUtil;
import com.ssdi.util.IConnectionData;
import com.ssdi.util.TestConnection;

public class testCountrySearch {

	private static databaseFactory factory;
	private ServicesDao serviceDao;
	
	@BeforeClass
	public static void myInitialization() throws Exception {
		factory = databaseFactory.getInstance("test");
	}
	
	@Before
	public void setUp() throws Exception {
		serviceDao = factory.createServiceDao();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test(){
		IConnectionData connectionData = new TestConnection();
		Connection testConnection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String selectSql = "select * from region where country_ID = 'c1';";
		String insertCountry = "insert into country values ('c1', 'India');";
		String insertRegion = "insert into region values ('c1', 'r1', 'Delhi', 'This is the capital of India');";
		
		/* Invoke function under test */
		try {
			testConnection = ConnectionUtil.getConnection(connectionData);
			
			statement = testConnection.createStatement();
		    statement.executeUpdate(insertCountry);
		    statement.executeUpdate(insertRegion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* Invoke function under test */
		serviceDao.searchRegions("India");
		
		/* Verify that database was correctly fetched */ 
		try {
			statement = testConnection.createStatement();
			resultSet = statement.executeQuery(selectSql);
			
			resultSet.next();
			assertEquals(resultSet.getString("region_ID"), "r1");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
