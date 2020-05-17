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

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.framework.setup.TestSetUp;
import com.framework.testutils.DriverManager;
import com.framework.testutils.ScreenCaptureUtility;
import com.framework.testutils.TestUtils;

/**
 * The Class EndToEnd.
 * 
 * @author maruthip
 */
public class EndToEnd extends TestSetUp {

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
	public void EndToEndTest(Hashtable<String, String> data, ITestContext context) throws IOException {

		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		// BaseLine Images
		// getBaselineImage(data, context);
		// Target Images
		// getCurrentURLImage(data, context);
		Assert.assertEquals(new ScreenCaptureUtility().areImagesEqual(data.get("name"), data.get("name")), true,
				data.get("targetUrl") + " is different from the baseline image. Please refer to above screenshot.    ");
	}

	public void getCurrentURLImage(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		DriverManager.getDriver().get(data.get("targetUrl"));
		new ScreenCaptureUtility().takeScreenShot(DriverManager.getDriver(), data.get("name"));
	}

	public void getBaselineImage(Hashtable<String, String> data, ITestContext context) throws IOException {
		context.getCurrentXmlTest().addParameter("image", data.get("name"));
		DriverManager.getDriver().get(data.get("url"));
		new ScreenCaptureUtility().takeBaselineScreenShot(DriverManager.getDriver(), data.get("name"));
	}
}
