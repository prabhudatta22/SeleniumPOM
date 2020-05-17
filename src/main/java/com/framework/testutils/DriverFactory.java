/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.testutils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.grid.selenium.GridLauncherV3;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.SkipException;

import com.framework.setup.TestSetUp;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	static String targetUrl = "";
	static DesiredCapabilities caps = null;

	private DriverFactory() {
	}

	/**
	 * This method is to create a driver instance for what is configured in
	 * configuration file.
	 * 
	 * @param browserName
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public static WebDriver createDriverInstance(String browserName)
			throws MalformedURLException, InterruptedException {
		WebDriver driver = null;
		String portNo = "5555";
		String downloadFilepath = System.getProperty("user.dir") + File.separator + "Download";
		// setUpGridHub();
		if (TestSetUp.configProperty.getProperty(Constants.EXECUTION_ENV).contains("Windows")) {
			if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				WebDriverManager.firefoxdriver().timeout(50);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAssumeUntrustedCertificateIssuer(false);

				// Download setting
				profile.setPreference("browser.download.folderlist", 2);
				profile.setPreference("browser.helperapps.neverAsk.saveToDisk", "jpeg");
				profile.setPreference("browser.download.dir", downloadFilepath);

				FirefoxOptions options = new FirefoxOptions();
				options.setProfile(profile);

				caps = DesiredCapabilities.firefox();
				caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);

				portNo = "5568";
				if (!TestSetUp.configProperty.get("SELENIUM_GRID").toString().equalsIgnoreCase("true")) {
					driver = new FirefoxDriver(caps);
				}

			} else if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.password_manager_enabled", "false");
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilepath);
				// chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting",
				// 1);
				// chromePrefs.put("safebrowsing.enabled", "true");
				// chromePrefs.put("download.prompt_for_download", "false");

				ChromeOptions options = new ChromeOptions();
				// options.addArguments("download.directory_upgrade", "true");
				// options.addArguments("download.prompt_for_download", "false");
				// options.addArguments("download.default_directory", downloadFilepath);
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--incognito");

				caps = DesiredCapabilities.chrome();
				caps.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
				caps.setCapability("disable-popup-blocking", true);
				caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				caps.setCapability("chrome.prefs", chromePrefs);
				caps.setCapability("chrome.setProxyByServer", false);
				caps.setCapability("Connnection", "keep-alive");
				caps.setCapability(ChromeOptions.CAPABILITY, options);

				portNo = "5566";

				if (!TestSetUp.configProperty.get("SELENIUM_GRID").toString().equalsIgnoreCase("true")) {
					driver = new ChromeDriver(caps);
				}
			} else if (browserName.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

				portNo = "5577";
				if (!TestSetUp.configProperty.get("SELENIUM_GRID").toString().equalsIgnoreCase("true")) {
					driver = new InternetExplorerDriver(caps);
				}

			}

			else if (browserName.equalsIgnoreCase("edge")) {
				portNo = "5588";
				WebDriverManager.edgedriver().setup();
				caps = DesiredCapabilities.edge();
				caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

				if (!TestSetUp.configProperty.get("SELENIUM_GRID").toString().equalsIgnoreCase("true")) {
					driver = new EdgeDriver(caps);
				}
			}

			targetUrl = Constants.GRID_URL;

		} else if (TestSetUp.configProperty.getProperty(Constants.EXECUTION_ENV).contains("Saucelabs")) {
			DesiredCapabilities caps = null;

			String sauceEnv = TestSetUp.configProperty.getProperty(Constants.BROWSER).toUpperCase();
			switch (sauceEnv) {
			case Constants.CHROME:
				caps = DesiredCapabilities.chrome();
				caps.setCapability(Constants.PLATFORM, Constants.CHROME_PLATFORM);
				caps.setCapability(Constants.VERSION, Constants.CHROME_VERSION);
				caps.setCapability("name", Constants.CHROME_PLATFORM + "-"
						+ TestSetUp.configProperty.getProperty(Constants.BROWSER) + "-" + Constants.CHROME_VERSION);
				caps.setCapability("tags", Constants.SAUCE_TAGS);
				break;
			case Constants.FIREFOX:
				caps = DesiredCapabilities.firefox();
				caps.setCapability(Constants.PLATFORM, Constants.FIREFOX_PLATFORM);
				caps.setCapability(Constants.VERSION, Constants.FIREFOX_VERSION);
				caps.setCapability("name", Constants.FIREFOX_PLATFORM + "-"
						+ TestSetUp.configProperty.getProperty(Constants.BROWSER) + "-" + Constants.FIREFOX_VERSION);
				caps.setCapability("tags", Constants.SAUCE_TAGS);
				break;
			case Constants.IE:
				caps = DesiredCapabilities.edge();
				caps.setCapability(Constants.PLATFORM, Constants.IE_PLATFORM);
				caps.setCapability(Constants.VERSION, Constants.SAUCE_IE_VERSION);
				caps.setCapability("name", Constants.IE_PLATFORM + "-"
						+ TestSetUp.configProperty.getProperty(Constants.BROWSER) + "-" + Constants.SAUCE_IE_VERSION);
				caps.setCapability("tags", Constants.SAUCE_TAGS);
				break;
			default:
				break;
			}

			targetUrl = Constants.SAUCE_URL;
		} else if (TestSetUp.configProperty.getProperty(Constants.EXECUTION_ENV).contains("Browserstack")) {
			DesiredCapabilities caps = null;

			String browserStackEnv = TestSetUp.configProperty.getProperty(Constants.BROWSER).toUpperCase();
			switch (browserStackEnv) {
			case Constants.CHROME:
				caps = DesiredCapabilities.chrome();
				caps.setCapability(Constants.BROWSERSTACK_NETWORK_LOGS, true);
				caps.setCapability(Constants.BROWSERSTACK_DEBUG, true);
				caps.setCapability("os", Constants.BROWSERSTACK_OS);
				caps.setCapability(Constants.OS_VERSION, Constants.BROWSERSTACK_OS_VERSION);
				caps.setCapability(Constants.VERSION, Constants.CHROME_VERSION);
				caps.setCapability(Constants.BROWSERSTACK_LOCAL, Constants.BROWSERSTACK_LOCAL_VALUE_FALSE);
				break;
			case Constants.FIREFOX:
				caps = DesiredCapabilities.firefox();
				caps.setCapability(Constants.BROWSERSTACK_NETWORK_LOGS, true);
				caps.setCapability(Constants.BROWSERSTACK_DEBUG, true);
				caps.setCapability("os", Constants.BROWSERSTACK_OS);
				caps.setCapability(Constants.OS_VERSION, Constants.BROWSERSTACK_OS_VERSION);
				caps.setCapability(Constants.VERSION, Constants.FIREFOX_VERSION);
				caps.setCapability(Constants.BROWSERSTACK_LOCAL, Constants.BROWSERSTACK_LOCAL_VALUE_FALSE);
				break;
			case Constants.IE:
				caps = DesiredCapabilities.edge();
				caps.setCapability("os", Constants.BROWSERSTACK_OS);
				caps.setCapability(Constants.BROWSERSTACK_NETWORK_LOGS, true);
				caps.setCapability(Constants.BROWSERSTACK_DEBUG, true);
				caps.setCapability(Constants.OS_VERSION, Constants.BROWSERSTACK_OS_VERSION);
				caps.setCapability(Constants.VERSION, Constants.BROWSERSTACK_IE_VERSION);
				caps.setCapability(Constants.BROWSERSTACK_LOCAL, Constants.BROWSERSTACK_LOCAL_VALUE_FALSE);
				break;
			default:
				break;
			}
			targetUrl = Constants.BROWSERSTACK_URL;
		} else if (TestSetUp.configProperty.getProperty(Constants.EXECUTION_ENV).contains("SeeTest")) {

			String seeEnv = TestSetUp.configProperty.getProperty(Constants.BROWSER).toUpperCase();
			switch (seeEnv) {
			case Constants.CHROME:
				caps = DesiredCapabilities.chrome();
				caps.setCapability(Constants.PLATFORM, Constants.SEETEST_CHROME_PLATFORM);
				caps.setCapability(Constants.VERSION, Constants.SEETEST_CHROME_VERSION);
				caps.setCapability("accessKey", Constants.SEETEST_ACCESS_KEY);
				caps.setCapability("testName",
						Constants.SEETEST_CHROME_PLATFORM + "-"
								+ TestSetUp.configProperty.getProperty(Constants.BROWSER) + "-"
								+ Constants.SEETEST_CHROME_VERSION);
				break;
			case Constants.FIREFOX:
				caps = DesiredCapabilities.firefox();
				caps.setCapability(Constants.PLATFORM, Constants.SEETEST_FIREFOX_PLATFORM);
				caps.setCapability(Constants.VERSION, Constants.SEETEST_FIREFOX_VERSION);
				caps.setCapability("accessKey", Constants.SEETEST_ACCESS_KEY);
				caps.setCapability("testName",
						Constants.SEETEST_FIREFOX_PLATFORM + "-"
								+ TestSetUp.configProperty.getProperty(Constants.BROWSER) + "-"
								+ Constants.SEETEST_FIREFOX_VERSION);
				break;
			case "IE":
				throw new SkipException("SeeTest Support for Edge browser is not available as of now..");

			default:
				break;
			}
			targetUrl = "https://cloud.seetest.io/wd/hub";
		}

		if (TestSetUp.configProperty.get("SELENIUM_GRID").toString().equalsIgnoreCase("true")) {
			System.out.println("Test is running in zalenium node" + targetUrl);
			driver = new RemoteWebDriver(new URL(targetUrl), caps);
		}
		DriverManager.setDriver(driver);
		DriverManager.maximizeBrowser(driver);
		DriverManager.pageLoadTimeout(driver);
		DriverManager.setImplicitWait(driver);
		System.out.println("Driver created " + driver);

		/**
		 * Additional execution environments can be added here.
		 */
		return DriverManager.getDriver();
	}

	/**
	 * @param browserName
	 */

	private static void setUpGridHub() {
		try {
			GridLauncherV3.main(new String[] { "-role", "hub", "-port", "4444" });
			System.out.println("HUB Started successfully");
		} catch (Exception e) {
		}
	}

	private static void setUpGridNode(String browserName, String portNo) {

		try {
			GridLauncherV3.main(new String[] { "-role", "node", "-hub", "http://localhost:4444/grid/register", "-host",
					"localhost", "-browser", "\"" + "browserName=" + browserName + ",version=81" + "\"", "-port",
					"\"" + portNo + "\"" });
			// , "-servlets", "com.automation.remarks.remote.node.Video"
			System.out.println("NODE Started successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method is to kill the driver.
	 */
	public static void destroyDriver() {
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}

	}
}
