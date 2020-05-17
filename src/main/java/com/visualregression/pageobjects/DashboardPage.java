/**
 ******************************************************************************
 * 							  	STAYING SHARP
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.visualregression.pageobjects;

import org.openqa.selenium.By;

import com.framework.testutils.DriverManager;

/**
 * The Class DashboardPage.
 */
public class DashboardPage {

	public ViewMyDetailsPage clickOnMyInfo() {
		DriverManager.getDriver().findElement(By.xpath("//*[@id='menu_pim_viewMyDetails']/b")).click();
		return new ViewMyDetailsPage();
	}

	public LoginPage doLogout() {
		DriverManager.getDriver().findElement(By.id("welcome")).click();
		DriverManager.getDriver().findElement(By.linkText("Logout")).click();
		return new LoginPage();
	}

}
