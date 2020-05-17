/**
 * 
 */
package com.techaspect.framework.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.framework.setup.TestSetUp;
import com.framework.testutils.Constants;
import com.framework.testutils.TestUtils;
import com.internal.page.OpportunityPage;

/**
 * @author Prabhudatta.C
 *
 */
public class OpportunityTest extends TestSetUp {

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testCreateNewOpportunity(Hashtable<String, String> data) {

		extentLogger().assignAuthor(Constants.AUTHOR1);
		extentLogger().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			appLogs.debug("RunMode is set to NO for testdata");
			throw new SkipException("RunMode is set to No for test data");
		}
		navigateToBaseURL();
		extentLogger().info("Navigate to url");
		OpportunityPage opage = new OpportunityPage().getOpportunityPage();
		opage.login(data);
		opage.createNewOpportunity(data);
	}

}
