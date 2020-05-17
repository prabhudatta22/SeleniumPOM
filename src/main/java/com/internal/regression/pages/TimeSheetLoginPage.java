/**
 * 
 */
package com.internal.regression.pages;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.framework.testutils.DriverManager;
import com.framework.ui.pageobjects.BasePage;

/**
 * @author Prabhudatta.C
 *
 */
public class TimeSheetLoginPage extends BasePage {

	@FindBy(xpath = PageConstants.USERNAME)
	private WebElement username;

	@FindBy(xpath = PageConstants.USERNAME_NEXT_BUTTON)
	private WebElement usernameNxtBtn;

	@FindBy(xpath = PageConstants.PASSWORD)
	private WebElement passwordInput;

	@FindBy(xpath = PageConstants.SIGNIN_BUTTON)
	private WebElement signInBtn;

	@FindBy(id = PageConstants.SUBMIT_CONTINUE)
	private WebElement submit_Continue;

	@FindBy(id = PageConstants.SUBMIT_NEXT)
	private WebElement submit_next;

	@FindBy(xpath = PageConstants.MY_TEAM_MENU)
	private WebElement myTeamMenu;

	@FindBy(xpath = PageConstants.REPORTS_MENU)
	private WebElement reportsMenu;

	@FindBy(xpath = PageConstants.MISSING_TIMESHEET_REPORTS_SUBMENU)
	private List<WebElement> missingReportDropDown;

	@FindBy(xpath = PageConstants.TIMESHEET_REPORT_SUBMENU)
	private List<WebElement> timeSheetReportDropDown;

	public TimeSheetLoginPage getTimeSeetLoginPage() {

		return (TimeSheetLoginPage) openPage(TimeSheetLoginPage.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.framework.ui.pageobjects.BasePage#getPageLoadCondition()
	 */
	@Override
	public ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(username);
	}

	public void loginValid(Hashtable<String, String> data) {

		TimeSheetLoginPage tlogin = getTimeSeetLoginPage();
		getPageLoadCondition(tlogin.username);
		sendKeys(tlogin.username, data.get("Username"), "Username");
		clickAction(tlogin.usernameNxtBtn, "UserName Next Btn");
		implicitWaitSec(10000);
		waitForPageLoad(getPageLoadCondition(tlogin.passwordInput));
		sendKeys(tlogin.passwordInput, data.get("password"), "password");
		clickAction(signInBtn, "Sign In Button");
		waitForPageLoad(getPageLoadCondition(submit_next));

		// clickAction(tlogin.submit_Continue, "Submit Continue Btn");
		clickAction(submit_next, "Submit Next Btn");
		waitForPageLoad(getPageLoadCondition(myTeamMenu));
		implicitWaitSec(10000);
	}

	public void navigateToMissingReport() throws IOException {

		implicitWaitSec(10000);
		waitForPageLoad(getPageLoadCondition(reportsMenu));
		jsClick(reportsMenu, "Reports Menu");
		testCaseLogger.get().info("Clicked on Reports Menu");
		jsClick(missingReportDropDown.get(0), "Missing timesheet Report Dropdown");
		testCaseLogger.get().info("Clicked on Missing timesheet Report Dropdown subMenu");
		waitForPageLoad(getPageLoadCondition(myTeamMenu));
		implicitWaitSec(10000);
		if (missingReportDropDown.get(1).getText().equalsIgnoreCase("Missing Timesheet Report")) {
			testCaseLogger.get().pass("Missing Timesheet Report Page accessed");
		}
		captureScreenshot(DriverManager.getDriver(), "Missing Report Page");
	}

	public void navigateToReport() {

		implicitWaitSec(10000);
		waitForPageLoad(getPageLoadCondition(reportsMenu));
		jsClick(reportsMenu, "Reports Menu");
		testCaseLogger.get().info("Clicked on Reports Menu");
		jsClick(timeSheetReportDropDown.get(0), "Timesheet Report Dropdown");
		testCaseLogger.get().info("Clicked on Timesheet Report Dropdown subMenu");
		waitForPageLoad(getPageLoadCondition(myTeamMenu));
		implicitWaitSec(10000);
		if (timeSheetReportDropDown.get(1).getText().equalsIgnoreCase("Timesheet Report")) {
			testCaseLogger.get().pass("Timesheet Report Page accessed");
		}
		captureScreenshot(DriverManager.getDriver(), "Timesheet Report Page");

	}
}
