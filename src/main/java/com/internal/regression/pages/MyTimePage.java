/**
 * 
 */
package com.internal.regression.pages;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import com.framework.testutils.DriverManager;
import com.framework.ui.pageobjects.BasePage;

/**
 * @author dharmendra.h
 *
 */

@SuppressWarnings("rawtypes")
public class MyTimePage extends BasePage {

	@FindBy(xpath = PageConstants.SUNDAY)
	private WebElement sunday_textbox;

	@FindBy(xpath = PageConstants.NEW_TIMESHEET_LINE_BUTTON)
	private WebElement new_timesheet_line_button;

	@FindBy(xpath = PageConstants.NEW_TIMESHEET_LINE_BUTTON)
	private List<WebElement> new_timesheet_line_buttons;

	@FindBy(xpath = PageConstants.TIMESHEET_DAYS)
	private List<WebElement> timesheetinputs;

	@FindBy(xpath = PageConstants.SELECT_TIME_LINE_DETAILS)
	private List<WebElement> timeLineSelectors;

	@FindBy(xpath = PageConstants.LAST_SAVED)
	private WebElement lastSavedMsg;

	@FindBy(xpath = PageConstants.SELECT_CATEGORY_DROPDOWN)
	private WebElement select_category_dropdown;

	@FindBy(xpath = PageConstants.SELECT_CATEGORY_ICON)
	private WebElement select_category_icon;

	@FindBy(xpath = PageConstants.SELECT_CATEGORY_TEXTBOX)
	private WebElement select_category_textbox;

	@FindBy(xpath = PageConstants.SELECT_PROJECT_DROPDOWN)
	private WebElement select_project_dropdown;

	@FindBy(xpath = PageConstants.PROJECT_NAME)
	private WebElement project_name;

	@FindBy(xpath = PageConstants.PROJECT_NAME_TEXTBOX)
	private WebElement project_name_textbox;

	@FindBy(xpath = PageConstants.JIRA_ISSUES_TEXTBOX)
	private WebElement jira_issues_textbox;

	@FindBy(xpath = PageConstants.SAVE_ENTRY_BUTTON)
	private WebElement save_entry_button;

	@FindBy(xpath = PageConstants.CHANGES_DETECTED_MSG)
	private WebElement changes_detected_msg;

	@FindBy(xpath = PageConstants.SAVE_BUTTON)
	private WebElement save_button;

	@FindBy(xpath = PageConstants.SUBMITWEEKFORAPPROVAL_BUTTON)
	private WebElement submitweekforapproval_button;

	@FindBy(xpath = PageConstants.YES_SUBMIT_BUTTON)
	private WebElement yes_submit_button;

	@FindBy(xpath = PageConstants.PENDING_APPROVAL_TEXT)
	private WebElement pending_approval_text;

	@FindBy(xpath = PageConstants.PENDING_APPROVAL_ICON)
	private WebElement pending_approval_icon;

	@FindBy(xpath = PageConstants.COPY_TIMESHEET_BUTTON)
	private WebElement copy_timesheet_button;

	@FindBy(xpath = PageConstants.DOWLOAD_TIMESHEET_BUTTON)
	private WebElement download_timesheet_button;

	@FindBy(xpath = PageConstants.DELETE_ICON)
	private WebElement delete_icon;

	@FindBy(xpath = PageConstants.LOADING_SCREEN)
	private WebElement screenLoading;

	@FindBy(xpath = PageConstants.EXISTING_TIMESHEETLINE)
	private WebElement existing_timeLine;

	@FindBy(xpath = PageConstants.YES_DELETE_BUTTON)
	private WebElement yes_delete_button;

	@FindBy(xpath = PageConstants.PREVIOUS_WEEK_BUTTON)
	private WebElement previuos_week_button;

	@FindBy(xpath = PageConstants.NEXT_WEEK_BUTTON)
	private WebElement next_week_button;

	@FindBy(xpath = PageConstants.MY_TIME_LINK)
	private WebElement my_time_link;

	@FindBy(xpath = PageConstants.MY_TEAM_LINK)
	private WebElement my_team_link;

	@FindBy(xpath = PageConstants.REPORTS_LINK)
	private WebElement reports_link;

	@FindBy(xpath = PageConstants.TASK_CHECKBOX)
	private WebElement task_checkbox;

	@FindBy(xpath = PageConstants.BILLABLE_CHECKBOX)
	private WebElement billable_checkbox;

	@FindBy(xpath = PageConstants.COMMENTS_CHECKBOX)
	private WebElement comments_checkbox;

	@FindBy(xpath = PageConstants.SAVE_COPYTIIMESHEET_BUTTON)
	private WebElement save_timesheet_button;

	@FindBy(xpath = PageConstants.TOTAL_TIME_IN_LINE)
	private WebElement totalInLineTimeValue;

	private int totalNoOfTimeEntered = 0;

	public MyTimePage getMyTimePage() {

		return (MyTimePage) openPage(MyTimePage.class);
	}

	@Override
	public ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete_Timesheet() throws InterruptedException {

		implicitWaitSec(300);
		captureScreenshot(DriverManager.getDriver(), "Delete timesheet");
		if (isElementPresentAndDisplay(existing_timeLine)) {
			click(delete_icon, "Delete Timesheet Line");
			implicitWaitSec(10000);
			jsClick(yes_delete_button, "Confirm Delete Timesheet Line");
			testCaseLogger.get().pass("Successfully deleted existing time sheet entry line");
		} else {
			testCaseLogger.get().info("There are no existing timesheet line present to delete");
		}
		implicitWaitSec(10000);

	}

	public void click_On_New_Timesheet_Line_Button() {
		System.out.println("new line add");
		jsClick(new_timesheet_line_button, "Added new time sheet line");
		testCaseLogger.get().info("Added new time sheet line");
		implicitWaitSec(300);
	}

	public void select_Jira_Issue() {
		// jira_issues_textbox.sendKeys("Testing");
		sendKeys(jira_issues_textbox, "Testing", "jira_issues Entered");
	}

	public void click_On_Save_Entry_button() {
		click(save_entry_button, "Save timesheet entry");
		if (changes_detected_msg.getText().equalsIgnoreCase("Changes detected. Please Submit.")) {
			testCaseLogger.get().pass("Project Entry Saved");
		}
	}

	public boolean enterTimesheetHrs(int i, String hrs) {
		boolean isEntered = false;
		try {
			if (isElementEnabledAndDisplay(timesheetinputs.get(i - 1))) {
				System.out.println("entering values");
				clickAction(timesheetinputs.get(i - 1), "timesheet hours field");
				sendKeysAction(timesheetinputs.get(i - 1), hrs, "timesheet hours field");
				implicitWaitSec(300);
				isEntered = true;
			} else {
				testCaseLogger.get().info("Couldn't able to enter values to timesheet line");
			}

		} catch (Exception e) {
			isEntered = false;
			testCaseLogger.get().info("Couldn't able to enter values to timesheet line" + e.getMessage());
		}

		return isEntered;
	}

	public void enter_Hours(String hours, String noOfTimeLine)

	{
		for (int i = 1; i <= timesheetinputs.size(); i++) {
			System.out.println(i + " no of Time hrs to be entered");
			implicitWaitSec(3000);
			boolean isEntered = enterTimesheetHrs(i, hours);
			implicitWaitSec(3000);
			if (isEntered) {
				this.totalNoOfTimeEntered++;
			}
		}
		testCaseLogger.get().pass("Total hours entered for " + this.totalNoOfTimeEntered + " days");
	}

	public void save_Timesheet() {
		click(save_button, "Save Timesheet Line");
		implicitWaitSec(3000);
	}

	public void submit_Timesheet_ForApproval() throws InterruptedException {
		click(submitweekforapproval_button, "submitweekforapproval_button");
		implicitWaitSec(5000);
		click(yes_submit_button, "yes_submit_button");
		implicitWaitSec(10000);
		Assert.assertEquals(pending_approval_icon.isDisplayed(), true);
		testCaseLogger.get().pass("Pending Approval icon is Present");
		if (pending_approval_text.getText().equalsIgnoreCase("Pending Approval")) {
			testCaseLogger.get().pass("Timesheet Successfully Submitted");
		} else {
			testCaseLogger.get().pass("Timesheet Not Submitted");
		}
	}

	public void validate_All_Elements_Presence() {

		// appLogs.debug("Executing Presence of all Elements");
		testCaseLogger.get().info("Executing Presence of all Elements om My Time page");
		Assert.assertEquals(copy_timesheet_button.isDisplayed(), true);
		testCaseLogger.get().pass("copy_timesheet_button found");
		Assert.assertEquals(download_timesheet_button.isDisplayed(), true);
		testCaseLogger.get().pass("download_timesheet_button found");
		Assert.assertEquals(previuos_week_button.isDisplayed(), true);
		testCaseLogger.get().pass("previuos_week_button found");

		Assert.assertEquals(next_week_button.isDisplayed(), true);
		testCaseLogger.get().pass("next_week_button found");
	}

	public void copyTimesheet() throws InterruptedException

	{
		implicitWaitSec(1350);
		PageFactory.initElements(DriverManager.getDriver(), MyTimePage.class);
		implicitWaitSec(1350);
		click(copy_timesheet_button, "copy_timesheet_button");
		testCaseLogger.get().info("Clicked on copy timesheet button");
		implicitWaitSec(1350);
		clickAction(billable_checkbox, "billable_checkbox");
		testCaseLogger.get().info("checked billable_checkbox");
		implicitWaitSec(1350);
		clickAction(comments_checkbox, "comments_checkbox");
		testCaseLogger.get().info("checked time & comments_checkbox");
		implicitWaitSec(1350);
		clickAction(save_timesheet_button, "save_timesheet_button");
		testCaseLogger.get().pass("Save the timesheet");
		captureScreenshot(DriverManager.getDriver(), "Copy timesheet");
	}

	public void copy_Past_Hours(String hours) {

		String hours_captured = DriverManager.getDriver()
				.findElement(By.xpath(PageConstants.TIMESHEET_DAYS.replace("%s", "2")));
		if (hours_captured.equalsIgnoreCase(hours)) {
			testCaseLogger.get().pass("Hours copied from last/past week");
		} else {
			testCaseLogger.get().pass("Hours are not copied from last/past week");
		}
	}

	/**
	 * @param data
	 */
	public void addNewTimeSheetLine(Hashtable<String, String> data) {
		String category_name = "//div[contains(@class,'item')][contains(text(),'%s')]";
		implicitWaitSec(3000);
		jsClick(timeLineSelectors.get(0), "Category ");

		sendKeys(select_category_textbox, data.get("SelectCategory"), "select_category");
		// sendKeys(select_category_textbox, data.get("SelectCategory"),
		// "select_category");
		click(DriverManager.getDriver().findElement(By.xpath(category_name.replace("%s", data.get("SelectCategory")))),
				"category_name");
		testCaseLogger.get().info("Selected Project Name");
		implicitWaitSec(5000);
		jsClick(timeLineSelectors.get(1), "Project Names");
		sendKeys(project_name_textbox, data.get("ProjectName"), "Selecct Project");
		click(DriverManager.getDriver().findElement(By.xpath(category_name.replace("%s", data.get("ProjectName")))),
				"project_name");
		implicitWaitSec(5000);
		select_Jira_Issue();
		testCaseLogger.get().info("Entered Jira value");
		click_On_Save_Entry_button();
		testCaseLogger.get().info("Clicked on Save_Entry button");

		if (lastSavedMsg.isDisplayed()) {
			testCaseLogger.get().pass("Task is added properly " + lastSavedMsg.getText());
			captureScreenshot(DriverManager.getDriver(), "lastSaved");
		}
		implicitWaitSec(3000);

	}

	/**
	 * @param data
	 */
	public void enterTimeSheet(Hashtable<String, String> data) {
		enter_Hours(data.get("hours"), data.get("NoOfTimeLine"));
		testCaseLogger.get().info("Hours entered");
		implicitWaitSec(1800);
	}

	public boolean validateTimeEntryValues(Hashtable<String, String> data) {
		boolean isSame = false;
		try {
			double totalHrs = this.totalNoOfTimeEntered * Double.parseDouble(data.get("hours"));
			testCaseLogger.get().info("Total calculated time entry in line is " + totalHrs);
			if (Integer.parseInt(totalInLineTimeValue.getText()) == totalHrs) {
				testCaseLogger.get().pass("Total calculated time entry in line is correct");
				isSame = true;
			} else {
				testCaseLogger.get().fail("Total calculated time entry in line is NOT correct");
				isSame = false;
			}
			captureScreenshot(DriverManager.getDriver(), "TimeEntry");

		} catch (Exception e) {
			isSame = false;
			testCaseLogger.get().fail("Total calculated time entry in line is NOT correct");
		}

		return isSame;
	}

	/**
	 * 
	 */
	public void click_On_New_Timesheet_Line_Buttons() {
		jsClick(new_timesheet_line_buttons.get(2), "timesheet line again");
		testCaseLogger.get().info("Added another new time sheet line");
		implicitWaitSec(3000);

	}

	/**
	 * @param data
	 */
	public void enterMultiLineTimeSheet(Hashtable<String, String> data) {

		for (int i = 1; i <= timesheetinputs.size(); i++) {
			System.out.println(i + " no of Time hrs to be entered");
			implicitWaitSec(3000);
			boolean isEntered = enterTimesheetHrs(i, data.get("hours"));
			implicitWaitSec(3000);
			if (isEntered) {
				this.totalNoOfTimeEntered++;
			}
		}
		testCaseLogger.get().pass("Total hours entered for " + this.totalNoOfTimeEntered + " days");

	}
}
