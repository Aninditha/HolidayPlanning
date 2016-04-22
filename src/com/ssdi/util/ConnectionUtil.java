package com.ssdi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection(IConnectionData connectionData) {
		Connection connection = null;
		try {
			// Register JDBC driver
			Class.forName(connectionData.getDriver());

			// Open a connection
			connection = DriverManager.getConnection(connectionData.getDBURL(), connectionData.getUserName(),
					connectionData.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
