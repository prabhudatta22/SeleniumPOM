package com.framework.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Prabhudatta.C
 *
 */

public class DBUtil {

	private static final Properties queries = new Properties();
	static {
		try {
			queries.loadFromXML(new FileInputStream(
					new File(System.getProperty(Constants.ROOT_DIR) + "/src/test/resources/Queries/sql.xml")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getNamedQuery(String queryName) {
		return queries.getProperty(queryName);
	}

}
