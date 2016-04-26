package com.test.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ssdi.POJO.userbean;
import com.ssdi.model.ServicesDao;
import com.ssdi.model.databaseFactory;
import com.ssdi.util.ConnectionUtil;
import com.ssdi.util.IConnectionData;
import com.ssdi.util.TestConnection;

public class testRegisterUser {

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
	public void testRegisterlogin() throws SQLException {
		
		IConnectionData connectionData = new TestConnection();
		Connection testConnection = null;

		userbean user= new userbean();
		
		user.setUsername("ssdiproject10");
		user.setEmail("project10@ssdi.com");
		user.setPassword("Qwerty123");
		
		testConnection = ConnectionUtil.getConnection(connectionData);
		
		user = serviceDao.registerUser(user);
		
		assertEquals(user.getUsername(), "ssdiproject10");
		assertEquals(user.getPassword(), "Qwerty123");
		assertEquals(user.getEmail(), "project10@ssdi.com");
	}
}
