package com.techaspect.framework.API.testcases;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.framework.api.CustomerAPI;
import com.framework.setup.TestSetUp;
import com.framework.testutils.DataProviderClass;
import com.framework.testutils.TestUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestCustomerAPINew extends TestSetUp {

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "dpone") // ,
																				// priority=2)
	public void testRetriveAllCustomers(Hashtable<String, String> data) {
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			appLogs.debug("RunMode is set to NO for testdata");
			throw new SkipException("RunMode is set to No for test data");
		}
		extentLogger().assignAuthor("maruthip");
		extentLogger().assignCategory("Smoke");

		Response response = CustomerAPI.retriveAllCustomers(data);
		extentLogger().log(Status.INFO, "Called API to retrieve all customers");
		TestUtils.appendResponseInReport(response);
		System.out.println(response.asString());
		JsonPath jsonResponse = response.jsonPath();
		int numberOfCustomers = jsonResponse.get("data.size()");
		extentLogger().log(Status.INFO, "Number of Customers: " + numberOfCustomers);

		for (int i = 0; i < numberOfCustomers; i++) {
			String dataArray = "data[" + i + "]";
			if (jsonResponse.get(dataArray + ".id").equals(data.get("id"))) {
				jsonResponse.get(dataArray + ".email");
			}
		}

	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "dpone") // ,
																				// priority=3)
	public void testCreateCustomer(Hashtable<String, String> data) {
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			appLogs.debug("RunMode is set to NO for testdata");
			throw new SkipException("RunMode is set to No for test data");
		}
		extentLogger().assignAuthor("maruthip");
		extentLogger().assignCategory("Production");

		Response response = CustomerAPI.createCustomer(data);
		TestUtils.appendResponseInReport(response);
		// System.out.println(response.asString());

		JsonPath jsonResponse = response.jsonPath();

		System.out.println("Response email id: " + jsonResponse.get("email"));
		// assertThat(jsonResponse.get("email")).isEqualTo(data.get("email"));

		System.out.println("Response subscription URL: " + jsonResponse.get("subscriptions.url"));
		assertThat(Integer.toString(response.getStatusCode())).isEqualTo(data.get("expectedStatusCode"));
	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "dpone") // ,
																				// priority=1)
	public void testDeleteACustomer(Hashtable<String, String> data) {
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			appLogs.debug("RunMode is set to NO for testdata");
			throw new SkipException("RunMode is set to No for test data");
		}
		extentLogger().assignAuthor("maruthip");
		extentLogger().assignCategory("Regression");

		Response response = CustomerAPI.deleteACustomer(data);
		extentLogger().log(Status.INFO, "Called API to Delete " + data.get("customerId") + " customer.");
		TestUtils.appendResponseInReport(response);
		System.out.println(response.asString());
		Assert.assertEquals(Integer.toString(response.getStatusCode()), data.get("expectedStatusCode"));
	}

}
