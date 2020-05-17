package com.framework.api;

import java.util.Hashtable;

import com.framework.setup.TestSetUp;
import com.framework.testutils.TestUtils;

import io.restassured.response.Response;

public class CustomerAPI extends TestSetUp {

	public static Response createCustomer(Hashtable<String, String> data) {
		requestSpecCreate = TestUtils.SetFormParm(data.get("formParam"), requestSpecCreate);
		return requestSpecCreate.request(data.get("methodType"), data.get("endPoint")).then().extract().response();
	}

	public static Response retriveAllCustomers(Hashtable<String, String> data) {
		return requestSpec.request(data.get("methodType"), (data.get("endPoint"))).then().extract().response();

	}

	public static Response deleteACustomer(Hashtable<String, String> data) {
		return requestSpec.request(data.get("methodType"), data.get("endPoint") + "/" + data.get("customerId")).then()
				.extract().response();

	}
}
