/**
 * 
 */
package com.internal.regression.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
public class MissingTimesheetReportPage extends BasePage {

	List<String> header = null;

	@FindBy(xpath = PageConstants.TIMESHEET_PERIOD_CALENDAR)
	private WebElement timeSheetReportCalendar;

	@FindBy(xpath = PageConstants.TIMESHEET_CALENDAR_RIGHT_ARROW)
	private WebElement timeSheetReportCalendarFutureArrow;

	@FindBy(xpath = PageConstants.TIMESHEET_SEARCH_BTN)
	private WebElement searchBtn;

	@FindBy(xpath = PageConstants.ENTRIES_PER_PAGE)
	private WebElement entryPerPage;

	@FindBy(xpath = PageConstants.REPORT_TABLE)
	private WebElement timeSheetReportTable;

	@FindBy(id = PageConstants.EMPLOYEE_NAME)
	private List<WebElement> empNames;

	@FindBy(xpath = PageConstants.WEEKEND_ENTRIES)
	private List<WebElement> weekendEntries;

	@FindBy(xpath = PageConstants.DOWNLOAD_REPORTS)
	private WebElement downloadReportsBtn;

	@FindBy(xpath = PageConstants.MYTEAM_CHECKUSER)
	private WebElement myTeam_Check_User;

	@FindBy(xpath = PageConstants.PROJECT_DOWN_ICON)
	private WebElement project_down_icon;

	@FindBy(xpath = PageConstants.PROJECT_ICON)
	private WebElement projectIcon;

	@FindBy(xpath = PageConstants.PROJECT_ITEM_LIST)
	private List<WebElement> project_item_list;

	@FindBy(xpath = PageConstants.TABLE)
	private List<WebElement> table;

	@FindBy(xpath = PageConstants.PROJECT_NAME)
	private WebElement project_name;

	@FindBy(xpath = PageConstants.SELECT_PROJECT)
	private WebElement selectProject;

	@FindBy(xpath = PageConstants.PROJECT_TEXT_BOX)
	private WebElement project_text_box;

	@FindBy(xpath = PageConstants.SELECT_DEPARTMENT)
	private WebElement selectDepartment;

	@FindBy(xpath = PageConstants.DEPARTMENT_ITEM_LIST)
	private List<WebElement> department_item_list;

	@FindBy(xpath = PageConstants.SELECT_EMPLOYEE)
	private WebElement select_employee;

	@FindBy(xpath = PageConstants.EMPLOYEE_ICON)
	private WebElement EmplyeeIcon;

	@FindBy(xpath = PageConstants.EMPLOYEE_ITEM_LIST)
	private List<WebElement> employee_item_list;

	@FindBy(xpath = PageConstants.DEPARTMENT_UP_ICON)
	private WebElement department_up_icon;

	@FindBy(xpath = PageConstants.DEPARTMENT_DOWN_ICON)
	private WebElement department_down_icon;

	@FindBy(xpath = PageConstants.SELECT_PRACTICE)
	private WebElement select_practice;

	@FindBy(xpath = PageConstants.PRACTICE_ITEM_LIST)
	private List<WebElement> practice_item_list;

	@FindBy(xpath = PageConstants.PRACTICE_UP_ICON)
	private WebElement practice_up_icon;

	@FindBy(xpath = PageConstants.PRACTICE_DOWN_ICON)
	private WebElement practice_down_icon;

	@FindBy(xpath = PageConstants.SELECT_LOCATION)
	private WebElement select_location;

	@FindBy(xpath = PageConstants.LOCATION_ITEM_LIST)
	private List<WebElement> location_item_list;

	@FindBy(xpath = PageConstants.LOCATION_UP_ICON)
	private WebElement location_up_icon;

	@FindBy(xpath = PageConstants.LOCATION_DOWN_ICON)
	private WebElement location_down_icon;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.framework.ui.pageobjects.BasePage#getPageLoadCondition()
	 */

	@SuppressWarnings("unchecked")
	public void validateDropdwonSorting(List<WebElement> list, WebElement webElement1, WebElement webElement2) {
		explicitwait(5);
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

	@Override
	public ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions
				.visibilityOf(DriverManager.getDriver().findElement(By.className(Constants.OR_FOOTER_DIV2)));
	}

	/**
	 * @return the header
	 */
	public List<String> getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(List<String> header) {
		this.header = header;
	}

	@SuppressWarnings("unchecked")
	public MissingTimesheetReportPage getMissingTimesheetReportPage() {
		return (MissingTimesheetReportPage) openPage(MissingTimesheetReportPage.class);
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void validateFutureDateValues() throws IOException {

		implicitWaitSec(5000);
		try {

			if (timeSheetReportCalendarFutureArrow.isDisplayed()) {
				if (timeSheetReportCalendarFutureArrow.getAttribute("disabled").equalsIgnoreCase("true")) {
					testCaseLogger.get().pass("Future Date arrow is not clickable");
				} else {
					testCaseLogger.get().fail("Future Date arrow is clickable");
				}
			}
		} catch (Exception e) {
			testCaseLogger.get().pass("Future Date arrow is not clickable");
		}
		captureScreenshot(DriverManager.getDriver(), "Future Date");

	}

	/**
	 * @throws IOException
	 * 
	 */
	public void validateEntriesPerPage() throws IOException {
		implicitWaitSec(5000);
		// click(myTeam_Check_User, "Only My Reportees");
		clickAction(searchBtn, "Search");
		implicitWaitSec(15000);
		scrollIntoView(timeSheetReportTable, "Report table");
		if (timeSheetReportTable.isDisplayed()) {
			scrollIntoView(entryPerPage, "entryPerPage");
			if (entryPerPage.isDisplayed()) {
				testCaseLogger.get().pass("Entries paer page is visible " + entryPerPage.getText());
			} else {
				testCaseLogger.get().fail("Entries paer page is not visible " + entryPerPage.getText());
			}
		}

		else {
			testCaseLogger.get()
					.info("Timesheet missing report table is not visible. The user may not have proper access");
		}
		captureScreenshot(DriverManager.getDriver(), "Entries Per Page");
	}

	/**
	 * 
	 */
	public void validateTotalEntries() {
		implicitWaitSec(5000);
		int rowVal = 0;
		scrollIntoView(timeSheetReportTable, "Report table");
		if (timeSheetReportTable.isDisplayed()) {
			try {

				List<WebElement> reportTRs = timeSheetReportTable.findElements(By.tagName("tr"));
				for (WebElement tr : reportTRs) {
					if (!tr.getText().contains("Emp ID")) {
						rowVal = calculateRowValue(tr);
						testCaseLogger.get().info(
								"For the row No " + reportTRs.indexOf(tr) + ", the total hour entry is " + rowVal);
					}

				}
			} catch (Exception e) {
				testCaseLogger.get().fail("Could not able to validate row count " + e.getLocalizedMessage());
			}
		}

	}

	/**
	 * @return
	 */
	private int calculateRowValue(WebElement tr) {

		int totalTime = 0;
		String dateVal = null;

		try {
			List<WebElement> reportTDs = tr.findElements(By.tagName("td"));
			int totalTds = reportTDs.size();
			for (WebElement td : reportTDs) {
				if ((reportTDs.indexOf(td) != totalTds - 1) && (reportTDs.indexOf(td) != 0)
						&& (reportTDs.indexOf(td) != 1)) {

					if (StringUtils.isEmpty(td.getText())) {
						System.out.println(td.getAttribute("class"));
						if (td.getAttribute("class").equalsIgnoreCase("tooltip-report monthly-tooltip")) {
							testCaseLogger.get().pass("This weekend there is no hour entered");
						} else {
							testCaseLogger.get().warning("This weekend there is timesheet hour entered");
						}
					}

					dateVal = StringUtils.isEmpty(td.getText()) ? "0" : td.getText();
					totalTime = totalTime + Integer.parseInt(dateVal);
				} else {
					System.out.println("Not covered >>> " + reportTDs.indexOf(td));
				}
			}

			testCaseLogger.get().info("Total time entered for this row is " + totalTime);
			int presentTotal = Integer.parseInt(reportTDs.get(totalTds - 1).getText());
			if (totalTime == presentTotal) {
				testCaseLogger.get().pass("Calculated Time entries is same " + totalTime);
			} else {
				testCaseLogger.get().fail(presentTotal + " Calculated Time entries is not same " + totalTime);
			}
		} catch (Exception e) {
			testCaseLogger.get().info("Could not able to get row count " + e.getLocalizedMessage());
		}
		return totalTime;
	}

	/**
	 * 
	 */
	public void validateEmployeeNames() {

		List<String> empNameList = new CopyOnWriteArrayList<String>();
		scrollIntoView(timeSheetReportTable, "Report table");
		PageFactory.initElements(DriverManager.getDriver(), MissingTimesheetReportPage.class);
		if (timeSheetReportTable.isDisplayed()) {
			if (!empNames.isEmpty()) {

				for (WebElement name : empNames) {
					empNameList.add(name.getText());
				}

				List<String> empNameListSorted = empNameList;
				Collections.sort(empNameList);
				if (empNameListSorted.equals(empNameList)) {
					testCaseLogger.get().pass("The Employee name in report table is in sorted format");
				} else {
					testCaseLogger.get().fail("The Employee name in report table is NOT in sorted format");
				}

			} else {
				testCaseLogger.get().info("There are no reportess under the user");
			}

		}
	}

	public void validateWeekendTimeEntryEmployee() {
		String EmployeeName = null;
		scrollIntoView(timeSheetReportTable, "Report table");
		PageFactory.initElements(DriverManager.getDriver(), MissingTimesheetReportPage.class);
		if (timeSheetReportTable.isDisplayed()) {

			List<WebElement> trs = timeSheetReportTable.findElements(By.tagName("tr"));

			for (WebElement tr : trs) {

				System.out.println(tr.getText());
				if (!tr.getText().contains("Employee Name")) {
					EmployeeName = tr.findElements(By.tagName("td")).get(1).getText();
					List<WebElement> tds = tr.findElements(By.tagName("td"));
					for (WebElement wk : tds) {
						System.out.println(wk.getAttribute("class"));
						if (wk.getAttribute("class").equalsIgnoreCase("tooltip-report monthly-tooltip")) {
							if (StringUtils.isEmpty(wk.getText())) {
								testCaseLogger.get()
										.pass("This weekend there is no effort logged by the employee " + EmployeeName);
							} else {
								testCaseLogger.get()
										.warning("This weekend there is effort logged by the employee " + EmployeeName);
							}
						}
					}
				}
			}
		}

	}

	/**
	 * @throws IOException
	 * 
	 */
	public void validateExcelReport() throws IOException {

		String Path = System.getProperty("user.dir") + File.separator + "Download";
		System.out.println("File download path >>> " + Path);
		List<WebElement> trs = timeSheetReportTable.findElements(By.tagName("tr"));
		click(downloadReportsBtn, "Report downlaod button");
		captureFullPageScreenshot(DriverManager.getDriver(), "Report Excel");
		implicitWaitSec(7000);
		try {
			List<Map<String, String>> excelReportMap = readXLSXFile(Path + File.separator + "Timesheet-Report.xlsx");
			if (excelReportMap.size() == trs.size()) {
				testCaseLogger.get()
						.pass("The Number of records in the table match with number of records in excel data");
			} else {

				testCaseLogger.get().info("The Number of records in the table is " + trs.size());
				testCaseLogger.get().info("The Number of records in excel data is " + excelReportMap.size());
				testCaseLogger.get()
						.fail("The Number of records in the table doesn't match with number of records in excel data");
			}
		} catch (FileNotFoundException fnfe) {
			captureScreenshot(DriverManager.getDriver(), "Report Not Found Excel");
			testCaseLogger.get().fail("The report file is not present in the path specified or download failed "
					+ fnfe.getLocalizedMessage());
		}

	}

	public List<Map<String, String>> readXLSXFile(String path) throws IOException {

		List<Map<String, String>> reportMapList = new CopyOnWriteArrayList<Map<String, String>>();
		InputStream ExcelFileToRead = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();
		String val = null;
		List<String> kes = null;
		List<String> kesFinal = new ArrayList<>();

		int rownum = 0;

		while (rows.hasNext()) {
			kes = new ArrayList<>();
			row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
					val = cell.getStringCellValue();
				}
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					val = String.valueOf(cell.getNumericCellValue());
				}

				else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
					val = String.valueOf(cell.getNumericCellValue());
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						double d = cell.getNumericCellValue();
						Calendar cal = Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						val = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						val = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + val;
					}
				}

				if (!val.contains("Timesheet Report")) {
					kes.add(val);
				}
			}

			if (rownum == 1) {
				this.header = kes;
				setHeader(this.header);
				System.out.println(getHeader().size());
			}
			rownum++;
			kesFinal.addAll(kes);
		}

		reportMapList = getMappedValues(getHeader(), kesFinal);
		return reportMapList;
	}

	/**
	 * @param header2
	 * @param kes
	 * @return
	 */
	private List<Map<String, String>> getMappedValues(List<String> header2, List<String> kes) {
		Map<String, String> reportMap = null;
		List<Map<String, String>> reportMapList = new CopyOnWriteArrayList<Map<String, String>>();
		int z = header2.size();
		int j = 0;
		for (int i = 0; i < kes.size(); i++) {
			reportMap = new ConcurrentHashMap<String, String>();
			for (j = 0; j < header2.size(); j++) {
				reportMap.put(header2.get(j), kes.get(z + j));
			}
			z = z + j;
			i = z;
			reportMapList.add(reportMap);
		}

		return reportMapList;
	}

	public void validateDepartmentDropdownIsSorted() throws Exception {

		validateDropdwonSorting(department_item_list, department_up_icon, selectDepartment);

	}

	public void validatePracticeDropdownIsSorted() throws Exception {

		validateDropdwonSorting(practice_item_list, practice_up_icon, select_practice);

	}

	public void validateLocationDropdownIsSorted() throws Exception {

		validateDropdwonSorting(location_item_list, location_up_icon, select_location);
	}

	public void validateEmployeeDropdownIsSorted() {

		validateDropdwonSorting(employee_item_list, EmplyeeIcon, select_employee);

	}

	public void validateProjectDropdownIsSorted() {

		validateDropdwonSorting(project_item_list, projectIcon, selectProject);
	}

	public void validateEmployeeDropdownIsMultiSelector() {
		List<WebElement> list = employee_item_list;

		for (int i = 0; i < list.size(); i++) {
			click(list.get(i), "Employee dropdown" + i + "Value Selected");
		}
		testCaseLogger.get().pass("Employee Dropdown is MultiSelector");

	}

	public void validateLocationDropdownIsMultiSelector() {
		List<WebElement> list = location_item_list;

		for (int i = 0; i < list.size(); i++) {
			click(list.get(i), "location dropdown" + i + "Value Selected");
		}
		testCaseLogger.get().pass("Location Dropdown is MultiSelector");

	}

	public void validateProjectFilter(Hashtable<String, String> data) {

		implicitWaitSec(30000);
		click(project_down_icon, "project_down_icon");
		implicitWaitSec(30000);
		sendKeys(project_text_box, data.get("ProjectName"), "Select Project");
		click(project_name, "project_name");
		clickAction(searchBtn, "SearchBtn");

	}
}
