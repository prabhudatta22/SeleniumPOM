/**
 * 
 */
package com.visualregression.pageobjects;

import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.framework.testutils.Constants;
import com.framework.testutils.DriverManager;
import com.framework.testutils.ScreenCaptureUtility;
import com.framework.ui.pageobjects.BasePage;

/**
 * @author Prabhudatta.C
 *
 */
public class FunctionalVIRPage extends BasePage {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.framework.ui.pageobjects.BasePage#getPageLoadCondition()
	 */
	@Override
	public ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions
				.visibilityOf(DriverManager.getDriver().findElement(By.className(Constants.OR_FOOTER_DIV2)));
	}

	@SuppressWarnings("unchecked")
	public FunctionalVIRPage open() {
		return (FunctionalVIRPage) openPage(FunctionalVIRPage.class);
	}

	/**
	 * @param data
	 */
	public String getTextContentDetails(Hashtable<String, String> data, String eval) {

		String xpath = ".//*[contains(text(),'" + data.get("ContentHeading").trim() + "')]";
		WebElement contentHeader = null;
		try {
			ExpectedConditions.visibilityOf(DriverManager.getDriver().findElement(By.xpath(xpath)));
			WebElement actualElement = DriverManager.getDriver().findElement(By.xpath(xpath));
			scrollIntoView(actualElement, "Content element");
			try {
				actualElement.click();
			} catch (Exception e) {
			}
			contentHeader = DriverManager.getDriver().findElement(By.xpath(xpath + "/ancestor::div[2]"));
			new ScreenCaptureUtility().shutterbug_highLight_section(DriverManager.getDriver(), actualElement,
					data.get("ContentHeading").trim(), "_croppedPic_");
			testCaseLogger.get().addScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/"
					+ data.get("ContentHeading") + eval + "_croppedPic_.png");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return contentHeader.getText();
	}

	/**
	 * @param data
	 */
	public void navigateToURL(String url) {
		DriverManager.getDriver().get(url);
		appLogs.debug("Navigating to BaseURL");

	}

	public Set removeDuplicates(String[] paragraphs) {
		Set<String> set = new LinkedHashSet<String>();
		for (String p : paragraphs) {
			set.add(p);
		}
		return set;
	}

	/**
	 * @param prodContent
	 * @param currentContent
	 */
	public boolean comapreTextContent(String expected, String actual) {

		String[] exparr = expected.split("\n");
		String[] actarr = actual.split("\n");

		if (exparr.length > actarr.length) {
			testCaseLogger.get().info("Expected content has more characters than actual content");
		}
		if (exparr.length < actarr.length) {
			testCaseLogger.get().info("Actual content has more characters than Expected content");
		} else {
			testCaseLogger.get().info("Both Actual and Expected content length is same");
		}

		testCaseLogger.get().info("Expected content length is " + exparr.length);
		testCaseLogger.get().info("Actual content length is " + actarr.length);

		boolean isSame = false;
		try {
			for (int i = 0; i < exparr.length; i++) {
				if (exparr[i].contentEquals(actarr[i])) {
					isSame = true;
				} else {
					testCaseLogger.get().fail(exparr[i] + " is not same as  " + actarr[i]);
					isSame = false;
					break;
				}
			}
		} catch (Exception e) {
			testCaseLogger.get().info(e.getMessage());
		}
		return isSame;
	}

	public boolean compareContent(String expected, String actual2) {

		boolean isSame = true;

		String stg4Arr[] = expected.split("\n");
		String acArr[] = actual2.split("\n");

		int lineNum = 1;
		for (int i = 0; i < acArr.length; i++) {
			try {
				while (stg4Arr[i] != null || acArr[i] != null) {
					if (stg4Arr[i] == null || acArr[i] == null) {
						isSame = false;
						testCaseLogger.get().info("Expected content at line no" + i + "  is >> " + stg4Arr[i]);
						testCaseLogger.get().info("Actual content line no" + i + "  is >> " + acArr[i]);
						testCaseLogger.get().fail("Two files have different content. They differ at line " + lineNum);
						i++;
						break;
					} else if (!stg4Arr[i].equalsIgnoreCase(acArr[i])) {
						isSame = false;
						testCaseLogger.get().info("Expected content at line no" + i + "  is >> " + stg4Arr[i]);
						testCaseLogger.get().info("Actual content line no" + i + "  is >> " + acArr[i]);
						testCaseLogger.get().fail("Two files have different content. They differ at line " + lineNum);
						i++;
						break;
					}

					lineNum++;
					break;
				}

			} catch (Exception e) {
				lineNum++;
				i++;
			}
		}

		if (isSame) {
			testCaseLogger.get().pass("Two sites/sections have same content.");
		}

		return isSame;
	}

}
