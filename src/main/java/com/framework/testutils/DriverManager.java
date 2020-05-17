/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.testutils;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import com.framework.setup.TestSetUp;

public class DriverManager {

	private DriverManager() {
	}

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/**
	 * This method is for getDriver.
	 */
	public static WebDriver getDriver() {
		return DriverManager.driver.get();
	}

	/**
	 * This method is for setDriver.
	 * 
	 * @param driver
	 */
	public static void setDriver(WebDriver driver) {
		DriverManager.driver.set(driver);
	}

	/**
	 * This method is for maximizing the browser.
	 * 
	 * @param driver
	 */
	public static void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
		if (TestSetUp.configProperty.get("isPortable").toString().equalsIgnoreCase("true")) {

			String viewX = StringUtils.isEmpty(TestSetUp.viewportX)
					? TestSetUp.configProperty.get("viewportX").toString()
					: TestSetUp.viewportX;
			String viewY = StringUtils.isEmpty(TestSetUp.viewportY)
					? TestSetUp.configProperty.get("viewportY").toString()
					: TestSetUp.viewportY;
			System.out.println("CMD values X " + TestSetUp.viewportX);
			System.out.println("CMD values Y " + TestSetUp.viewportY);

			System.out.println("Prop values X " + TestSetUp.configProperty.get("viewportX"));
			System.out.println("Prop values Y " + TestSetUp.configProperty.get("viewportY"));

			int x = Integer.parseInt(viewX);
			int y = Integer.parseInt(viewY);

			System.out.println("Considered values X " + x);
			System.out.println("Considered values Y " + y);

			driver.manage().window().setSize(new Dimension(x, y));
		}
	}

	/**
	 * This method is for setting implicit wait.
	 * 
	 * @param driver
	 */
	public static void setImplicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	public static void pageLoadTimeout(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}

	public static void tearDown() {
		if (driver != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
