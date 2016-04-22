package com.ssdi.model;

import com.ssdi.util.IConnectionData;
import com.ssdi.util.ProductionConnection;
import com.ssdi.util.TestConnection;

public class databaseFactory {

	@SuppressWarnings("unused")
	private static databaseFactory instance;
	private String environment;

	private databaseFactory(String environment) {
		this.environment = environment;
	}

	public static databaseFactory getInstance(String environment) {
		return instance = new databaseFactory(environment);
	}

	public ServicesDao createServiceDao() {

		IConnectionData connectionData = null;

		if (environment.equals("production")) {
			connectionData = new ProductionConnection();
		} else if (environment.equals("test")) {
			connectionData = new TestConnection();
		}

		return new ServicesDao(connectionData);
	}
}