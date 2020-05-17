/**
 * 
 */
package com.visualregression.pageobjects;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.framework.testutils.DriverManager;
import com.framework.testutils.ScreenCaptureUtility;
import com.framework.ui.pageobjects.BasePage;

/**
 * @author Prabhudatta.C
 *
 */
public class ExperionPage extends BasePage {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.visualregression.pageobjects.BasePage#getPageLoadCondition(
	 * )
	 */
	@Override
	public ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public ExperionPage open() {
		return (ExperionPage) openPage(ExperionPage.class);
	}

	/**
	 * @param data
	 */
	public String getTextContentDetails(Hashtable<String, String> data, String name) {

		// String xpath = ".//*[contains(text(),'" + data.get("Content
		// Heading").trim() + "')]";
		WebElement contentHeader = null;
		try {
			ExpectedConditions.visibilityOf(DriverManager.getDriver().findElement(By.id(data.get("ContentHeading"))));
			WebElement actualElement = DriverManager.getDriver().findElement(By.id(data.get("ContentHeading")));
			scrollIntoView(actualElement, "Content element");
			testCaseLogger.get().info("scroll to view Content element");
			// contentHeader =
			// DriverManager.getDriver().findElement(By.xpath(xpath +
			// "/ancestor::div[2]"));
			new ScreenCaptureUtility().shutterbug_highLight_section(DriverManager.getDriver(), actualElement,
					data.get("ContentHeading").trim(), name + "_croppedPic_");
			testCaseLogger.get().addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/Screenshots/" + data.get("name") + "_croppedPic_.png");

			testCaseLogger.get().info("The entire content of the element shows up as >>> ");

			/*
			 * System.out.println(
			 * "####################################################################"
			 * ); WebElement entirecontent =
			 * DriverManager.getDriver().findElement(By.xpath(xpath +
			 * "/ancestor::*")); System.out.println(entirecontent.getText());
			 * System.out.println(
			 * "####################################################################"
			 * );
			 * 
			 * List<WebElement> childElements =
			 * contentHeader.findElements(By.xpath(".//*")); List<WebElement>
			 * ImmideateparentElements =
			 * contentHeader.findElements(By.xpath("./../..")); List<WebElement>
			 * ImmideateparentElementsGroup =
			 * contentHeader.findElements(By.xpath("./../../.."));
			 * List<WebElement> ImmideateparentElementsGroupDiv =
			 * contentHeader.findElements(By.xpath("./../../../.."));
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
