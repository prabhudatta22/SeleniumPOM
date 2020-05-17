/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							COPYRIGHTS TO TA Digital
 *							
 * *****************************************************************************
 */
package com.techaspect.bdd.tests.steps;

import java.util.Hashtable;

//import java.util.List;

import org.testng.Assert;
//import org.testng.annotations.Test;

import com.framework.setup.TestSetUp;
import com.internal.regression.pages.TimeSheetApprovalPage;
import com.internal.regression.pages.TimeSheetLoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SampleOneSteps extends TestSetUp {

	@Given("^I am on the Home page \"([^\"]*)\" of Home Page website$")
	public void i_am_on_the_Home_page_of_Home_Page_website(String websiteURL, String usename, String password)
			throws Throwable {

		Hashtable<String, String> data = new Hashtable<>();
		data = prepareTestData(usename, password);
		navigateToURL(websiteURL);
		TimeSheetLoginPage tlogin = new TimeSheetLoginPage().getTimeSeetLoginPage();
		tlogin.loginValid(data);
		testCaseLogger.get().info("Login to application");

	}

	@When("^I move to MenuOne$")
	public void i_move_to_MenuOne() throws Throwable {

		TimeSheetApprovalPage tsApp = new TimeSheetApprovalPage().open();
		tsApp.validateAllPresence();
	}

	@Then("^I should see the \"([^\"]*)\" text on MenuOne$")
	public void i_should_see_the_text_on_MenuOne(String expectedMenuText) throws Throwable {
		// code here and assertion below
		boolean result = true;
		try {
			Assert.assertEquals(result, true, "Title didn't match");
		} catch (Throwable t) {
			// code to report the error in testng
			throw t;
		}
		// throw new PendingException();
	}

}
