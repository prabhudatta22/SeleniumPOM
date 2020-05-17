/**
 * 
 */
package com.tadigital.timesheet.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.framework.setup.TestSetUp;
import com.framework.testutils.Constants;
import com.framework.testutils.TestUtils;
import com.internal.regression.pages.MyTimePage;
import com.internal.regression.pages.TimeSheetLoginPage;

/**
 * @author dharmendra.h
 *
 */
public class CopyTimesheetTest extends TestSetUp {

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void copyTimeSheet(Hashtable<String, String> data) throws InterruptedException {
		testCaseLogger.get().assignAuthor("Dharmendra");
		testCaseLogger.get().assignCategory(Constants.SMOKE_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			appLogs.debug("RunMode is set to NO for testdata");
			throw new SkipException("RunMode is set to No for test data");
		}
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");

		TimeSheetLoginPage tlogin = new TimeSheetLoginPage().getTimeSeetLoginPage();
		tlogin.loginValid(data);
		testCaseLogger.get().info("Login to application");
		MyTimePage mytimepage = new MyTimePage().getMyTimePage();
		mytimepage.delete_Timesheet();
		mytimepage.copyTimesheet();
	}

}
