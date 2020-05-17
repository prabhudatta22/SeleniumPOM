package com.framework.testutils;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author prabhudatta
 *
 */
public class WebDriverHelper {
	WebDriver driver;

	public WebDriverHelper(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement waitForElementToBeDisplayed(final WebElement element, int timeOutPeriod,
			final String logMessage) {

		// try{
		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));

		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					if (element.isDisplayed()) {

						return element;
					} else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {

					return null;
				}
			}

		});

	}

	public WebElement waitForElementToBeDisplayed(final By by, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(by);
					if (element.isDisplayed())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}

	public WebDriver WAIT_FOR_FRAME_TO_BE_DISPLAYED(WebDriver driver, int timeOutPeriod, final String svalue) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(svalue));

		return webDriverWait.until(new ExpectedCondition<WebDriver>() {

			public WebDriver apply(WebDriver driver) {
				try {
					return driver;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}

	public void WAIT_FOR_FRAME_TO_BE_DISPLAYED(WebDriver driver, int timeOutPeriod, By id) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(id));

	}

	public WebDriver WaitForFrametoBeDisplayedByIndex(WebDriver driver, int timeOutPeriod, final int index) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));

		return (WebDriver) webDriverWait.until(new ExpectedCondition<WebDriver>() {

			public WebDriver apply(WebDriver driver) {

				WebDriver TLocator = null;
				TLocator = (WebDriver) driver.switchTo().frame(index);

				if (TLocator != null)
					return TLocator;
				return driver;
			}

		});

	}

	public WebElement waitForElementToBeClickable(final By by, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		return webDriverWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(by);
					if (element.isEnabled() && element.isDisplayed())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}

	public WebElement waitForElementToBeClickable(final WebElement element, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					if (element.isEnabled() && element.isDisplayed())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}

	public WebElement waitForElementToBeEnabled(final WebElement element, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));

		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {

					if (element.isEnabled())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}

	public WebElement waitForElementToBeEnabled(final By by, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(by);
					if (element.isEnabled())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}

	public WebElement waitForOptionToBePopulatedInList(final WebElement dropdownList, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					List<WebElement> options = dropdownList.findElements(By.tagName("option"));
					if (options.size() > 1) {
						return dropdownList;
					} else
						return null;

				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}

	public void waitForElementToDisappear(final By by, int timeOutPeriod) {

		FluentWait<By> fluentWait = new FluentWait<By>(by);
		fluentWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		fluentWait.withTimeout(Duration.ofSeconds(Constants.DRIVER_TIME_OUT));
		fluentWait.until(new Function<By, Boolean>() {
			public Boolean apply(By by) {
				return !driver.findElement(by).isDisplayed();
			}
		});

	}

	public void bringElementInView(WebElement element) {
		((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public void scrollUp() {
		((JavascriptExecutor) this.driver).executeScript("scroll(0, -250);");
	}

	public void scrollDown() {
		((JavascriptExecutor) this.driver).executeScript("scroll(0, 250);");
	}

	public void waitForElementToDisappear(final WebElement element, int timeOutPeriod) {

		FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(element);
		fluentWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		fluentWait.withTimeout(Duration.ofSeconds(Constants.DRIVER_TIME_OUT));
		fluentWait.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement t) {
				return !t.isDisplayed();
			}
		});
	}

	public void waitForAlert(int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT)).until(ExpectedConditions.alertIsPresent());

	}

	public void waitForElementPresent(final By by, int timeout) {
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(this.driver, timeout)
				.ignoring(StaleElementReferenceException.class);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				WebElement element = webDriver.findElement(by);
				return element != null && element.isDisplayed();
			}
		});
	}

	public String acceptAlert(int timeOutPeriod) {
		waitForAlert(timeOutPeriod);
		Alert alert = this.driver.switchTo().alert();
		String AlertMessage = alert.getText();
		alert.accept();
		return AlertMessage;
	}

	/**
	 * @param waitTime
	 * @see pass value in seconds
	 */
	public void explicitWait(int waitTime) {

		try {
			Thread.sleep(waitTime * 1000);
		} catch (InterruptedException e) {

		}
	}

	public void waitForTextToBePresentInElement(WebElement pageElement, String text, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		webDriverWait.until(ExpectedConditions.textToBePresentInElement(pageElement, text));
	}

	public void waitForTextToBeAbsentInElement(By by, String text, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		webDriverWait.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
	}

	public String WaitForPageLoad(int timeOutPeriod) {
		String msg = null;
		try {
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(this.driver);
			fluentWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
			fluentWait.withTimeout(Duration.ofSeconds(Constants.DRIVER_TIME_OUT));
			fluentWait.until(new Function<WebDriver, Boolean>() {

				public Boolean apply(WebDriver driver) {
					System.out.println(
							"State " + ((JavascriptExecutor) driver).executeScript("return document.readyState"));
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			});
			msg = "completed";
		} catch (Exception e) {
			msg = e.getMessage();
			System.out.println(e + " Exception");
		}

		return msg;
	}

}
