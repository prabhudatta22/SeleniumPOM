/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.testutils;

public interface Constants {

	/*
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------- FRAMEWORK
	 * CONSTANTS
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------
	 */
	public String ROOT_DIR = "user.dir";
	public String SCREENSHOTS_FOLDER = "\\screenshots\\";
	public String AUTHOR1 = "Prabhudatta";
	public String REGRESSION_CATEGORY = "Regression";
	public String SMOKE_CATEGORY = "Smoke";
	public String ACCEPTANCE_CATEGORY = "Acceptance";

	/*
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------- SONARQUBE
	 * CONSTANTS
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------
	 */
	public String SELENIUM_GRID = "Selenium_GRID_URL";
	public String EXECUTION_ENV = "executionEnvironment";
	public String BROWSER = "browser";
	public String PLATFORM = "platform";
	public String VERSION = "version";
	public String OS_VERSION = "os_version";
	public String BROWSERSTACK_LOCAL = "browserstack.local";
	public String BROWSERSTACK_LOCAL_VALUE_FALSE = "false";
	public String BROWSERSTACK_LOCAL_VALUE_TRUE = "true";
	public String FIREFOX = "FIREFOX";
	public String CHROME = "CHROME";
	public String IE = "IE";
	public String GRID_URL = "http://localhost:4444/wd/hub"; // 192.168.2.92

	/*
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------- REPORTING
	 * CONSTANTS
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------
	 */
	public String OR_ACTUAL_TITLE = "Actual Title: ";
	public String OR_START_FONT_TAG = "<font color=";
	public String OR_END_FONT_TAG = "</font>";

	/*
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------- CLOUD
	 * RELATED CONSTANTS
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------
	 */
	public String SAUCE_USERNAME = "ram.pushpala";
	public String SAUCE_ACCESS_KEY = "b177d05e-599d-48d4-bb13-59df1b24b42c";
	public String SAUCE_URL = "http://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	public String SAUCE_TAGS = "SauceTest";
	public String SAUCE_IE_VERSION = "15.15063";
	public String SAUCE_APPIUM_VERSION = "1.8.1";
	public String SAUCE_DEVICE_NAME = "Samsung Galaxy Nexus GoogleAPI Emulator";
	public String SAUCE_DEVICE_ORIENTATION = "portrait";
	public String SAUCE_ANDROID_PLATFORM_VERSION = "4.4";
	public String SAUCE_ANDROID_PLATFORM_NAME = "Android";

	public String BROWSERSTACK_USERNAME = "techaspect2";
	public String BROWSERSTACK_ACCESS_KEY = "QRo4dhjzZ7CzopFGJKZH";
	public String BROWSERSTACK_URL = "https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";
	public String BROWSERSTACK_TAGS = "BrowserStackTest";
	public String BROWSERSTACK_OS = "Windows";
	public String BROWSERSTACK_OS_VERSION = "10";
	public String BROWSERSTACK_IE_VERSION = "17.0";
	public String BROWSERSTACK_NETWORK_LOGS = "browserstack.networkLogs";
	public String BROWSERSTACK_DEBUG = "browserstack.debug";

	public String SEETEST_USERNAME = "maruthip@techaspect.com";
	public String SEETEST_PASS = "Testing@123";
	public String SEETEST_ACCESS_KEY = "eyJ4cC51IjoxMDg2NDM3LCJ4cC5wIjoxMDg2NDM2LCJ4cC5tIjoiTVRVek16azVOemc0Tmprd09RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4NDkzNTc4ODgsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.L17fidN9BxB5dk1Le5LZn6XhDlvdeQeSYww03voDW80";
	public String SEETEST_CHROME_VERSION = "66";
	public String SEETEST_CHROME_PLATFORM = "WIN10";
	public String SEETEST_FIREFOX_PLATFORM = "WIN10";
	public String SEETEST_FIREFOX_VERSION = "57.0.2";
	public String SEETEST_IE_PLATFORM = "WIN10";
	public String SEETEST_IE_VERSION = "11";

	public String CHROME_PLATFORM = "Windows 10";
	public String CHROME_VERSION = "67.0";
	public String FIREFOX_PLATFORM = "Windows 10";
	public String FIREFOX_VERSION = "57.0";
	public String IE_PLATFORM = "Windows 7";

	/*
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------- LOCATORS
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------
	 */

	/* Base Page */
	public int DRIVER_TIME_OUT = 50;

	/* Landing Page */
	public String OR_EMAIL = "email";
	public String OR_PASS = "pass";
	public String OR_LOGIN = "//input[@value='Log In']";
	public String OR_FIRSTNAME = "firstname";
	public String OR_LASTNAME = "lastname";
	public String OR_REG_EMAIL = "reg_email__";
	public String OR_REG_PASS = "reg_passwd__";

	/* HomePage */
	public String OR_FOOTER_DIV = "footer";
	public String OR_FOOTER_DIV2 = "div.logo > a > img";
	public String OR_ICON_FACEBOOK = "//footer[@id='footer']/div/div/div[2]/a[1]/span";
	public String OR_ICON_FACEBOOK2 = "//html/body/div[1]/div[2]/footer/div[2]/div/div/div[1]/ul/li[1]/a/i";
	public String OR_ICON_TWITTER = "//footer[@id='footer']/div/div/div[2]/a[2]/span";
	public String OR_ICON_TWITTER2 = "//html/body/div[1]/div[2]/footer/div[2]/div/div/div[1]/ul/li[2]/a/i";
	public String OR_ICON_LINKEDIN = "//footer[@id='footer']/div/div/div[2]/a[3]/span";
	public String OR_ICON_INSTAGRAM2 = "//html/body/div[1]/div[2]/footer/div[2]/div/div/div[1]/ul/li[3]/a/i";

	public String OR_LOGO = "//img[@alt='image not displaying']";
	public String OR_LOGO2 = "//*[@id='page-header']/div/div/div[1]/div[1]/figure/a/img";

	/* In Progress */
	public String OR_REGISTER_FIRSTNAME = "//input[@placeholder='First Name']";
	public String OR_REGISTER_LASTNAME = "//input[@placeholder='Last Name']";
	public String OR_REGISTER_PHONENUMBER = "//input[@type='tel']";
	public String OR_REGISTER_MALE = "";
	public String OR_REGISTER_FEMALE = "";

	public String OR_REGISTER_HOBBIES_CRICKET = "";
	public String OR_REGISTER_HOBBIES_MOVIES = "";
	public String OR_REGISTER_HOBBIES_HOCKEY = "";

	public String OR_REGISTER_LANGUAGES = "";

	public String OR_REGISTER_PASS = "";
	public String OR_REGISTER_CONFIRM_PASS = "";

	public String OR_REGISTER_SUBMIT = "";

	// TAQA CLOSE INFO
	public String OR_TA_QA_INFO = "//*[@id='sfWebDebugDetails']/li[6]/a/img";

	// LOGIN PAGE LOGO
	public String OR_LOGO_VIR = "//*[@id='divLogo']/img";

	// LOGIN CONSTANTS
	public String OR_USERNAME = "txtUsername";
	public String OR_PASS_VIR = "txtPassword";
	public String OR_LOGIN_VIR = "btnLogin";
	public String OR_AUTHENTICATED_USER = "welcome";
}
