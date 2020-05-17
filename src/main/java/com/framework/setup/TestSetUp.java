/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.setup;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.ApplicationContext;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.framework.testutils.ConfigProperties;
import com.framework.testutils.Constants;
import com.framework.testutils.DriverFactory;
import com.framework.testutils.DriverManager;
import com.framework.testutils.ExcelReader;
import com.framework.testutils.ExtentManager;
import com.framework.testutils.ExtentXManager;
import com.framework.testutils.ScreenCaptureUtility;
import com.framework.testutils.TestUtils;

import io.restassured.specification.RequestSpecification;

public class TestSetUp {

	public static Properties configProperty;
	public static ConfigProperties testEnvironment;
	public static ExtentReports extent;
	public static ExtentXReporter extentx;
	public static ThreadLocal<ExtentTest> classLevelExtentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testCaseLogger = new ThreadLocal<ExtentTest>();
	public static Logger appLogs = Logger.getLogger("devpinoyLogger");
	public static RequestSpecification requestSpec;
	public static RequestSpecification requestSpecCreate;
	public static ExcelReader excel = null;
	public static String deviceCheck = "false";

	// the below variables are for portable device view port settings
	public static String viewportX = "";
	public static String viewportY = "";

	public static ApplicationContext context = null;

	public ApplicationContext getContext() {
		return context;
	}

	@BeforeSuite(alwaysRun = true)
	public synchronized void beforeSuite() throws Exception {

		viewportX = System.getProperty("x");
		viewportY = System.getProperty("y");

		TestUtils.moveReportsToArchiveFolder();
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(new File(
					System.getProperty(Constants.ROOT_DIR) + "/src/test/resources/PropertyFiles/config.properties"));

			configProperty = new Properties();
			configProperty.load(fi);

		} catch (FileNotFoundException e) {
			appLogs.debug("config.properties file is not found. " + e);

		} finally {
			if (fi != null) {
				fi.close();
			}
		}

		try {
			cleanFolder("Screenshots");
		} catch (Exception e) {
		}

		extent = ExtentManager.getExtent();
		if (configProperty.get("extentX").toString().equalsIgnoreCase("true")) {
			extentx = ExtentXManager.getExtent();
		}
		if ((this.getClass().getName().contains("API")) || (this.getClass().getName().contains("api"))) {

			ConfigFactory.setProperty("environment", "qa");
			testEnvironment = ConfigFactory.create(ConfigProperties.class);
			excel = new ExcelReader(
					System.getProperty(Constants.ROOT_DIR) + "/src/test/resources/testData/APIsimple.xlsx");
			// extent = ExtentManager.getExtent();
		} else {

			if (configProperty.getProperty("isVisualReg").trim().equalsIgnoreCase("true")) {
				try {
					cleanFolder("src/test/resources/images/diffImages");
				} catch (Exception e) {
					appLogs.debug("Could not delete diffImages folder data");
					System.out.println("Could not delete diffImages folder data");
				}
				try {
					cleanFolder("src/test/resources/images/screenshots");

				} catch (Exception e) {
					appLogs.debug("Could not delete screenshot folder data");
					System.out.println("Could not delete screenshot folder data");
				}
				excel = new ExcelReader(System.getProperty(Constants.ROOT_DIR)
						+ "/src/test/resources/testData/VisualRegressionTestData.xlsx");
				System.out.println("Rows are:- " + excel.getRowCount("TestCases"));
			} else {
				// extent = ExtentManager.getExtent();
				try {
					cleanFolder("Download");
				} catch (Exception e) {
				}
				excel = new ExcelReader(
						System.getProperty(Constants.ROOT_DIR) + "/src/test/resources/testData/simple.xlsx");
			}

		}
	}

	public static RequestSpecification setRequestSpec() {
		return given().auth().basic(testEnvironment.getSecretKey(), "").when();
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		/* Extent Reporting */
		if (configProperty.get("extentX").toString().equalsIgnoreCase("true")) {
			extent.attachReporter(extentx);
		}
		ExtentTest parent = extent.createTest(getClass().getSimpleName());
		classLevelExtentTest.set(parent);
		testCaseLogger.set(parent);
	}

	@BeforeMethod(alwaysRun = true)
	public synchronized void beforeMethod(Method method) throws MalformedURLException, InterruptedException {
		appLogs.debug("Starting execution of test case: " + method.getName());
		System.out.println("Starting execution of test case: " + method.getName());
		System.out.println(configProperty.getProperty("browser"));
		WebDriver driver = null;
		System.out.println(this.getClass().getName());
		if (!(// (this.getClass().getName().contains("API")) &&
				// (this.getClass().getName().contains("api"))&&
		(this.getClass().getName().contains("jmeter")) && (this.getClass().getName().contains("performance")))) {
			if (driver == null) {
				DriverFactory.createDriverInstance(configProperty.getProperty("browser"));
			}
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result, ITestContext context, Method method) throws IOException {

		extent.flush();
		DriverFactory.destroyDriver();
	}

	@AfterClass
	public void afterClass() {
		/* After Class code comes here. */
		// extent.flush();
		DriverFactory.destroyDriver();
	}

	@AfterTest
	public void afterTest() {
		// extent.flush();
		DriverFactory.destroyDriver();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {

		if (DriverManager.getDriver() != null) {
			/*
			 * DriverManager.getDriver().navigate() .to(
			 * "http://localhost:5566/selenium-server/driver/?cmd=shutDownSeleniumServer" );
			 */
			DriverManager.getDriver().quit();
		}
		// extent.flush();

	}

	public void navigateToURL(String url) {
		DriverManager.getDriver().navigate().to(url);
	}

	public static ExtentTest extentLogger() {
		return testCaseLogger.get();
	}

	public void assignAuthor(String authorName) {
		extentLogger().assignAuthor(authorName);
	}

	public void assignCategory(String category) {
		extentLogger().assignCategory(category);
	}

	public void navigateToBaseURL() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
		DriverManager.getDriver().navigate().to(configProperty.getProperty("url"));
		appLogs.debug("Navigating to BaseURL");
	}

	public void initializeDriver() throws MalformedURLException, InterruptedException {
		DriverFactory.createDriverInstance(configProperty.getProperty("browser"));
		DriverManager.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		DriverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * @param usename
	 * @param password
	 * @return
	 */
	public Hashtable<String, String> prepareTestData(String usename, String password) {
		Hashtable<String, String> data = new Hashtable<>();
		data.put("Username", usename);
		data.put("password", password);
		return data;
	}

	public void getBaselineImage(String pageName) throws IOException {
		new ScreenCaptureUtility().takeBaselineScreenShot(DriverManager.getDriver(), pageName);

	}

	public void getCurrentURLImage(String pageName) throws IOException {
		new ScreenCaptureUtility().takeScreenShot(DriverManager.getDriver(), pageName);
	}

	public void getElementScreenShot(String element, String name) throws IOException {
		WebElement ele = DriverManager.getDriver().findElement(By.xpath(element));
		new ScreenCaptureUtility().takeElementScreenShot(DriverManager.getDriver(), name, ele);
	}

	public void getBaselineElementScreenShot(String element, String name) throws IOException {
		WebElement ele = DriverManager.getDriver().findElement(By.xpath(element));
		new ScreenCaptureUtility().takeBaseLineElementScreenShot(DriverManager.getDriver(), name, ele);
	}

	/**
	 * Delete Folder contents.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void cleanFolder(String path) throws IOException {
		appLogs.debug("cleaning the folder " + path);
		appLogs.debug("Folder absolute path " + System.getProperty("user.dir") + File.separator + path);
		System.out.println("Folder absolute path " + System.getProperty("user.dir") + File.separator + path);
		FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + File.separator + path));
		appLogs.debug("Deleted all files in the folder " + System.getProperty("user.dir") + File.separator + path);
	}

}
