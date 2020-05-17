/**
 * 
 */
package com.internal.regression.pages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.framework.testutils.Constants;
import com.framework.testutils.DBUtil;
import com.framework.testutils.DriverManager;
import com.framework.testutils.QueryUtil;
import com.framework.ui.pageobjects.BasePage;
import com.internal.connection.TIBAConnection;
import com.testCase.POJO.EmployeePojo;
import com.testCase.POJO.WeeklyTimeEntry;

/**
 * @author Prabhudatta.C
 *
 */
@SuppressWarnings("rawtypes")
public class TimeSheetApprovalPage extends BasePage {

	@FindBy(xpath = PageConstants.TIMESHEET_APPV_LINK)
	private WebElement timsheetAppvLink;

	@FindBy(id = PageConstants.WEEK_START_DATE)
	private WebElement weekStartDate;

	@FindBy(id = PageConstants.WEEK_END_DATE)
	private WebElement weekEndDate;

	@FindBy(id = PageConstants.OPTIONS_CHECKBOX_GROUP)
	private List<WebElement> options;

	@FindBy(id = PageConstants.ALL_CHECKBOX)
	private WebElement allCheckBox;

	@FindBy(id = PageConstants.EMP_SELECTOR_CONTAINER)
	private WebElement empSelectContainer;

	@FindBy(id = PageConstants.EMP_SELECT_DROPDOWN)
	private WebElement empSelectDropDown;

	@FindBy(id = PageConstants.CLIENT_PROJECT_CHECKBOX)
	private WebElement clientProjectchkbx;

	@FindBy(id = PageConstants.OTHERS_CHECKBOX)
	private WebElement otherschkbx;

	@FindBy(id = PageConstants.SEARCH_BUTTON)
	private List<WebElement> searchBtn;

	@FindBy(id = PageConstants.RESET_BUTTON)
	private WebElement resetBtn;

	@FindBy(id = PageConstants.TIMESHEET_STATUS)
	private WebElement timesheetStatus;

	@FindBy(id = PageConstants.TIMESHEET_COMMENT)
	private WebElement comment;

	@FindBy(id = PageConstants.TIMESHEET_RESULT_TABLE)
	private WebElement timesheetResultTable;

	@FindBy(css = PageConstants.WARNING_MSG) // id = "flash-messages"
	private WebElement warningmsg;

	@FindBy(xpath = PageConstants.ALL_EMP_PARENT_CHECKBOX)
	private List<WebElement> allEmpParentChkBx;

	@FindBy(id = PageConstants.SELECT_ALL_CHECKBOX)
	private WebElement selectAllChkBx;

	@FindBy(xpath = PageConstants.MYTEAM_MENU)
	private WebElement myTeam_Menu;

	@FindBy(xpath = PageConstants.MYTEAM_SELECT_WEBTABLE)
	private List<WebElement> myTeamSelectWebTable;

	@FindBy(className = PageConstants.MYTEAMAPPROVAL_LABEL)
	private WebElement timesheetApproval_Label;

	@FindBy(xpath = PageConstants.MYTEAM_CALANDER)
	private WebElement timesheet_Calander;

	@FindBy(xpath = PageConstants.MYTEAM_PRE_BTN)
	private WebElement timesheet_Pre_Btn;

	@FindBy(xpath = PageConstants.MYTEAM_ALL_CHECKBOX)
	private WebElement timesheet_All_CheckBox;

	@FindBy(xpath = PageConstants.MYTEAM_NOTSUBMITTED_CHECKBOX)
	private WebElement timesheet_NotSubmitted_CheckBox;

	@FindBy(xpath = PageConstants.MYTEAM_REJECTED_CHECKBOX)
	private WebElement timesheet_Rejected_CheckBox;

	@FindBy(xpath = PageConstants.MYTEAM_PENDINGAPPROVAL_CHECKBOX)
	private WebElement timesheet_PendingApproval_CheckBox;

	@FindBy(xpath = PageConstants.MYTEAM_APPROVED_CHECKBOX)
	private WebElement timesheet_Approved_CheckBox;

	@FindBy(xpath = PageConstants.MYTEAM_BILLABLE_CHECKBOX)
	private WebElement timesheet_Billable_CheckBox;

	@FindBy(xpath = PageConstants.MYTEAM_CLIENT_TB)
	private WebElement timesheet_client_TB;

	@FindBy(xpath = PageConstants.MYTEAM_PROJECT_TB)
	private WebElement timesheet_Project_TB;

	@FindBy(xpath = PageConstants.MYTEAM_EMPLOYEE_TB)
	private WebElement timesheet_Employee_TB;

	@FindBy(xpath = PageConstants.MYTEAM_SEARCH_BTN)
	private WebElement timesheet_Search_BTN;

	@FindBy(xpath = PageConstants.MYTEAM_RESET_BTN)
	private WebElement timesheet_Reset_BTN;

	@FindBy(xpath = PageConstants.MYTEAM_CLIENT_ARROW_BTN)
	private WebElement timesheet_Client_Arrow_BTN;

	@FindBy(xpath = PageConstants.MYTEAM_PROJECT_ARROW_BTN)
	private WebElement timesheet_Project_Arrow_Btn;

	@FindBy(xpath = PageConstants.MYTEAM_EMPLOYEE_ARROW_BTN)
	private WebElement timesheet_Employee_Arrow_Btn;

	@FindBy(xpath = PageConstants.MYTEAM_EMPLOYEE_SELECT_BTN)
	private WebElement timesheet_Employee_Select_Btn;

	@FindBy(xpath = PageConstants.MYTEAM_SELECT_CHECKBOX)
	private WebElement myTeam_Select_CheckBox;

	@FindBy(xpath = PageConstants.MYTEAM_COMMENT_BOX)
	private WebElement myTeam_Comment_Box;

	@FindBy(xpath = PageConstants.MYTEAM_CHECKUSER)
	private WebElement myTeam_Check_User;

	@FindBy(xpath = PageConstants.MYTEAM_APPROVE_BTN)
	private WebElement myTeam_Approve_Btn;

	@FindBy(xpath = PageConstants.MYTEAM_REJECT_BTN)
	private WebElement myTeam_Reject_Btn;

	@FindBy(xpath = PageConstants.MYTEAM_CLIENT_DD_VALUES)
	private List<WebElement> myTeam_Client_DD_Values;

	@FindBy(xpath = PageConstants.MYTEAM_PROJECT_DD_VALUES)
	private List<WebElement> myTeam_Project_DD_Values;

	@FindBy(xpath = PageConstants.MYTEAM_EMPLOYEES_DD_VALUES)
	private List<WebElement> myTeam_Employee_DD_Values;

	@FindBy(xpath = PageConstants.MYTEAM_COPYBUTTON)
	private WebElement myTeamCopyButton;

	@FindBy(xpath = PageConstants.MYTEAM_SAVEBUTTON)
	private WebElement myTeamSaveButton;

	@FindBy(xpath = PageConstants.MYTEAM_ERRORMESSAGE)
	private WebElement myTeamErrorMessage;

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

	public String getHomePageTitle() {
		appLogs.debug("Executing getHomePageTitle");
		return DriverManager.getDriver().getTitle();
	}

	@SuppressWarnings("unchecked")
	public TimeSheetApprovalPage open() {
		return (TimeSheetApprovalPage) openPage(TimeSheetApprovalPage.class);
	}

	/**
	 * @throws InterruptedException
	 * @description: this will check presence of all the element for timesheet
	 *               approval page
	 */
	public void validateAllPresence() throws InterruptedException {

		appLogs.debug("Executing Presence of all Elements");
		testCaseLogger.get().info("Executing Presence of all Elements");
		Assert.assertEquals(timesheetApproval_Label.isDisplayed(), true);
		testCaseLogger.get().pass("timesheetApproval_Label found");
		Assert.assertEquals(timesheet_Calander.isDisplayed(), true);
		testCaseLogger.get().pass("timesheet_Calander found");
		Assert.assertEquals(timesheet_Pre_Btn.isDisplayed(), true);
		testCaseLogger.get().pass("timesheet_Pre_Btn found");
		Assert.assertEquals(timesheet_All_CheckBox.isDisplayed(), true);
		testCaseLogger.get().pass("timesheet_All_CheckBox found");
		Assert.assertEquals(timesheet_NotSubmitted_CheckBox.isDisplayed(), true);
		Assert.assertEquals(timesheet_Rejected_CheckBox.isDisplayed(), true);
		testCaseLogger.get().pass("timesheet_Rejected_CheckBox found");
		Assert.assertEquals(timesheet_PendingApproval_CheckBox.isDisplayed(), true);
		testCaseLogger.get().pass("timesheet_PendingApproval_CheckBox found");
		Assert.assertEquals(timesheet_Search_BTN.isDisplayed(), true);
		testCaseLogger.get().pass("searchBtn found");
		Assert.assertEquals(timesheet_Reset_BTN.isDisplayed(), true);
		testCaseLogger.get().pass("resetBtn found");

	}

	/**
	 * This method is to Approve / Reject Time sheet.
	 * 
	 * @param data
	 * @return
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	public boolean approveTimeSheet(Hashtable<String, String> data) throws InterruptedException {

		WebElement element = null;
		String userRole;
		boolean isApproved = false;

		explicitwait(200);
		jsClick(myTeam_Menu, "My Team Menu Click");
		explicitwait(100);
		jsClick(timesheet_Client_Arrow_BTN, "Timesheet Client Arrow Button");
		sendKeys(timesheet_client_TB, data.get("Client"), "Pass Client Value");
		element = DriverManager.getDriver()
				.findElement(By.xpath("//div[contains(text(),'" + data.get("Client") + "')]"));
		jsClick(element, "Timesheet Client Dropdown select");
		element = null;
		explicitwait(200);

		jsClick(timesheet_Project_Arrow_Btn, "Timesheet Project Arrow Button");
		sendKeys(timesheet_Project_TB, data.get("Project"), "Pass Project Value");
		element = DriverManager.getDriver()
				.findElement(By.xpath("//div[contains(text(),'" + data.get("Project") + "')]"));
		jsClick(element, "Timesheet Project Dropdown select");
		explicitwait(1000);
		clickAction(timesheet_Employee_Arrow_Btn, "Timesheet Employee Arrow Button");
		sendKeys(timesheet_Employee_TB, data.get("Employee"), "Pass Employee Value");
		clickAction(timesheet_Employee_Select_Btn, "Timesheet Employee Select Button");
		jsClick(timesheet_All_CheckBox, "Click all check box");
		explicitwait(2000);
		jsClick(timesheet_Search_BTN, "To Click Search Button");
		explicitwait(500);

		if (myTeam_Check_User.isDisplayed()) {
			userRole = "Admin";
		} else {
			userRole = "Project Manager";
		}

		List<WebElement> rowsList = findElements(By.xpath(PageConstants.MYTEAM_SELECT_WEBTABLE));
		String taskType;
		String status;
		for (WebElement row : rowsList) {
			List<WebElement> rowValues = row.findElements(By.tagName("td"));

			for (int cnum = 0; cnum < rowValues.size(); cnum++) {
				if (userRole == "Project Manager") {
					if (rowValues.get(cnum).getText().equals(data.get("Employee"))) {
						taskType = rowValues.get(2).getText();
						status = rowValues.get(6).getText();

						if (taskType.equalsIgnoreCase(data.get("TaskGroup"))) {
							if (status.equalsIgnoreCase(data.get("Status"))) {
								System.out.println("Task type print- " + taskType + "Status- " + status);
								scrollIntoView(myTeam_Select_CheckBox, "Scroll the page");
								jsClick(myTeam_Select_CheckBox, "Clicking the check box");
							}
						}
					}
				} else {
					status = rowValues.get(6).getText();
					if (status.equalsIgnoreCase(data.get("Status"))) {
						System.out.println("Print Status- " + status);
						scrollIntoView(myTeam_Select_CheckBox, "Scroll the page");
						jsClick(myTeam_Select_CheckBox, "Clicking the check box");
					}
				}
			}
		}
		if ("Approve" == data.get("Approve/Reject")) {
			sendKeys(myTeam_Comment_Box, data.get("Comments"), "Pass Comments for Approval or Reject");
			jsClick(myTeam_Approve_Btn, "Click Approve Button");
			testCaseLogger.get().info("Task has been approved");
			explicitwait(500);
		} else {
			sendKeys(myTeam_Comment_Box, data.get("Comments"), "Pass Comments for Approval or Reject");
			jsClick(myTeam_Reject_Btn, "Click Reject Button");
			testCaseLogger.get().info("Task has been Rejected");
			explicitwait(500);
		}
		return isApproved;
	}

	/**
	 * @return: List of employeepojo containing all the information regarding
	 *          timesheet result
	 * 
	 */
	public List<EmployeePojo> getEmpTimeSheetDetails(Hashtable<String, String> data) {

		selectOptions(data);
		selectEmployee(data.get("EmpName"));
		selectAssignment(data);
		getPageLoadCondition();
		click(searchBtn.get(0), "Search");
		pageloadWait(30);
		getPageLoadCondition(timesheetResultTable);
		return getTimeSheetResult();
	}

	/**
	 * @return : List of EmployeePojo which will contain all the information about
	 *         everything related to timesheet.
	 */
	private List<EmployeePojo> getTimeSheetResult() {

		List<EmployeePojo> empojo = new CopyOnWriteArrayList<>();
		if (timesheetResultTable.isDisplayed()) {

			List<WebElement> trs = timesheetResultTable.findElements(By.tagName("tr"));
			for (WebElement row : trs) {

				if (!row.getText().contains("Employee")) {
					List<WebElement> tds = row.findElements(By.tagName("td"));
					EmployeePojo pojo = new EmployeePojo();
					WeeklyTimeEntry timesheetval = new WeeklyTimeEntry();

					for (int i = 0; i < tds.size();) {

						if (StringUtils.isNotEmpty(tds.get(i).getAttribute("name"))) {
							pojo.setApprove(true);
						}
						i++;
						pojo.setEmpname(tds.get(i).getText().trim());

						i++;
						pojo.setTaskGroup(tds.get(i).getText().trim());

						i++;
						pojo.setProject(tds.get(i).getText().trim());

						i++;
						pojo.setTask(tds.get(i).getText().trim());

						i++;
						pojo.setBillable(tds.get(i).getText().trim());

						i++;
						pojo.setRefId(tds.get(i).getText().trim());

						i++;
						pojo.setStatus(tds.get(i).getText().trim());

						i++;
						pojo.setTimesheetVal(attachTimesheet(tds, i, timesheetval));

						empojo.add(pojo);

					}
				}
			}
		}
		return empojo;
	}

	/**
	 * @param tds
	 * @param i
	 * @param timesheetval
	 * @return : WeeklyTimeEntry pojo which will contain all the hr entered per row.
	 */
	private WeeklyTimeEntry attachTimesheet(List<WebElement> tds, int i, WeeklyTimeEntry timesheetval) {

		if (timesheetval != null) {

			timesheetval.setMondayHr(getIntVal(tds.get(i)));

			i++;
			timesheetval.setTuesDayHr(getIntVal(tds.get(i)));

			i++;
			timesheetval.setWednesDayHr(getIntVal(tds.get(i)));

			i++;
			timesheetval.setThursDayHr(getIntVal(tds.get(i)));

			i++;
			timesheetval.setFridayHr(getIntVal(tds.get(i)));

			i++;
			timesheetval.setSaturdayHr(getIntVal(tds.get(i)));

			i++;
			timesheetval.setSunDayHr(getIntVal(tds.get(i)));
		}
		return timesheetval;
	}

	/**
	 * @param webElement
	 * @return
	 */
	private int getIntVal(WebElement webElement) {

		return StringUtils.isEmpty(webElement.getText()) ? 0 : Integer.parseInt(webElement.getText());
	}

	/**
	 * @param data
	 */
	public void selectAssignment(Hashtable<String, String> data) {

		for (String assignment : data.get("Assignment").split(",")) {
			if (assignment.equalsIgnoreCase("Client")) {
				click(clientProjectchkbx, "clientProjectchkbx");
				testCaseLogger.get().pass(data.get("Assignment") + " selected as Assignment");
			} else {
				click(otherschkbx, "otherschkbx");
				testCaseLogger.get().pass(data.get("Assignment") + " selected as Assignment");
			}
		}
	}

	/**
	 * @param string
	 */
	private void selectEmployee(String empname) {

		if (empSelectContainer.isEnabled()) {
			click(empSelectContainer, "empSelectContainer");
			testCaseLogger.get().pass("empSelectContainer clicked");
			clickListBox(empSelectDropDown, empname);
			testCaseLogger.get().pass(empname + " selected as Employee");

		}
	}

	/**
	 * @param data
	 *            This method is to check the check boxes on the input it receives
	 *            from testData
	 */
	private void selectOptions(Hashtable<String, String> data) {

		if (!options.isEmpty()) {

			for (String testOption : data.get("Options").split(",")) {
				for (WebElement option : options) {
					if (option.getText().equalsIgnoreCase(testOption)) {
						click(option, "option selected " + option.getText());
						testCaseLogger.get().pass(option.getText() + " checked");
						break;
					}
				}
			}
		}

	}

	/**
	 * @param data
	 */
	public void validateCheckBox(Hashtable<String, String> data) {

		data.put("Options", "Approved");
		selectOptions(data);
		selectEmployee(data.get("EmpName"));
		selectAssignment(data);
		click(searchBtn.get(0), "Search button checked");
		pageloadWait(30);
		getPageLoadCondition(timesheetResultTable);

		List<WebElement> trs = timesheetResultTable.findElements(By.tagName("tr"));
		for (WebElement row : trs) {

			if (!row.getText().contains("Employee")) {
				List<WebElement> tds = row.findElements(By.tagName("td"));

				if (StringUtils.isEmpty(tds.get(0).getAttribute("name"))) {
					testCaseLogger.get().pass("couldn't find the check box");
					break;
				} else {
					testCaseLogger.get().fail("Found the check box");
				}
			}
		}
	}

	/**
	 * @param data
	 *            This method will execute the query to change the employee
	 *            timesheet status from approved to pending.
	 */
	public void changeDBTheStatus(Hashtable<String, String> data) {
		// TODO Auto-generated method stub
		String query = DBUtil.getNamedQuery("getEmployeeID");
		String firstName = data.get("EmpName").split(" ")[0];
		String lastName = data.get("EmpName").split(" ")[data.get("EmpName").split(" ").length - 1];
		int empIDNo = 0;

		try {
			query = QueryUtil.setString(new StringBuilder(query), "FirstName", "%" + firstName + "%").toString();
			query = QueryUtil.setString(new StringBuilder(query), "LastName", "%" + lastName + "%").toString();
		} catch (SQLException e) {
			testCaseLogger.get().fail("Could not format the select query " + e.getMessage());
			e.printStackTrace();
		}

		Connection con = new TIBAConnection().getConnection();
		if (con != null) {

			Statement stmt = null;
			ResultSet rs = null;
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					empIDNo = rs.getInt("employee_id");
					testCaseLogger.get().info(empIDNo + " Got the employee ID for " + data.get("EmpName"));
					break;
				}

				query = DBUtil.getNamedQuery("updateEmployeeID");
				try {
					query = QueryUtil.setInt(new StringBuilder(query), "empID", empIDNo).toString();
				} catch (SQLException e) {
					testCaseLogger.get().fail("Could not format the update query " + e.getMessage());
					e.printStackTrace();
				}

				int updated = stmt.executeUpdate(query);
				if (updated > 0) {
					testCaseLogger.get().info("Record has been reverted back for the employee" + data.get("EmpName"));
				} else {
					testCaseLogger.get()
							.info("Record has not been reverted back for the employee" + data.get("EmpName"));
				}

			} catch (SQLException e) {
				testCaseLogger.get().fail("Could execute the query " + e.getMessage());
				e.printStackTrace();
			} finally {

				try {
					if (rs != null) {
						rs.close();
					}
					if (stmt != null) {
						stmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					testCaseLogger.get()
							.fail("Could able to close Resultset, Statement 0r Connection " + e.getMessage());
				}

			}

		}

	}

	public boolean editTimeSheet(Hashtable<String, String> data) throws InterruptedException {
		boolean editTimesheet = false;
		String userRole;

		explicitwait(1000);
		jsClick(myTeam_Menu, "My Team Menu Click");
		explicitwait(2000);
		jsClick(timesheet_All_CheckBox, "Click all check box");
		explicitwait(2000);
		jsClick(timesheet_Search_BTN, "To Click Search Button");
		explicitwait(10000);

		if (isElementPresentAndDisplay(myTeam_Check_User)) {
			userRole = "Admin";
		} else {
			userRole = "Project Manager";
		}
		String taskType;
		String status;
		for (WebElement row : myTeamSelectWebTable) {
			List<WebElement> rowValues = row.findElements(By.tagName("td"));

			for (int cnum = 0; cnum < rowValues.size(); cnum++) {
				if (userRole == "Project Manager") {
					if (rowValues.get(cnum).getText().equals(data.get("Employee"))) {
						taskType = rowValues.get(2).getText();
						status = rowValues.get(6).getText();

						if (taskType.equalsIgnoreCase(data.get("TaskGroup"))) {
							if (status.equalsIgnoreCase(data.get("Status"))) {
								scrollIntoView(myTeam_Select_CheckBox, "Scroll the page");

							}
						}
					}
				} else {
					status = rowValues.get(6).getText();
					if (status.equalsIgnoreCase(data.get("Status"))) {
						scrollIntoView(myTeam_Select_CheckBox, "Scroll the page");
						click(rowValues.get(15), rowValues.get(15).getText());

					}
				}
			}
		}

		return editTimesheet;
	}

	/**
	 * @param data
	 * @return
	 * @throws InterruptedException
	 */
	public boolean copyTimeSheet(Hashtable<String, String> data) throws InterruptedException {
		boolean copyTimesheet = false;
		String userRole;

		fillMyTeamPageDetails(data);
		if (isElementPresentAndDisplay(myTeam_Check_User)) {
			userRole = "Admin";
		} else {
			userRole = "Project Manager";
		}

		if (userRole == "Project Manager") {
			fetchCopyButtonForTimesheet(data);

		} else {
			fetchCopyButtonForTimesheet(data);
		}

		return copyTimesheet;
	}

	/**
	 * This Method is to fill details across My Team page for Approve/ Reject, Edit
	 * and Copy Time sheets.
	 * 
	 * @param data
	 * @return
	 * @throws InterruptedException
	 */
	public boolean fillMyTeamPageDetails(Hashtable<String, String> data) throws InterruptedException {

		WebElement element = null;
		boolean isDetailsFilled = false;

		implicitWaitSec(1000);
		jsClick(myTeam_Menu, "My Team Menu Click");
		implicitWaitSec(2000);
		jsClick(timesheet_Client_Arrow_BTN, "Timesheet Client Arrow Button");
		checkDropDownValuesOrder(myTeam_Client_DD_Values);
		sendKeys(timesheet_client_TB, data.get("Client"), "Pass Client Value");
		element = DriverManager.getDriver()
				.findElement(By.xpath("//div[contains(text(),'" + data.get("Client") + "')]"));
		jsClick(element, "Timesheet Client Dropdown select");
		element = null;
		implicitWaitSec(200);

		jsClick(timesheet_Project_Arrow_Btn, "Timesheet Project Arrow Button");
		checkDropDownValuesOrder(myTeam_Project_DD_Values);
		sendKeys(timesheet_Project_TB, data.get("Project"), "Pass Project Value");
		element = DriverManager.getDriver()
				.findElement(By.xpath("//div[contains(text(),'" + data.get("Project") + "')]"));
		jsClick(element, "Timesheet Project Dropdown select");
		implicitWaitSec(1000);
		clickAction(timesheet_Employee_Arrow_Btn, "Timesheet Employee Arrow Button");
		checkDropDownValuesOrder(myTeam_Employee_DD_Values);
		sendKeys(timesheet_Employee_TB, data.get("Employee"), "Pass Employee Value");
		clickAction(timesheet_Employee_Select_Btn, "Timesheet Employee Select Button");
		jsClick(timesheet_All_CheckBox, "Click all check box");
		implicitWaitSec(2000);
		jsClick(timesheet_Search_BTN, "To Click Search Button");
		implicitWaitSec(3000);
		return isDetailsFilled;
	}

	/**
	 * @param data
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	public void fetchCopyButtonForTimesheet(Hashtable<String, String> data) throws InterruptedException {
		List<WebElement> rowsList = findElements(By.xpath(PageConstants.MYTEAM_SELECT_WEBTABLE));

		for (WebElement row : rowsList) {
			if (row.getText().contains(data.get("Employee"))) {
				if (row.getText().contains(data.get("TaskGroup")) && row.getText().contains(data.get("Status"))) {

					List<WebElement> columnData = row.findElements(By.tagName("td"));
					scrollIntoView(myTeam_Select_CheckBox, "Scroll the page");
					click(myTeamCopyButton, "Copy Button");
					// columnData.get(columnData.size() -
					// 1).findElement(By.xpath(myTeamCopyButton)).click();
					setValueCopyTimesheet(data);
				}
			}
		}
	}

	/**
	 * @param data
	 */
	public void setValueCopyTimesheet(Hashtable<String, String> data) {
		int employeeCount = findElements(By.xpath("//table[@class='manager-table emp-details-table']//tbody")).size();
		int lastRowcount = findElements(
				By.xpath("//table[@class='manager-table emp-details-table']//tbody[" + employeeCount + "]")).size();
		WebElement copyRow = findElement(By.xpath("//table[@class='manager-table emp-details-table']//tbody["
				+ employeeCount + "]//tr[" + lastRowcount + "]"));

		WebElement columnData = copyRow.findElement(By.tagName("td"));
		columnData.clear();
		columnData.sendKeys(data.get("EditAfterValue"));
		boolean clickBtnEnabled = isElementPresentAndDisplay(myTeamSaveButton);
		if (clickBtnEnabled == true) {
			jsClick(myTeamSaveButton, "Save Button");
			testCaseLogger.get().info("Clicked Save Button after succesful copy action performed.");
		}
		if (isElementPresentAndDisplay(myTeamErrorMessage)) {
			String errorMessage = myTeamErrorMessage.getText();
			testCaseLogger.get().info(errorMessage);
		}

	}

	@SuppressWarnings("unchecked")
	public void checkDropDownValuesOrder(List<WebElement> elements) {

		ArrayList originalList = new ArrayList();

		for (WebElement row : elements) {
			originalList.add(row.getText());
		}
		ArrayList tempList = originalList;
		Collections.sort(tempList); // When you change one list, it changes the other list as well.

		/*
		 * So the test gets pass all the time because the sequence in the originalList
		 * and tempList is going to be same. If you are following above process then
		 * your test never fails, because When you change one list, it changes the other
		 * list as well.
		 */

		if (originalList == tempList) {
			testCaseLogger.get().info("Dropdown values are sorted");

		} else {
			testCaseLogger.get().info("Dropdown values are not sorted");
		}
	}

}
