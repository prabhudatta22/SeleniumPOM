package com.framework.testutils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author Prabhudatta.C
 *
 */
public abstract class QueryUtil {

	public static StringBuilder setBigDecimal(StringBuilder query, String parameterName, BigDecimal value)
			throws SQLException {
		validate(query, parameterName, value);
		String pName = ":" + parameterName.trim();
		query = replace(query, pName, value.toPlainString());
		return query;
	}

	public static StringBuilder setInt(StringBuilder query, String parameterName, int value) throws SQLException {
		validate(query, parameterName, "value");
		String pName = ":" + parameterName.trim();
		query = replace(query, pName, value + "");
		return query;
	}

	public static StringBuilder setString(StringBuilder query, String parameterName, String value) throws SQLException {

		validate(query, parameterName, value);

		String pName = ":" + parameterName.trim();
		String pValue = "'" + value + "'";
		query = replace(query, pName, pValue);

		return query;
	}

	public static StringBuilder setStringInList(StringBuilder query, String parameterName, List<String> values)
			throws SQLException {

		validate(query, parameterName, values);
		String pName = ":" + parameterName.trim();
		StringBuilder pValues = new StringBuilder();

		for (String value : values) {
			pValues.append("'");
			pValues.append(value);
			pValues.append("',");
		}
		pValues.deleteCharAt(pValues.length() - 1);
		query = replace(query, pName, pValues.toString());

		return query;
	}

	private static StringBuilder replace(StringBuilder query, String target, String replacement) throws SQLException {

		boolean foundMatch = false;
		int startIndex = 0;
		int targetLength = target.length();

		while ((startIndex = query.indexOf(target, startIndex)) != -1) {

			query = query.replace(startIndex, startIndex + targetLength, replacement);

			if (!foundMatch) {
				foundMatch = true;
			}
		}

		if (!foundMatch) {
			throw new SQLException("String " + target + " not found in query " + query);
		}

		return query;
	}

	private static void validate(StringBuilder query, String parameterName, Object value) throws SQLException {

		if (query == null || parameterName == null || value == null || parameterName.trim().equals("")
				|| query.length() < 10) {
			throw new SQLException("Invalid SQL Parameters parameterName " + parameterName + " , value " + value
					+ " or SQL query " + query);
		}
	}

	private static void validate(StringBuilder query, String parameterName, List<String> value) throws SQLException {

		if (query == null || parameterName == null || value == null || parameterName.trim().equals("")
				|| value.size() == 0 || query.length() < 10) {
			throw new SQLException("Invalid SQL Parameters, parameterName " + parameterName + " , value " + value
					+ " or SQL query " + query);
		}
	}

}
