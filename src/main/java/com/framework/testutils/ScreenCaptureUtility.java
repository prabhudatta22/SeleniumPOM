/**
 ******************************************************************************
 * 							  	TA Digital Visual Regression Tool
 *  										CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.testutils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.core.Snapshot;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.framework.setup.TestSetUp;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * The Class ScreenCaptureUtility.
 * 
 * @author maruthip
 */
public class ScreenCaptureUtility {

	/**
	 * Take baseline screen shot.
	 *
	 * @param driver
	 *            the driver
	 * @param name
	 *            the name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void takeBaselineScreenShot(WebDriver driver, String name) throws IOException {
		Screenshot screen = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		BufferedImage bi = screen.getImage();
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/images/baseline/" + name + ".png");
		ImageIO.write(bi, "png", file);

	}

	/**
	 * Take screen shot.
	 *
	 * @param driver
	 *            the driver
	 * @param name
	 *            the name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String takeScreenShot(WebDriver driver, String name) throws IOException {
		Screenshot screen = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		BufferedImage bi = screen.getImage();
		File file = new File(
				System.getProperty("user.dir") + "/src/test/resources/images/screenshots/" + name + ".png");
		ImageIO.write(bi, "png", file);
		return file.getAbsolutePath();

	}

	/**
	 * Take element screen shot.
	 *
	 * @param driver
	 *            the driver
	 * @param name
	 *            the name
	 * @param element
	 *            the element
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void takeElementScreenShot(WebDriver driver, String name, WebElement element) throws IOException {
		Screenshot screen = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element);
		BufferedImage bi = screen.getImage();
		File file = new File(
				System.getProperty("user.dir") + "/src/test/resources/images/screenshots/" + name + ".png");
		ImageIO.write(bi, "png", file);

	}

	public void takeBaseLineElementScreenShot(WebDriver driver, String name, WebElement element) throws IOException {
		Screenshot screen = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element);
		BufferedImage bi = screen.getImage();
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/images/baseline/" + name + ".png");
		ImageIO.write(bi, "png", file);

	}

	/**
	 * Are images equal.
	 *
	 * @param baseline
	 *            the baseline
	 * @param screenshot
	 *            the screenshot
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public boolean areImagesEqual(String baseline, String screenshot) throws IOException {
		BufferedImage imgBaseline = null;
		BufferedImage imgScreenshot = null;

		imgBaseline = ImageIO.read(
				new File(System.getProperty("user.dir") + "/src/test/resources/images/baseline/" + baseline + ".png"));
		imgScreenshot = ImageIO.read(new File(
				System.getProperty("user.dir") + "/src/test/resources/images/screenshots/" + screenshot + ".png"));

		ImageDiff diff = new ImageDiffer().makeDiff(imgBaseline, imgScreenshot);
		boolean isDifferent = diff.hasDiff();

		if (isDifferent) {
			BufferedImage diffImage = diff.getMarkedImage();
			ImageIO.write(diffImage, "png", new File(
					System.getProperty("user.dir") + "/src/test/resources/images/diffImages/" + baseline + ".png"));
		}
		return !isDifferent;
	}

	/**
	 * Highlight element.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 */
	public void highlightElement(WebDriver driver, By by) {
		WebElement ele = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow;');", ele);
	}

	/**
	 * Highlight one section.
	 *
	 * @param driver
	 *            the driver
	 * @param WebElement
	 *            the element
	 * @param Stirng
	 *            the element text
	 */

	public void shutterbug_highLight_section(WebDriver driver, WebElement element, String msg, String name) {
		Shutterbug.shootPage(driver).highlightWithText(element, msg).withName(name).save();
	}

	/**
	 * Take entire page snapshot
	 *
	 * @param driver
	 *            the driver
	 */

	public void shutterbug_fullPage_snapshot(WebDriver driver) {
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save();
	}

	/**
	 * Takes screenshot of the whole page using Chrome DevTools. This is applicable
	 * for Chrome only. Use this one instead of ScrollStrategy.WHOLE_PAGE if page
	 * has sticky header or any other sticky elements.
	 *
	 * @param driver
	 *            the driver
	 */

	public Snapshot shutterbug_fullPage_snapshot_stcikyHeader(WebDriver driver, String name) {
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, true).withName(name).save();
		return Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, true).withName(name);

	}

	/**
	 * Takes screenshot of the whole page using Chrome DevTools. This is applicable
	 * for Chrome only. Use this one instead of ScrollStrategy.WHOLE_PAGE if page
	 * has sticky header or any other sticky elements.
	 *
	 * @param driver
	 *            the driver
	 */

	public void shutterbug_CreateDiff_snapshot(WebDriver driver, BufferedImage expectedImage, String diffImagePath,
			double threshhold) {
		Shutterbug.shootPage(driver).equalsWithDiff(expectedImage, diffImagePath, threshhold);
	}

	/**
	 * Takes screenshot of the whole page using Chrome DevTools. This is applicable
	 * for Chrome only. Use this one instead of ScrollStrategy.WHOLE_PAGE if page
	 * has sticky header or any other sticky elements.
	 *
	 * @param driver
	 * @param expectedImage
	 * @param actualImage
	 * @param diffImagePath
	 * @param threshhold
	 * @return boolean
	 * 
	 * 
	 */

	public boolean shutterbug_CreateDiff_snapshot(WebDriver driver, BufferedImage expectedImage,
			BufferedImage actualImage, String diffImagePath, double threshhold) {

		ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualImage);
		boolean isDifferent = diff.hasDiff();

		if (isDifferent) {
			BufferedImage diffImage = diff.getMarkedImage();
			try {
				ImageIO.write(diffImage, "png", new File(diffImagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isDifferent; // ImageProcessor.imagesAreEqualsWithDiff(expectedImage,
							// actualImage, diffImagePath, threshhold);
	}

	/**
	 * Are images equal.
	 *
	 * @param baseline
	 *            the baseline
	 * @param screenshot
	 *            the screenshot
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public boolean areScreenshotsEqual(String baseline, String target) throws IOException {
		BufferedImage imgBaseline = null;
		BufferedImage imgScreenshot = null;

		imgBaseline = ImageIO.read(new File(System.getProperty("user.dir") + "/Screenshots/" + baseline + ".png"));
		imgScreenshot = ImageIO.read(new File(System.getProperty("user.dir") + "/Screenshots/" + target + ".png"));

		ImageDiff diff = new ImageDiffer().makeDiff(imgBaseline, imgScreenshot);
		boolean isDifferent = diff.hasDiff();

		if (isDifferent) {
			BufferedImage diffImage = diff.getMarkedImage();
			ImageIO.write(diffImage, "png",
					new File(System.getProperty("user.dir") + "/Screenshots/" + baseline + "_Diff.png"));
		}
		return !isDifferent;
	}

	/**
	 * This method will take screenshot for any step
	 * 
	 * @param WebElement
	 *            pageElement
	 * @param String
	 *            logMessage
	 * @return void
	 */

	public String captureFullPageScreenshot(WebDriver driver, String screenShotName) {

		String dest = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + screenShotName
				+ ".png";
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, true).withName(screenShotName).save();

		try {
			TestSetUp.testCaseLogger.get().addScreenCaptureFromPath(new File(dest).getAbsolutePath());
		} catch (Exception e) {
		}
		return dest;
	}
}
