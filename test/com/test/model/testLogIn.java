package com.test.model;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.ssdi.util.ConnectionUtil;
import com.ssdi.util.IConnectionData;
import com.ssdi.util.TestConnection;
import com.ssdi.model.databaseFactory;
import com.ssdi.POJO.userbean;
import com.ssdi.model.ServicesDao;

public class testLogIn {
	
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
		Statement statement = null;
		ResultSet resultSet = null;
		userbean user= new userbean();
		
		user.setUsername("ssdiproject10");
		user.setEmail("project10@ssdi.com");
		user.setPassword("Qwerty123");
		String selectSql = "select * from customerdetails where username = 'project10@ssdi.com' "
				+ "and password = 'Qwerty123'";
		
		testConnection = ConnectionUtil.getConnection(connectionData);
		
		serviceDao.registerUser(user);
		
		/* Invoke function under test */
		assertTrue(serviceDao.logIn("project10@ssdi.com","Qwerty123"));

		/* Verify that database was correctly fetched */ 
		try {
			statement = testConnection.createStatement();
			resultSet = statement.executeQuery(selectSql);
			
			resultSet.next();
			assertEquals(resultSet.getString("Email"), "project10@ssdi.com");
			assertEquals(resultSet.getString("password"), "Qwerty123");
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
