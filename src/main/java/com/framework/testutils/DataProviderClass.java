/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.testutils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.framework.setup.TestSetUp;

public class DataProviderClass extends TestSetUp {

	/* Use this dataprovider when we have one excel sheet for one test case. */
	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		/*
		 * 1. Sheet name same as the test case name 2. Returns the row count in
		 * the sheet 3. Returns the column count in the sheet
		 */
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		/*
		 * We are using rows-1 as the first row in the excel will be header. We
		 * are using number of columns as 1 because we will be using Hashtable.
		 */
		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table = null;
		/* Creating one hashtable for each data row */
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}
		}
		return data;
	}

	/*
	 * Use this dataprovider when we have all test cases data in one excel
	 * sheet.
	 */
	@DataProvider(name = "dpone")
	public Object[][] getDatas(Method m) {

		String sheetName = testEnvironment.getSheetName();
		int rows = excel.getRowCount(sheetName);
		String testName = m.getName();
		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {
			String testCaseName = excel.getCellData(sheetName, 0, testCaseRowNum);
			appLogs.debug("TestCase name in excel--> " + testCaseName);
			if (testCaseName.equalsIgnoreCase(testName))
				break;
		} // Checking total rows in test case
		int dataStartRowNum = testCaseRowNum + 2;// dataStartRowNum=8
		int testRows = 0;
		while (!excel.getCellData(sheetName, 0, dataStartRowNum + testRows).equals("")) {
			testRows++;
		} // Checking total cols in test case
		appLogs.debug("Total no of rows: " + testRows);
		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;
		while (!excel.getCellData(sheetName, testCols, colStartColNum).equals("")) {
			testCols++;
		}

		Object[][] data = new Object[testRows][1];
		int i = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < testCols; cNum++) {
				String colName = excel.getCellData(sheetName, cNum, colStartColNum);
				String testData = excel.getCellData(sheetName, cNum, rNum);
				table.put(colName, testData);
			}
			data[i][0] = table;
			i++;
		}
		return data;
	}
}