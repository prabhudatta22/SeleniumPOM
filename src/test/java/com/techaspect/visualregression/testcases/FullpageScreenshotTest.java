/**
 ******************************************************************************
 * 							  	TA Digital Visual Regression Tool
 *  										CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.techaspect.visualregression.testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.framework.setup.TestSetUp;
import com.framework.testutils.DriverManager;
import com.framework.testutils.ScreenCaptureUtility;
import com.framework.testutils.TestUtils;
import com.visualregression.pageobjects.ExperionPage;

/**
 * The Class FullpageScreenshotTest.
 * 
 * @author maruthip
 */
public class FullpageScreenshotTest extends TestSetUp {

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
	public void testFullScreenshot(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		DriverManager.getDriver().get(data.get("url"));
		new ScreenCaptureUtility().shutterbug_fullPage_snapshot_stcikyHeader(DriverManager.getDriver(),
				data.get("name"));
		DriverManager.getDriver().get(data.get("targetUrl"));
		new ScreenCaptureUtility().shutterbug_fullPage_snapshot_stcikyHeader(DriverManager.getDriver(),
				data.get("name") + "_target");

		BufferedImage imgBaseline = ImageIO
				.read(new File(System.getProperty("user.dir") + "/Screenshots/" + data.get("name") + ".png"));

		BufferedImage imgtarget = ImageIO.read(
				new File(System.getProperty("user.dir") + "/Screenshots/" + data.get("name") + "_target" + ".png"));

		String diffImagePath = System.getProperty("user.dir") + "/Screenshots/" + data.get("name") + "_DiffImage_"
				+ ".png";

		new ScreenCaptureUtility().shutterbug_CreateDiff_snapshot(DriverManager.getDriver(), imgtarget, imgBaseline,
				diffImagePath, 10);

		DriverManager.getDriver().close();
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
	public void testExperionFullScreenshot(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		DriverManager.getDriver().get(data.get("url"));
		new ScreenCaptureUtility().shutterbug_fullPage_snapshot_stcikyHeader(DriverManager.getDriver(),
				data.get("name") + "_Experion");
		DriverManager.getDriver().get(data.get("targetUrl"));
		new ScreenCaptureUtility().shutterbug_fullPage_snapshot_stcikyHeader(DriverManager.getDriver(),
				data.get("name") + "_target_Experion");

		BufferedImage imgBaseline = ImageIO
				.read(new File(System.getProperty("user.dir") + "/Screenshots/" + data.get("name") + "_Experion.png"));

		BufferedImage imgtarget = ImageIO.read(new File(
				System.getProperty("user.dir") + "/Screenshots/" + data.get("name") + "_target" + "_Experion.png"));

		String diffImagePath = System.getProperty("user.dir") + "/Screenshots/" + data.get("name")
				+ "_DiffImage_Experion_" + ".png";

		new ScreenCaptureUtility().shutterbug_CreateDiff_snapshot(DriverManager.getDriver(), imgtarget, imgBaseline,
				diffImagePath, 10);

		DriverManager.getDriver().close();
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
	public void testExperionSectionalScreen(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		DriverManager.getDriver().get(data.get("url"));

		ExperionPage exp = new ExperionPage();
		exp.getTextContentDetails(data, data.get("Content Heading").trim());

		DriverManager.getDriver().get(data.get("targetUrl"));
		exp.getTextContentDetails(data, data.get("Content Heading").trim() + "_target");

		BufferedImage imgBaseline = ImageIO.read(new File(System.getProperty("user.dir") + "/Screenshots/"
				+ data.get("Content Heading").trim() + "_croppedPic_.png"));

		BufferedImage imgtarget = ImageIO.read(new File(System.getProperty("user.dir") + "/Screenshots/"
				+ data.get("Content Heading").trim() + "_target" + "_croppedPic_.png"));

		String diffImagePath = System.getProperty("user.dir") + "/Screenshots/" + data.get("Content Heading").trim()
				+ "_DiffImage_Experion_croppedPic" + ".png";

		new ScreenCaptureUtility().shutterbug_CreateDiff_snapshot(DriverManager.getDriver(), imgtarget, imgBaseline,
				diffImagePath, 10);

		DriverManager.getDriver().close();
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
}
