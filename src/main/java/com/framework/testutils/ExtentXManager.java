/**
 * The ExtentManager class is used to create the report file
 *
 * @author Prabhudatta.c
 * @version 1.0
 * 
 */

package com.framework.testutils;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.framework.setup.TestSetUp;

public class ExtentXManager {

	private ExtentXManager() {
	}

	private static ExtentXReporter extentx;
	private static String filePath = "./extentreport.html";

	public static ExtentXReporter getExtent() {
		if (extentx != null) {
			return extentx;
		} else {
			extentx = new ExtentXReporter(TestSetUp.configProperty.get("extentServer").toString());
			extentx.config().setProjectName("Timesheet Revamp");
			extentx.config().setReportName("Timsheet Revamp Regression");
			extentx.config()
					.setServerUrl("http://" + TestSetUp.configProperty.get("extentServer").toString() + ":1337");
			return extentx;
		}
	}

	public static ExtentHtmlReporter getHtmlReporter() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.setAppendExisting(false);
		htmlReporter
				.loadXMLConfig(System.getProperty("user.dir") + "/src/test/resources/ReportConfig/ReportsConfig.xml");
		return htmlReporter;
	}

}
