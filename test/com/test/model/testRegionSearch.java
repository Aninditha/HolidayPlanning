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

public class testRegionSearch {

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
		String insertAttractions = "insert into attractions values('r1', 'a1', 'Red Fort', "
				+ "'This is the residence of mughal emperor');";

		/* Invoke function under test */
		try {
			testConnection = ConnectionUtil.getConnection(connectionData);

			statement = testConnection.createStatement();
			statement.executeUpdate(insertAttractions);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Invoke function under test */
		ArrayList<taBean> attractionsList = serviceDao.searchAttractions("Delhi");

		taBean ta = attractionsList.get(0);
		assertEquals(ta.getAttractionName(), "Red Fort");
		assertEquals(ta.getDescription(), "This is the residence of mughal emperor");
	}
}