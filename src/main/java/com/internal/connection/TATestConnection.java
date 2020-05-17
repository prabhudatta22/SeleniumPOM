/**
 * 
 */
package com.internal.connection;

import java.sql.Connection;

/**
 * @author Prabhudatta.C
 *
 */
public interface TATestConnection {

	public Connection getConnection();

	public Connection getConnection(String dbURL, String user_name, String password);

}
