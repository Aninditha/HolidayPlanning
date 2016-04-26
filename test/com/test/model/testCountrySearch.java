package com.test.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ssdi.POJO.taBean;
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
	public void test() {

		IConnectionData connectionData = new TestConnection();
		Connection testConnection = null;
		Statement statement = null;

		String insertCountry = "insert into country values ('c1', 'India');";
		String insertRegion = "insert into region values ('c1', 'r1', 'Delhi', 'This is the capital of India');";

		/* Invoke function under test database */
		try {
			testConnection = ConnectionUtil.getConnection(connectionData);

			statement = testConnection.createStatement();
			statement.executeUpdate(insertCountry);
			statement.executeUpdate(insertRegion);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Invoke function under test */
		ArrayList<taBean> regionList = serviceDao.searchRegions("India");

		/* Verify that database was correctly fetched */

		taBean ta = regionList.get(0);
		assertEquals(ta.getRegionName(), "Delhi");
		assertEquals(ta.getDescription(), "This is the capital of India");

	}
}
