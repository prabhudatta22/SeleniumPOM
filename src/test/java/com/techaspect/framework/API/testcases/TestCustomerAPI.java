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

public class TestCustomerAPI extends TestSetUp {

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
				System.out.println("DataArray email is: " + jsonResponse.get(dataArray + ".email"));
				System.out.println("DataArray Sources URL is: " + jsonResponse.get(dataArray + ".sources.url"));
				System.out.println("DataArray Sources Size is: " + jsonResponse.get(dataArray + ".sources.size()"));

			}
		}

		/*
		 * List<Map<String,?>> listOfData = jsonResponse.get("data");
		 * for(Map<String,?>list:listOfData){
		 * System.out.println(list.get("id")); if
		 * (list.get("id").equals(data.get("id"))) {
		 * System.out.println(list.get("email"));
		 * //System.out.println(list.get("sources.url")); }
		 * 
		 * }
		 */
		Assert.assertEquals(Integer.toString(response.getStatusCode()), data.get("expectedStatusCode"));
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
