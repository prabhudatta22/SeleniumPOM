/**
 * 
 */
package com.internal.regression.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.framework.testutils.Constants;
import com.framework.testutils.DriverManager;
import com.framework.ui.pageobjects.BasePage;

/**
 * @author Prabhudatta.C
 *
 */
@SuppressWarnings("rawtypes")
public class TimeSheetReportPage extends BasePage {

	@FindBy(id = PageConstants.MENU_REPORTS)
	private WebElement reportsMenu;

	@FindBy(id = PageConstants.RM_REPORTS_MENU)
	private WebElement rmReportsMenu;

	@FindBy(id = PageConstants.TIMESHEET_REPORTS_MENU)
	private WebElement timeSheetReportMenu;

	@FindBy(xpath = PageConstants.YEARLY_REPORTS_CHECKBOX)
	private WebElement yearlyReportChkBx;

	@FindBy(id = PageConstants.TIME_PERIOD)
	private WebElement timeperiod;

	@FindBy(xpath = PageConstants.TIME_PERIOD_YEAR)
	private WebElement timePeriod_year;

	@FindBy(css = PageConstants.YEAR_DROPDOWN)
	private WebElement yearDropDwn;

	@FindBy(css = PageConstants.MONTH_DROPDOWN)
	private WebElement monthPicker;

	@FindBy(id = PageConstants.DIRECT_REPORTEES)
	private WebElement directReportess;

	@FindBy(css = PageConstants.EMP_NAME)
	private WebElement empName;

	@FindBy(id = PageConstants.EMP_RESULT_CONTAINER)
	private WebElement empResultContainer;

	@FindBy(id = PageConstants.TASK_GROUP)
	private WebElement taskGroup;

	@FindBy(id = PageConstants.TASK_GROUP_DROPDOWN)
	private WebElement taskGroupDropDown;

	@FindBy(className = PageConstants.TASK_GROUP_DROPDOWN_SEARCH)
	private WebElement taskGroupDropDownSearch;

	@FindBy(id = PageConstants.PROJECT_CONTAINER)
	private WebElement projectContainer;

	@FindBy(id = PageConstants.PROJECT_RESULT_DROPDOWN)
	private WebElement projectResultDropDown;

	@FindBy(id = PageConstants.PRACTICE)
	private WebElement practice;

	@FindBy(id = PageConstants.PRACTICE_DROPDOWN)
	private WebElement practiceDropDown;

	@FindBy(id = PageConstants.LOCATION)
	private WebElement location;

	@FindBy(id = PageConstants.LOCATION_RESULT_DROPDOWN)
	private WebElement locationResultDropDown;

	@FindBy(xpath = PageConstants.STATUS_CHECKBOX_LIST)
	private List<WebElement> statusChkBxList;

	@FindBy(id = PageConstants.CLIENT_APPROVALS_CHECKBOX)
	private WebElement clientApprovalschkbx;

	@FindBy(id = PageConstants.REPORT_RESULTS_TABLE)
	private WebElement reportResultTable;

	@FindBy(css = PageConstants.DOWNLOAD_BUTTON)
	private List<WebElement> downloadBtns;

	@FindBy(id = PageConstants.SEARCH_BUTN)
	private WebElement searchBtn;

	@FindBy(xpath = PageConstants.TR_DEPARTMENT_ICON)
	private WebElement trDepartmentIcon;

	@FindBy(xpath = PageConstants.TR_DEPARTMENT_ITEM_LIST)
	private List<WebElement> trDepartmentItemList;

	@FindBy(xpath = PageConstants.TR_PRACTICE_ICON)
	private WebElement trPracticeIcon;

	@FindBy(xpath = PageConstants.TR_PRACTICE_ITEM_LIST)
	private List<WebElement> trPracticeItemList;

	@FindBy(xpath = PageConstants.TR_LOCATION_ICON)
	private WebElement trLocationIcon;

	@FindBy(xpath = PageConstants.TR_LOCATION_ITEM_LIST)
	private List<WebElement> trLocationItemList;

	@FindBy(xpath = PageConstants.TR_EMPLOYEE_ICON)
	private WebElement trEmployeeIcon;

	@FindBy(xpath = PageConstants.TR_EMPLOYEE_ITEM_LIST)
	private List<WebElement> trEmployeeItemList;

	@FindBy(xpath = PageConstants.TR_GROUPTYPE_ICON)
	private WebElement trGroupTypeIcon;

	@FindBy(xpath = PageConstants.TR_GROUPTYPE_ITEM_LIST)
	private List<WebElement> trGroupTypeItemList;

	@FindBy(xpath = PageConstants.TR_DEPARTMENT_NAME)
	private WebElement trDepartmentName;

	@FindBy(xpath = PageConstants.TR_PRACTICE_NAME)
	private WebElement trPracticeName;

	@FindBy(xpath = PageConstants.TR_LOCATION_NAME)
	private WebElement trLocationName;

	@FindBy(xpath = PageConstants.TR_EMPLOYEE_NAME)
	private WebElement trEmployeeName;

	@FindBy(xpath = PageConstants.TR_GROUPTYPE_NAME)
	private WebElement trGroupTypeName;

	@FindBy(xpath = PageConstants.TR_GROUPTASK_NAME)
	private WebElement trGroupTaskName;

	@FindBy(xpath = PageConstants.TR_ACC_INDIA_TG_LIST)
	private List<WebElement> trAccIndiaTGList;

	@FindBy(xpath = PageConstants.TR_GROUPTASK_ICON)
	private WebElement trGroupTaskIcon;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.framework.ui.pageobjects.BasePage#getPageLoadCondition()
	 */
	@Override
	public ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions
				.visibilityOf(DriverManager.getDriver().findElement(By.className(Constants.OR_FOOTER_DIV2)));
	}

	@SuppressWarnings("unchecked")
	public TimeSheetReportPage open() {
		return (TimeSheetReportPage) openPage(TimeSheetReportPage.class);
	}

	/**
	 * @param data
	 */
	public void downLoadReport(Hashtable<String, String> data) {

		if (!isElementPresentAndDisplay(downloadBtns.get(0))) {
			testCaseLogger.get().pass("Download excel butn is not available before seach");
		} else {
			testCaseLogger.get().fail("Download excel butn is available before seach");
		}

		selectDuration(data);
		selectEmployee(data);
		selectTaskProject(data);

		click(practice, "Practice");
		clickListBox(practiceDropDown, data.get("Practice").trim());

		click(location, "location");
		clickListBox(locationResultDropDown, data.get("Location").trim());
		click(searchBtn, "Search");

		if (isElementPresentAndDisplay(downloadBtns.get(0))) {
			testCaseLogger.get().pass("Download excel butn is available after seach");
		} else {
			testCaseLogger.get().fail("Download butn is available after seach");
		}

		click(downloadBtns.get(0), "Download Excel");

	}

	/**
	 * @param data
	 */
	private void selectTaskProject(Hashtable<String, String> data) {

		click(taskGroup, "task group ");
		clickListBox(taskGroupDropDown, data.get("Task Group").trim());
		testCaseLogger.get().pass("Task Group selected " + data.get("Task Group").trim());

		if (data.get("Task Group").contains("Delivery / Project")) {
			click(projectContainer, "Project dropdown");
			clickListBox(projectResultDropDown, data.get("ProjectName").trim());
			testCaseLogger.get().pass("Project selected " + data.get("ProjectName").trim());
		}
	}

	/**
	 * @param data
	 */
	private void selectEmployee(Hashtable<String, String> data) {

		if (data.get("DirectReportee").equalsIgnoreCase("Yes")) {
			testCaseLogger.get().info("Getting my direct reportee check box visible");
			click(directReportess, "Direct Reportees check box");
			testCaseLogger.get().pass("checkin my direct reportee options");
		}

		if (StringUtils.isNotEmpty(data.get("EmpName"))) {
			sendKeys(empName, data.get("EmpName"), "Entering emp name as " + data.get("EmpName"));
			clickListBox(empResultContainer, data.get("EmpName").trim());
			testCaseLogger.get().pass("Selecting employee " + data.get("EmpName"));
		}

	}

	/**
	 * @param data
	 */
	private void selectDuration(Hashtable<String, String> data) {

		click(timeperiod, "Time picker");
		selectListBox(yearDropDwn, data.get("Year"), "ByVisibleText");
		testCaseLogger.get().pass("Selecting year");
		selectMonth(data);
	}

	/**
	 * @param data
	 */
	private void selectMonth(Hashtable<String, String> data) {

		boolean isSelected = false;
		List<WebElement> monthRows = monthPicker.findElements(By.tagName("tr"));
		for (WebElement month : monthRows) {
			List<WebElement> months = month.findElements(By.tagName("td"));

			for (WebElement monthVal : months) {
				if (monthVal.getText().equalsIgnoreCase(data.get("Month"))) {
					jsClick(monthVal, data.get("Month") + " clicked");
					testCaseLogger.get().pass("Selecting Month");
					isSelected = true;
					break;
				}
			}

			if (isSelected) {
				testCaseLogger.get().pass("Month selected");
				break;
			}
		}
	}

	/**
	 * 
	 */
	public void navigateToUrl() {

		mouseHover(reportsMenu);
		mouseHover(rmReportsMenu);
		jsClick(timeSheetReportMenu, "Timesheet report menu");
		getPageLoadCondition(practice);
		testCaseLogger.get().info("Time sheet report page ");

	}

	/**
	 * @param data
	 */
	public void validateAllControls(Hashtable<String, String> data) {

		for (WebElement chkbx : statusChkBxList) {

			if (isElementPresentAndDisplay(chkbx)) {
				testCaseLogger.get().pass("Element is visible : " + chkbx.getText());
			} else {
				testCaseLogger.get().fail("Element is not visible : " + chkbx.getText());
			}
		}
	}

	/**
	 * @param data
	 */
	public void validateTimeSheetVerify(Hashtable<String, String> data) {

		selectDuration(data);
		selectEmployee(data);
		selectTaskProject(data);

		click(practice, "Practice");
		clickListBox(practiceDropDown, data.get("Practice").trim());

		click(location, "location");
		clickListBox(locationResultDropDown, data.get("Location").trim());
		checkStatus(data);
		click(searchBtn, "Search");
		verifyResultTabledata(data);

	}

	/**
	 * @param data
	 */
	private void verifyResultTabledata(Hashtable<String, String> data) {

		if (isElementPresentAndDisplay(reportResultTable)) {
			List<WebElement> tr = reportResultTable.findElements(By.tagName("tr"));
			if (tr.get(0).getText().contains(data.get("EmpName"))) {
				testCaseLogger.get().pass("Found the employee to be part of the report result" + data.get("EmpName"));
			} else {
				testCaseLogger.get().fail("Employee Not found in the report result" + data.get("EmpName"));
			}
		}

	}

	/**
	 * @param data
	 */
	private void checkStatus(Hashtable<String, String> data) {

		for (WebElement chkbx : statusChkBxList) {
			for (String stats : data.get("Status").split(",")) {
				System.out.println(chkbx.getAttribute("value"));
				if (chkbx.getAttribute("value").equalsIgnoreCase(stats.trim())) {
					jsClick(chkbx, stats);
					testCaseLogger.get().pass("Status checked as " + stats);
					break;
				}
			}
		}

	}

	/**
	 * @param data
	 */
	public void validateTimeSheetProjectVerify(Hashtable<String, String> data) {
		selectDuration(data);
		selectEmployee(data);
		selectTaskProject(data);
	}

	public void downLoadDetailReport(Hashtable<String, String> data) {

		System.out.println("testsudhir" + downloadBtns.size());
		if (!isElementPresentAndDisplay(downloadBtns.get(0))) {
			testCaseLogger.get().pass("Download excel butn is not available before seach");
		} else {
			testCaseLogger.get().fail("Download excel butn is available before seach");
		}

		click(yearlyReportChkBx, "YearlyReportcheckbox");
		selectDuration(data);
		selectEmployee(data);
		selectTaskProject(data);

		click(practice, "Practice");
		clickListBox(practiceDropDown, data.get("Practice").trim());

		click(location, "location");
		clickListBox(locationResultDropDown, data.get("Location").trim());
		click(searchBtn, "Search");

		if (isElementPresentAndDisplay(downloadBtns.get(1))) {
			testCaseLogger.get().pass("Download excel butn is available after seach");
		} else {
			testCaseLogger.get().fail("Download butn is available after seach");
		}

		click(downloadBtns.get(1), "Download Detail Excel");

	}

	public void myDirectReporteesReportDownload(Hashtable<String, String> data) {

		if (!isElementPresentAndDisplay(downloadBtns.get(0))) {
			testCaseLogger.get().pass("Download excel butn is not available before seach");
		} else {
			testCaseLogger.get().fail("Download excel butn is available before seach");
		}

		selectDuration(data);
		click(directReportess, "DirectReporteesChkbox");
		// selectEmployee(data);
		selectTaskProject(data);

		click(practice, "Practice");
		clickListBox(practiceDropDown, data.get("Practice").trim());

		click(location, "location");
		clickListBox(locationResultDropDown, data.get("Location").trim());
		click(searchBtn, "Search");

		if (isElementPresentAndDisplay(downloadBtns.get(0))) {
			testCaseLogger.get().pass("Download excel butn is available after seach");
		} else {
			testCaseLogger.get().fail("Download butn is available after seach");
		}

		click(downloadBtns.get(0), "Download Excel");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.framework.ui.pageobjects.BasePage#getPageLoadCondition()
	 */

	// select_location.click();
	@SuppressWarnings("unchecked")
	public void validateDropdwonSorting(List<WebElement> list, WebElement webElement1, WebElement webElement2) {
		implicitWaitSec(5);
		String elementName = webElement2.getText();
		click(webElement1, elementName);
		List<WebElement> dp = list;
		System.out.println("Size of Dropdown is " + dp.size());

		List actualList = new ArrayList<>();

		for (WebElement webElement : dp) {
			String name = webElement.getText();

			actualList.add(name);

		}
		List tempList = actualList;
		Collections.sort(tempList);

		if (actualList.equals(tempList)) {
			testCaseLogger.get().pass(elementName + " Dropdown values are sorted");
		} else {
			testCaseLogger.get().fail(elementName + " Dropdown values are not sorted");
		}
	}

	public void validateTRDepartmentDropdownIsSorted() {

		validateDropdwonSorting(trDepartmentItemList, trDepartmentIcon, trDepartmentName);
	}

	public void validateTRPracticeDropdownIsSorted() {

		validateDropdwonSorting(trPracticeItemList, trPracticeIcon, trPracticeName);

	}

	public void validateTRLocationDropdownIsSorted() {

		validateDropdwonSorting(trLocationItemList, trLocationIcon, trLocationName);
	}

	public void validateTREmployeeDropdownIsSorted() {

		validateDropdwonSorting(trEmployeeItemList, trEmployeeIcon, trEmployeeName);
	}

	public void validateTRGroupTypeDropdownIsSorted() {

		validateDropdwonSorting(trGroupTypeItemList, trGroupTypeIcon, trGroupTypeName);
	}

	public void validateTRTaskGroupDropdownIsSorted() {

	}

	public void validateTRLocationDropdownIsMultiSelector() {

		List<WebElement> list = trLocationItemList;

		for (int i = 0; i < list.size(); i++) {
			click(list.get(i), "Location dropdown" + i + "Value Selected");
		}
		testCaseLogger.get().pass("Location Dropdown is MultiSelector");

	}

	public void validateTREmployeeDropdownIsMultiSelector() {

		List<WebElement> list = trEmployeeItemList;

		for (int i = 0; i < list.size(); i++) {
			click(list.get(i), "Employee dropdown" + i + "Value Selected");
		}
		testCaseLogger.get().pass("Employee Dropdown is MultiSelector");

	}

	public void validateTRTaskGoupDropdownSorting(String groupType, List<WebElement> list) {

		click(trGroupTypeIcon, "trGroupTypeIcon");
		for (int i = 0; i <= trGroupTypeItemList.size(); i++) {
			if (trGroupTypeItemList.get(i).getText().equalsIgnoreCase(groupType))

			{
				click(trGroupTypeItemList.get(i), "" + groupType + "");
				validateDropdwonSorting(list, trGroupTaskIcon, trGroupTaskName);
				testCaseLogger.get().pass("Task Group Dropdown values are sorted for Group Type as " + groupType + "");
			}

		}

	}

	public void validateTRTaskGoupAccIndiaIsSorted() {
		validateTRTaskGoupDropdownSorting("Accounts_India", trAccIndiaTGList);

	}

	public void validateTRTaskGoupAccUSIsSorted() {
		validateTRTaskGoupDropdownSorting("Accounts_US", trAccIndiaTGList);
	}

	public void validateTRTaskGoupDelivaryIsSorted() {
		validateTRTaskGoupDropdownSorting("Delivery", trAccIndiaTGList);

	}

	public void validateTRTaskGoupGeneralIsSorted() {
		validateTRTaskGoupDropdownSorting("General", trAccIndiaTGList);

	}

	public void validateTRTaskGoupHumanResourcesIsSorted() {
		validateTRTaskGoupDropdownSorting("Human Resources", trAccIndiaTGList);

	}

	public void validateTRTaskGoupItSupportIsSorted() {
		validateTRTaskGoupDropdownSorting("IT Support", trAccIndiaTGList);

	}

	public void validateTRTaskGroupMarketingIsSorted() {
		validateTRTaskGoupDropdownSorting("Marketing", trAccIndiaTGList);

	}

	public void validateTRTaskGoupOrganizationIsSorted() {
		validateTRTaskGoupDropdownSorting("Organization", trAccIndiaTGList);

	}

	public void validateTRTaskGoupPracticeIsSorted() {
		validateTRTaskGoupDropdownSorting("Practice", trAccIndiaTGList);

	}

	public void validateTRTaskGoupPreSalesIsSorted() {
		validateTRTaskGoupDropdownSorting("PreSales", trAccIndiaTGList);

	}

	public void validateTRTaskGoupTimeOffIsSorted() {
		validateTRTaskGoupDropdownSorting("Time-off", trAccIndiaTGList);

	}
}
