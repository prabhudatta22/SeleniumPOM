/**
 * 
 */
package com.internal.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Prabhudatta.C
 *
 */
public class TIBAConnection implements TATestConnection {

	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	static final String DB_URL = "jdbc:mariadb://192.168.2.214:3306";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "Tec#890";

	@Override
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection done " + conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.internal.connection.TATestConnection#getConnection(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public Connection getConnection(String dbURL, String user_name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
