package com.ssdi.TestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;

public class testFlightSearch {

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
		serviceDao.searchFlights("nyc", "maa", "2016-04-30", "", 2);
	}
}
