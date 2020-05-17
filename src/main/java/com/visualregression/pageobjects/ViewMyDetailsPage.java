/**
 ******************************************************************************
 * 							  	STAYING SHARP
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.visualregression.pageobjects;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.framework.testutils.DriverManager;

/**
 * The Class HomePage.
 */
public class ViewMyDetailsPage {

	public LoginPage clickOnLogin() {

		DriverManager.getDriver().findElement(By.linkText("Login")).click();
		Reporter.log(" Clicked on Login Button. ");
		return new LoginPage();
	}

	public String isLoginButtonPresent() {
		int size = DriverManager.getDriver().findElements(By.linkText("Login")).size();
		if (size > 0) {
			return "Login button is present on the page";
		} else {
			return "Login button is not present on the page";
		}
	}

	public LoginPage doLogout() {
		DriverManager.getDriver().findElement(By.id("welcome")).click();
		DriverManager.getDriver().findElement(By.linkText("Logout")).click();
		return new LoginPage();
	}

}
