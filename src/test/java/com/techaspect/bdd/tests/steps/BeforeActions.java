/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							COPYRIGHTS TO TA Digital
 *							
 * *****************************************************************************
 */
package com.techaspect.bdd.tests.steps;

import com.framework.setup.TestSetUp;
import com.framework.testutils.DriverFactory;

//import org.testng.annotations.Parameters;

import cucumber.api.java.Before;

public class BeforeActions extends TestSetUp {

	@Before
	public void setUp() throws Exception {
		// initialize();
		beforeSuite();
		DriverFactory.createDriverInstance(configProperty.getProperty("browser"));
		// SeleniumDriver.setUpDriver();
	}
}