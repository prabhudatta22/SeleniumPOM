/**
 * 
 */
package com.internal.api;

import java.util.Set;

import org.openqa.selenium.Cookie;

import com.framework.api.APIPage;

import io.restassured.response.Response;

/**
 * @author Prabhudatta.C
 *
 */
public class TimeSheetEmailAPIPage extends APIPage {

	/**
	 * 
	 */
	public void getWeeklySubmissionReport(Set<Cookie> cookie) {

		Response res = APIPage.getWithCookie("/getWeeklySubmissionReports", null, cookie);
		System.out.println(res.asString());
		System.out.println(res.getStatusCode());
	}

}
