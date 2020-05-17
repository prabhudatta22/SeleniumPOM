/**
 ******************************************************************************
 * 							  	TA Digital Visual Regression Tool
 *  										CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.techaspect.visualregression.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.framework.setup.TestSetUp;
import com.framework.testutils.Constants;
import com.framework.testutils.DriverManager;
import com.framework.testutils.ScreenCaptureUtility;
import com.framework.testutils.TestUtils;

/**
 * The Class EndToEnd.
 * 
 * @author maruthip
 */
public class AllInOne extends TestSetUp {

	/**
	 * Prepare baseline.
	 *
	 * @param data
	 *            the data
	 * @param context
	 *            the context
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void prepareBaseline(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		testCaseLogger.get().assignAuthor("Prabhudatta");
		testCaseLogger.get().assignCategory("Visual Regression");
		DriverManager.getDriver().get(data.get("url"));
		new ScreenCaptureUtility().takeBaselineScreenShot(DriverManager.getDriver(), data.get("name"));
	}

	/**
	 * Test full page screenshot.
	 *
	 * @param data
	 *            the data
	 * @param context
	 *            the context
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testFullPageScreenshot(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		DriverManager.getDriver().get(data.get("url"));
		new ScreenCaptureUtility().takeScreenShot(DriverManager.getDriver(), data.get("name"));
	}

	/**
	 * Test element screen shot.
	 *
	 * @param data
	 *            the data
	 * @param context
	 *            the context
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testElementScreenShot(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		DriverManager.getDriver().get(data.get("url"));
		WebElement ele = DriverManager.getDriver().findElement(By.xpath(Constants.OR_LOGO));
		new ScreenCaptureUtility().takeElementScreenShot(DriverManager.getDriver(), data.get("name"), ele);
	}

	/**
	 * Test compare images.
	 *
	 * @param data
	 *            the data
	 * @param context
	 *            the context
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testCompareImages(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		DriverManager.getDriver().get(data.get("url"));
		new ScreenCaptureUtility().takeScreenShot(DriverManager.getDriver(), data.get("name"));
		Assert.assertEquals(new ScreenCaptureUtility().areImagesEqual(data.get("name"), data.get("name")), true,
				data.get("url") + " is different from the baseline image. Please refer to above screenshot.    ");
	}

	/**
	 * Test compare images to fail.
	 *
	 * @param data
	 *            the data
	 * @param context
	 *            the context
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	// This is for my testing purpose only
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testCompareImagesToFail(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		DriverManager.getDriver().get(data.get("url"));
		new ScreenCaptureUtility().highlightElement(DriverManager.getDriver(), By.xpath(Constants.OR_LOGO));
		new ScreenCaptureUtility().highlightElement(DriverManager.getDriver(), By.linkText(Constants.OR_LOGIN));
		new ScreenCaptureUtility().takeScreenShot(DriverManager.getDriver(), data.get("name"));
		Assert.assertEquals(new ScreenCaptureUtility().areImagesEqual(data.get("name"), data.get("name")), true,
				data.get("url") + " is different from the baseline image. Please refer to above screenshot.    ");
	}

}
