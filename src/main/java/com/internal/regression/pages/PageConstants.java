/**
 * 
 */
package com.internal.regression.pages;

/**
 * @author dharmendra.h
 *
 */
public interface PageConstants {

	public static String NEW_TIMESHEET_LINE_BUTTON = ".//*[@class='button  primary ']";
	public static String EXISTING_TIMESHEETLINE = ".//*[@class='wrapper-grid-row add-anim']";

	public static String SELECT_TIME_LINE_DETAILS = ".//*[@class='dropdown-list__selector d-selector']";
	public static String LAST_SAVED = ".//*[@class='last-saved']";
	public static String HOUR_LIMIT = ".//*[@class='task-save-error']";

	public static String SELECT_CATEGORY_DROPDOWN = "//*[@id=\"app\"]/div[2]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[3]/div[1]/div/div[1]/div[1]";

	public static String SELECT_CATEGORY_TEXTBOX = ".//*[@name='select-category']";

	public static String SELECT_CATEGORY_ICON = "//*[@id=\"app\"]/div[2]/div[6]/div[1]/div[1]/div/div[2]/div[2]/div/div[3]/div[1]/div/div[1]/div[2]/img";

	public static String SELECT_PROJECT_DROPDOWN = "//*[@id=\"app\"]/div[2]/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[3]/div[2]/div/div[1]/div[1]";

	public static String PROJECT_NAME = "//*[contains(@class,'item')][contains(text(),'AARP-Offshore Resourcing')]";

	public static String PROJECT_NAME_TEXTBOX = ".//*[@name='select-project']";

	public static String JIRA_ISSUES_TEXTBOX = ".//*[@name='custom-project-task-name']";

	public static String SAVE_ENTRY_BUTTON = "//*[contains(@class,'button__text')][contains(text(),'Save Entry')]";

	public static String CHANGES_DETECTED_MSG = "//*[@class='flex-container']/div[1]/div[1]";

	// Locators for hours text-boxes

	public static String TIMESHEET_DAYS = ".//*[@class='flex-prop']";

	public static String SUNDAY = "//*[@id=\"app\"]/div[2]/div[5]/div[2]/div[2]/div[8]/div/div[1]/input";

	public static String SAVE_BUTTON = "//*[@id=\"app\"]/div[2]/div[6]/div[3]/div/div[1]/button[1]";

	public static String SUBMITWEEKFORAPPROVAL_BUTTON = "//*[@id=\"app\"]/div[2]/div[6]/div[3]/div/div[1]/button[2]";

	public static String YES_SUBMIT_BUTTON = "//*[@id=\"app\"]/div[2]/div[1]/div[2]/div/div[1]/div/div/div[5]/div[2]/div/div[4]/button[2]/div/div[2]";

	////////////////////////////////////////////////////////////////////////

	public static String PENDING_APPROVAL_TEXT = "//*[@id=\"app\"]/div[2]/div[6]/div[2]/div/div/div";

	public static String PENDING_APPROVAL_ICON = "//*[@id=\"app\"]/div[2]/div[6]/div[2]/div/div/div/img";

	public static String DOWLOAD_TIMESHEET_BUTTON = "//*[@id=\"app\"]/div[2]/div[6]/div[3]/div/div[2]/button/div/div[2]";

	public static String PREVIOUS_WEEK_BUTTON = "//*[@id=\"app\"]/div[2]/div[1]/div[2]/div/div[1]/div/div/button[1]";

	public static String NEXT_WEEK_BUTTON = "//*[@id=\"app\"]/div[2]/div[1]/div[2]/div/div[1]/div/div/button[2]";

	public static String MY_TIME_LINK = "//*[@id=\"app\"]/div[1]/div/div/div[2]/div/div[1]/div/div[1]/div[1]";

	public static String MY_TEAM_LINK = "//*[@id=\"app\"]/div[1]/div/div/div[2]/div/div[1]/div/div[1]/div[2]";

	public static String REPORTS_LINK = "//*[@id=\"app\"]/div[1]/div/div/div[2]/div/div[1]/div/div[1]/div[3]";

	/* ----------- COPY TIMESHEET fUNCTIONALITY LOCATORS ----------------------- */

	public static String COPY_TIMESHEET_BUTTON = ".//*[@class='button m-2-right m-2-left  ']";

	public static String TASK_CHECKBOX = "//*[@class='copy-body-grid']//div[1]//label[1]//span[1]";

	public static String BILLABLE_CHECKBOX = "//*[@class='copy-body-grid']//div[2]//label[1]//span[1]";

	public static String COMMENTS_CHECKBOX = "//*[@class='copy-body-grid']//div[3]//label[1]//span[1]";

	public static String SAVE_COPYTIIMESHEET_BUTTON = "//*[@class='button__text'][contains(text(),'Save')]";

	public static String TOTAL_TIME_IN_LINE = ".//*[@class='last-grid-column']";

	public static String LOADING_SCREEN = ".//*[@class='loading-screen']";

	/*
	 * ----------- DELETE TIMESHEET fUNCTIONALITY LOCATORS -----------------------
	 */

	public static String DELETE_ICON = ".//*[@title='DELETE TASK']";

	public static String YES_DELETE_BUTTON = "//div[contains(text(),'Yes, Delete')]";

	/* ----------------- MY TEAM PAGE LOCATORS ---------------------- */

	public static String MYTEAM_MENU = "//*[contains(text(),'My Team')]";
	public static String MYTEAMAPPROVAL_LABEL = "p-6-top p-6-bottom flex-container heading-text";
	public static String MYTEAM_CALANDER = "//button[contains(@class,'secondary icon-left')]";
	public static String MYTEAM_PRE_BTN = "//div[contains(text(),'<')]";
	public static String MYTEAM_ALL_CHECKBOX = "//label[@for='all_id_manager']//span";
	public static String MYTEAM_NOTSUBMITTED_CHECKBOX = "//label[@for='notSubmitted_id_manager']//span";
	public static String MYTEAM_REJECTED_CHECKBOX = "//label[@for='rejected_id_manager']//span";
	public static String MYTEAM_PENDINGAPPROVAL_CHECKBOX = "//label[@for='pendingApproval_id_manager']//span";
	public static String MYTEAM_APPROVED_CHECKBOX = "//label[@for='approved_id_manager']//span";
	public static String MYTEAM_BILLABLE_CHECKBOX = "//label[@for='onlyBillable_id']//span";
	public static String MYTEAM_CLIENT_TB = "//*[@id=\"app\"]/div[2]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div/div[2]/div[1]/input";
	public static String MYTEAM_PROJECT_TB = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/div[1]/input[1]";
	public static String MYTEAM_EMPLOYEE_TB = "//*[@id=\"app\"]/div[2]/div[2]/div[1]/div/div[2]/div[3]/div[2]/div/div/div/div[2]/input";
	public static String MYTEAM_SEARCH_BTN = "//div[contains(text(),'Search')]";
	public static String MYTEAM_RESET_BTN = "//div[contains(text(),'Reset')]";
	public static String MYTEAM_CLIENT_ARROW_BTN = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/img[1]";
	public static String MYTEAM_PROJECT_ARROW_BTN = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[1]/div[2]/img[1]";
	public static String MYTEAM_EMPLOYEE_ARROW_BTN = "//div[@class='multiselect__select']";
	public static String MYTEAM_EMPLOYEE_SELECT_BTN = "//*[@id=\"app\"]/div[2]/div[2]/div[1]/div/div[2]/div[3]/div[2]/div/div/div/div[3]/ul/li[1]/span";
	public static String MYTEAM_SELECT_CHECKBOX = "//td[@class='checkbox-accordion-ele text-center']//input[@name='selectedTask']";
	public static String MYTEAM_SELECT_WEBTABLE = "//table[@class='manager-table emp-details-table']//tbody/tr";
	public static String MYTEAM_COMMENT_BOX = "//textarea[@id='comment-label']";
	public static String MYTEAM_CHECKUSER = "//label[@for='directReportees_id']/span";
	public static String MYTEAM_APPROVE_BTN = "//div[contains(text(),'Approve')]";
	public static String MYTEAM_REJECT_BTN = "//div[contains(text(),'Reject')]";
	public static String MYTEAM_CLIENT_DD_VALUES = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/div[3]";
	public static String MYTEAM_PROJECT_DD_VALUES = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/div[2]/div[3]";
	public static String MYTEAM_EMPLOYEES_DD_VALUES = "/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[4]/div[2]/div[1]/div[1]/div[1]/div[3]";
	public static String MYTEAM_EDITBUTTON = ".//*[@alt='Edit Icon']";
	public static String MYTEAM_COPYBUTTON = ".//*[@alt='Copy Icon']";
	public static String MYTEAM_EDITTEXTBOX = "//td[11]//input[1]";
	public static String MYTEAM_SAVEBUTTON = ".//div[contains(text(),'Save')]";
	public static String MYTEAM_CANCELBUTTON = ".//div[contains(text(),'Cancel')]";
	public static String MYTEAM_ERRORMESSAGE = "//div[@class='manager-error-msg']";
	public static String MYTEAM_COPYTEXTBOX = "//td[8]//input[1]";
	public static String PROJECT_DOWN_ICON = "//div[contains(@class,'col-12 flex-col p-0')]//img[contains(@class,'icon rotate-90 xs')]";
	public static String PROJECT_ITEM_LIST = "//*[@class='item']";
	public static String PROJECT_TEXT_BOX = "//div[contains(@class,'col-12 flex-col p-0')]//input[contains(@class,'txt-input')]";

	public static String TOTAL_LABEL = "//*[@class='subgrid div2 label-footer']//div[1]";

	public static String TABLE = "//table[@class='reports-table']//thead//tr";

	public static String SELECT_DEPARTMENT = "//span[contains(text(),'Select Department')]";
	public static String DEPARTMENT_ITEM_LIST = "//*[@class='item']";
	public static String DEPARTMENT_UP_ICON = "//div[contains(@class,'emp-card bg-white p-3')]//div[2]//div[2]//div[1]//div[1]//div[1]//div[2]//img[1]";
	public static String DEPARTMENT_DOWN_ICON = "//div[contains(@class,'emp-card bg-white p-3')]//div[2]//div[2]//div[1]//div[1]//div[2]//div[2]//img[1]";

	public static String SELECT_PRACTICE = "//span[contains(text(),'Select Practice')]";
	public static String PRACTICE_ITEM_LIST = "//*[@class='item']";
	public static String PRACTICE_UP_ICON = "//div[contains(@class,'flex-col col-8 m-2-top m-6-left')]//img[contains(@class,'icon rotate-90 xs')]";
	public static String PRACTICE_DOWN_ICON = "//div[contains(@class,'flex-col col-8 m-2-top m-6-left')]//img[contains(@class,'icon rotate-90-rev xs')]";

	public static String SELECT_LOCATION = "//span[contains(text(),'Select Location')]";
	public static String LOCATION_ITEM_LIST = "//*[@class='item']";
	public static String LOCATION_UP_ICON = "//div[@class='flex-col col-8 m-2-top m-5-left']//div[@class='multiselect__select']";
	public static String LOCATION_DOWN_ICON = "//div[@class='flex-col col-8 m-2-top m-5-left']//div[@class='multiselect__select']";

	public static String SELECT_EMPLOYEE = "//span[contains(text(),'Select Employees')]";
	public static String EMPLOYEE_ITEM_LIST = "//*[@class='item']";

	/**
	 * Missing Timesheet Reports page
	 */

	public static String REPORTS_MENU = ".//*[contains(text(),'Reports')]";
	public static String MISSING_TIMESHEET_REPORTS_SUBMENU = ".//*[contains(text(),'Missing Timesheet Report')]";
	public static String TIMESHEET_REPORT_SUBMENU = ".//*[contains(text(),'Timesheet Report')]";

	public static String TIMESHEET_PERIOD_CALENDAR = ".//*[@class='button  secondary icon-left']";
	public static String TIMESHEET_CALENDAR_RIGHT_ARROW = ".//*[@class='button  secondary m-2-left ']";
	public static String TIMESHEET_SEARCH_BTN = ".//*[contains(text(),'Search')]";
	public static String ENTRIES_PER_PAGE = ".//*[contains(text(),'entries per page')]";
	public static String REPORT_TABLE = ".//*[@class='reports-table']";
	public static String EMPLOYEE_NAME = "emp-name";
	public static String WEEKEND_ENTRIES = ".//*[@class='tooltip-report monthly-tooltip']";
	public static String DOWNLOAD_REPORTS = ".//*[contains(text(),'Download Report')]";
	public static String EMPLOYEE_ICON = "//div[contains(@class,'flex-col col-8 m-2-top m-3-left')]//div[contains(@class,'multiselect__select')]";
	public static String TR_DEPARTMENT_ICON = "//div[contains(@class,'flex-container m-6-top')]//div[2]//div[2]//div[2]//div[1]//div[1]//div[1]//div[2]//img[1]";
	// public static String TR_DEPARTMENT_UP_ICON =
	// "//div[contains(@class,'flex-container
	// m-6-top')]//div[2]//div[2]//div[2]//div[1]//div[1]//div[2]//div[2]//img[1]";
	public static String SELECT_PROJECT = "//span[contains(text(),'Project')]";
	public static String PROJECT_ICON = "//div[contains(@class,'col-12 flex-col p-0')]//img[@class='icon rotate-90 xs']";

	public static String TR_DEPARTMENT_ITEM_LIST = "//*[@id=\"app\"]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/div/div[2]/div[3]";
	public static String TR_DEPARTMENT_NAME = "//div[contains(text(),'Department')]";

	public static String TR_PRACTICE_ICON = "//div[contains(@class,'flex-col col-8 m-2-top m-6-left')]//img[contains(@class,'icon rotate-90 xs')]";
	public static String TR_PRACTICE_ITEM_LIST = "//*[@id=\"app\"]/div[2]/div[2]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[3]";
	public static String TR_PRACTICE_NAME = "//div[contains(text(),'Practice')]";

	public static String TR_LOCATION_ICON = "//div[contains(@class,'flex-col col-8 m-2-top m-5-left')]//div[contains(@class,'multiselect__select')]";
	public static String TR_LOCATION_ITEM_LIST = "//*[@id=\\\"app\\\"]/div[2]/div[2]/div/div[2]/div[4]/div[2]/div/div/div[3]/ul//li[@class='multiselect__element']";
	public static String TR_LOCATION_NAME = "//div[contains(text(),'Location')]";

	public static String TR_EMPLOYEE_ICON = "//div[contains(@class,'flex-col col-8 m-2-top m-3-left')]//div[contains(@class,'multiselect__select')]";
	public static String TR_EMPLOYEE_ITEM_LIST = "//*[@id=\"app\"]/div[2]/div[2]/div/div[2]/div[5]/div[2]/div/div/div[3]/ul//li[@class='multiselect__element']";
	public static String TR_EMPLOYEE_NAME = "//div[contains(text(),'Employee')]";

	public static String TR_GROUPTYPE_ICON = "//div[3]//div[2]//div[2]//div[1]//div[1]//div[1]//div[2]//img[1]";
	public static String TR_GROUPTYPE_ITEM_LIST = "//*[@id=\"app\"]/div[2]/div[2]/div/div[3]/div[2]/div[2]/div/div/div[2]/div[3]";
	public static String TR_GROUPTYPE_NAME = "//div[contains(text(),'Group Type')]";

	public static String TR_GROUPTASK_ICON = "//div[contains(@class,'flex-col col-8 m-2-top m-3-left')]//div[contains(@class,'multiselect__select')]";
	public static String TR_GROUPTASK_ITEM_LIST = "//*[@id=\"app\"]/div[2]/div[2]/div/div[2]/div[4]/div[2]/div/div/div[2]/div[3]";
	public static String TR_GROUPTASK_NAME = "//div[contains(text(),'Task Group')]";
	public static String TR_ACC_INDIA_TG_LIST = "//body/div[@id='app']/div/div[contains(@class,'flex-container m-6-top')]/div[contains(@class,'flex-row d-flex border-8 p-2-top p-5-bottom')]/div[3]/div[3]/div[2]/div[1]/div[1]/div[2]/div[3]//*[@class='item']";
	// public static String = data.get("");
	public static String TR_GROUPTYPE = "//div[contains(text(),'Accounts_India')]";
	
	
	/* ----------------- TIME SHEET APPROVAL PAGE LOCATORS ---------------------- */
	
	public static String TIMESHEET_APPV_LINK = ".//*[@href='timesheet/timesheetApproval']";
	public static String WEEK_START_DATE = "week-startdate";
	public static String WEEK_END_DATE = "week-enddate";
	public static String OPTIONS_CHECKBOX_GROUP = "q_option_checkboxgroup";
	public static String ALL_CHECKBOX = "q_option_checkboxgroup_allcheck";
	public static String EMP_SELECTOR_CONTAINER = "select2-q_employee-container";
	public static String EMP_SELECT_DROPDOWN = "select2-q_employee-results";
	public static String CLIENT_PROJECT_CHECKBOX = "q_clientproject";
	public static String OTHERS_CHECKBOX = "q_others";
	public static String SEARCH_BUTTON = "searchBtn";
	public static String RESET_BUTTON = "resetBtn";
	public static String TIMESHEET_STATUS = "timesheetStatus";
	public static String TIMESHEET_COMMENT = "timeSheetText";
	public static String TIMESHEET_RESULT_TABLE = "resultTable";
	public static String WARNING_MSG = ".message";
	public static String ALL_EMP_PARENT_CHECKBOX = ".//*[@name='chkSelectRow[]']";
	public static String SELECT_ALL_CHECKBOX = "ohrmList_chkSelectAll";
	
	
	/* ----------------- TIME SHEET LOGIN PAGE LOCATORS ---------------------- */
	
	public static String USERNAME = "//input[@type='email']";
	public static String USERNAME_NEXT_BUTTON = "//input[@value='Next']";
	public static String PASSWORD = "//input[@type='password']";
	public static String SIGNIN_BUTTON = "//input[@value='Sign in']";
	public static String SUBMIT_CONTINUE = "idSubmit_SAOTCC_Continue";
	public static String SUBMIT_NEXT = "idSIButton9";
	public static String MY_TEAM_MENU = "//div[contains(text(),'My Team')]";
	
	
	/* ----------------- TIME SHEET REPORT PAGE LOCATORS ---------------------- */
	public static String MENU_REPORTS = "menu__Reports";
	public static String RM_REPORTS_MENU = "menu_pim_ResourceManagementReports";
	public static String TIMESHEET_REPORTS_MENU = "menu_timesheet_timesheetReport";
	public static String YEARLY_REPORTS_CHECKBOX = ".//*[@name='q[yearly_report]']";
	public static String TIME_PERIOD = "q_period";
	public static String TIME_PERIOD_YEAR = ".//*[contains(@class, 'mtz-monthpicker-year')]";
	public static String YEAR_DROPDOWN = "select.mtz-monthpicker";
	public static String MONTH_DROPDOWN = "table.mtz-monthpicker";
	public static String DIRECT_REPORTEES = "q_reportees";
	public static String EMP_NAME = ".select2-search__field";
	public static String EMP_RESULT_CONTAINER = "select2-q_employees-results";
	public static String TASK_GROUP = "select2-q_group-container";
	public static String TASK_GROUP_DROPDOWN = "select2-q_group-results";
	public static String TASK_GROUP_DROPDOWN_SEARCH = "select2-search__field";
	public static String PROJECT_CONTAINER = "select2-q_project-container";
	public static String PROJECT_RESULT_DROPDOWN = "select2-q_project-results";
	public static String PRACTICE = "select2-q_practice-container";
	public static String PRACTICE_DROPDOWN = "select2-q_practice-results";
	public static String LOCATION = "select2-q_location-container";
	public static String LOCATION_RESULT_DROPDOWN = ".//*[@name='q[status][]']";
	public static String STATUS_CHECKBOX_LIST = "select2-q_project-results";
	public static String CLIENT_APPROVALS_CHECKBOX = "q_client_approvals";
	public static String REPORT_RESULTS_TABLE = "resultTable";
	public static String DOWNLOAD_BUTTON = ".ts-download";
	public static String SEARCH_BUTN = "searchBtn";







	
	
	
	
	}

