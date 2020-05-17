/**
 ******************************************************************************
 * 							  	STAYING SHARP
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.visualregression.pageobjects;

import org.openqa.selenium.By;

import com.framework.testutils.Constants;
import com.framework.testutils.DriverManager;

/**
 * The Class LoginPage.
 */
public class LoginPage {

	public String doLogin(String username, String password) throws InterruptedException {
		String actual;
		DriverManager.getDriver().findElement(By.id(Constants.OR_USERNAME)).sendKeys(username);
		DriverManager.getDriver().findElement(By.id(Constants.OR_PASS)).sendKeys(password);
		DriverManager.getDriver().findElement(By.id(Constants.OR_LOGIN)).click();
		actual = DriverManager.getDriver().findElement(By.id(Constants.OR_AUTHENTICATED_USER)).getText();
		return actual;
	}

	public String isLogoPresent() {
		int size = DriverManager.getDriver().findElements(By.xpath(Constants.OR_LOGO)).size();
		if (size > 0) {
			return "Logo is present on the page";
		} else {
			return "Logo is not present on the page";
		}
	}

}
