/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							COPYRIGHTS TO TA Digital
 *							
 * *****************************************************************************
 */
package com.techaspect.bdd.tests.runner;

import java.io.File;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import com.aventstack.extentreports.ExtentTest;
import com.cucumber.listener.Reporter;
import com.framework.setup.TestSetUp;

import cucumber.api.CucumberOptions;
//import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
//import utils.Constants;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "json:target/json/output.json" }, features = {
		"src/test/resources/features/" }, glue = { "com.techaspect.bdd.tests.steps" }, plugin = { "pretty",
				"json:target/cucumber.json", "html:target/cucumber",
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, tags = {
						"@BDDTest" }, monochrome = true)

public class RunCuke extends AbstractTestNGCucumberTests {

	@BeforeClass
	@Parameters({ "mobiledevice" })
	public void devicePreCheck(String device) {
		System.out.println("Device status is :" + device);
		System.out.println("Device status before updating..." + TestSetUp.deviceCheck);
		TestSetUp.deviceCheck = device;
	}

	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File("src/test/resources/extentConfig/extent-config.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", "Windows-8");
		Reporter.setTestRunnerOutput("Sample test com.techaspect.bdd.tests.runner output message");
		// Reporter.loadXMLConfig(new
		// File("D:\\AutomationProjects\\CucumberPageObjects\\src\\test\\resources\\extent-config.xml"));
	}
}
