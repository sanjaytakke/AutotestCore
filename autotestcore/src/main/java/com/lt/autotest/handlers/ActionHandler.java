package com.lt.autotest.handlers;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.autotest.utils.Utility;
import com.lt.base.DriverManager;

public class ActionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActionHandler.class);
	private static int configWaitTimeInSeconds = Utility.getDefaultWaitTime();

	public static void clickAll(String locator, String locateby) {
		LOGGER.debug("ActionHandler.clickAll - Trying to locate the all the weblements for :" + locator);
		boolean elementNotFound = false;
		List<WebElement> webElements = null;
		try {

			switch (locateby) {
			case "xpath": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
				break;
			}
			case "css": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locator)));
				break;
			}
			case "id": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locator)));
				break;
			}
			case "linktext": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locator)));
				break;
			}
			case "partiallinktext": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locator)));
				break;
			}
			case "classname": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locator)));
				break;
			}
			case "name": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locator)));
				break;
			}
			case "tagname": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locator)));
				break;
			}
			default: {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
				break;
			}
			}
			LOGGER.info("ActionHandler.clickAll - Total Webelements located :::::" + webElements.size());
			int i = 0;
			for (WebElement elements : webElements) {
				LOGGER.debug("ActionHandler.clickAll - Clicking on the element number : " + (++i));
				click(elements);
			}
			LOGGER.debug("ActionHandler.clickAll - Success");
		} catch (Exception e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.clickAll - Exception :" + e);
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	// A Click Event but with a default wait time set
	public static void click(WebElement element) {
		Utility.flash(element);
		click(element, configWaitTimeInSeconds);
	}

	// A Click Event but with a custom wait time to be set
	public static void click(WebElement element, int waitTimeInSeconds) {
		boolean elementNotFound = true;
		for (int i = 0; i < 2; i++) {
			try {
				// Wait for the element to be clickable
				WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				if (element.isDisplayed() && element.isEnabled()) {
					LOGGER.debug("ActionHandler.click - Trying to click the weblement  :" + element);
					element.click();
					LOGGER.debug("ActionHandler.click - Success");
					elementNotFound = false;
					break;
				}
			} catch (StaleElementReferenceException e) {
				LOGGER.info("ActionHandler.click - StaleElementReferenceException : Re trying to click the weblement :"
						+ element);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					LOGGER.error("ActionHandler.click - InterruptedException :" + e1);
				}
			} catch (NoSuchElementException e) {
				LOGGER.error("ActionHandler.click - NoSuchElementException :" + e);
			} catch (WebDriverException e) {
				LOGGER.error("ActionHandler.click - WebDriverException :" + e);
			} catch (Exception e) {
				LOGGER.error("ActionHandler.click - Exception :" + e);
			}
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	// A Click Event but with a custom wait time to be set
	public static void scrollAndClick(WebElement element) {
		boolean elementNotFound = true;
		for (int i = 0; i < 6; i++) {
			try {
				// Wait for the element to be clickable
				WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 5);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				if (element.isDisplayed() && element.isEnabled()) {
					LOGGER.debug("ActionHandler.scrollAndClick - Trying to click the weblement  :" + element);
					element.click();
					LOGGER.debug("ActionHandler.click - Success");
					elementNotFound = false;
					break;
				}
			} catch (StaleElementReferenceException e) {
				LOGGER.info(
						"ActionHandler.scrollAndClick - StaleElementReferenceException : Re trying to click the weblement :"
								+ element);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					LOGGER.error("ActionHandler.scrollAndClick - InterruptedException :" + e1);
				}
			} catch (NoSuchElementException e) {
				LOGGER.error("ActionHandler.scrollAndClick - NoSuchElementException :" + e);
			} catch (WebDriverException e) {
				LOGGER.error("ActionHandler.scrollAndClick - WebDriverException :" + e);
			} catch (Exception e) {
				LOGGER.error("ActionHandler.scrollAndClick - Exception :" + e);
			}
			if (elementNotFound) {
				pageScrollDown();
			}
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	// A doubleClick Event but with a default wait time set
	public static void doubleClick(WebElement element) {

		doubleClick(element, configWaitTimeInSeconds);

	}

	// A doubleClick Event but with a custom wait time to be set
	public static void doubleClick(WebElement element, int waitTimeInSeconds) {

		boolean elementNotFound = false;
		for (int i = 0; i < 2; i++) {
			try {
				// Wait for the element to be clickable
				WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds);
				wait.until(ExpectedConditions.elementToBeClickable(element));
				if (element.isDisplayed() && element.isEnabled()) {
					LOGGER.debug("ActionHandler.doubleClick - Trying to doubleClick the weblement  :" + element);
					Actions action = new Actions(DriverManager.getDriver());
					action.doubleClick(element).perform();

					LOGGER.debug("ActionHandler.doubleClick - Success");
					elementNotFound = false;
					break;
				}
			} catch (StaleElementReferenceException e) {
				// handle stale element exception...
				LOGGER.info(
						"ActionHandler.doubleClick - StaleElementReferenceException : Re trying to doubleClick the weblement :"
								+ element);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					LOGGER.error("ActionHandler.doubleClick - InterruptedException :" + e1);
				}
				elementNotFound = true;
			} catch (NoSuchElementException e) {
				elementNotFound = true;
				LOGGER.error("ActionHandler.doubleClick - NoSuchElementException :" + e);
			} catch (WebDriverException e) {
				elementNotFound = true;
				LOGGER.error("ActionHandler.doubleClick - WebDriverException :" + e);
			} catch (Exception e) {
				elementNotFound = true;
				LOGGER.error("ActionHandler.doubleClick - Exception :" + e);
			}
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	// SetText for an Element with default timeout
	public static void setText(WebElement element, String text) {

		setText(element, text, configWaitTimeInSeconds);
	}

	// Alert accept
	public static void acceptAlert() {
		try {
			int i = 0;
			while (i++ < 5) {
				try {
					Alert alt = DriverManager.getDriver().switchTo().alert();
					alt.accept();
					LOGGER.debug("ActionHandler.acceptAlert - Success");
					break;
				} catch (NoAlertPresentException e) {
					Thread.sleep(1000);
					i++;
					continue;
				}
			}
		} catch (Exception e) {
			LOGGER.error("ActionHandler.acceptAlert - Exception :" + e);
		}
	}

	// Alert dismiss
	public static void dismissAlert() {
		try {
			int i = 0;
			while (i++ < 5) {
				try {
					Alert alt = DriverManager.getDriver().switchTo().alert();
					alt.dismiss();
					LOGGER.debug("ActionHandler.dismissAlert - Success");
					break;
				} catch (NoAlertPresentException e) {
					Thread.sleep(1000);
					i++;
					continue;
				}
			}
		} catch (Exception e) {
			LOGGER.error("ActionHandler.dismissAlert - Exception :" + e);
		}
	}

	// Alert getAlertMessage
	public static String getAlertMessage() {
		String alertMessage = "";
		try {
			int i = 0;
			while (i++ < 5) {
				try {
					Alert alt = DriverManager.getDriver().switchTo().alert();
					alertMessage = alt.getText();
					LOGGER.debug("ActionHandler.getAlertMessage - Success");
					break;
				} catch (NoAlertPresentException e) {
					Thread.sleep(1000);
					i++;
					continue;
				}
			}
		} catch (Exception e) {
			LOGGER.error("ActionHandler.getAlertMessage - Exception :" + e);
		}
		return alertMessage;
	}

	// SetText with a custom wait Time
	public static void setText(WebElement element, String text, int waitTimeInSeconds) {

		boolean elementNotFound = true;
		for (int i = 0; i < 3; i++) {
			try {
				// Wait for the element to be visible
				LOGGER.debug("ActionHandler.setText - Trying to set text for the weblement :" + element);
				WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds);
				wait.until(ExpectedConditions.visibilityOf(element));

				if (element.isDisplayed() && element.isEnabled()) {
					element.clear();
					element.sendKeys(text);
					LOGGER.debug("ActionHandler.setText - Success");
					elementNotFound = false;
					break;
				}
				wait(3);

			} catch (TimeoutException e) {
				LOGGER.error("ActionHandler.setText - TimeoutException : " + e);
			} catch (NoSuchElementException e) {
				LOGGER.error("ActionHandler.setText - NoSuchElementException : " + e);
			} catch (WebDriverException e) {
				LOGGER.error("ActionHandler.setText - WebDriverException : " + e);
			} catch (Exception e) {
				LOGGER.error("ActionHandler.setText - Exception : " + e);
			}
			if (elementNotFound) {
				LOGGER.debug(
						"ActionHandler.setText - Entering text failed using conventional way. Trying alternate ways..");
				Actions action = new Actions(DriverManager.getDriver());
				action.sendKeys(element, text).perform();
				elementNotFound = false;
				break;
			}
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	public static void clearText(WebElement element) {

		boolean elementNotFound = true;
		for (int i = 0; i < 3; i++) {
			try {
				// Wait for the element to be visible
				LOGGER.debug("ActionHandler.clearText - Trying to clear text for the weblement :" + element);
				WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds);
				wait.until(ExpectedConditions.visibilityOf(element));

				if (element.isDisplayed() && element.isEnabled()) {
					element.clear();
					LOGGER.debug("ActionHandler.clearText - Success");
					elementNotFound = false;
					break;
				}
				wait(3);

			} catch (TimeoutException e) {
				LOGGER.error("ActionHandler.clearText - TimeoutException : " + e);
			} catch (NoSuchElementException e) {
				LOGGER.error("ActionHandler.clearText - NoSuchElementException : " + e);
			} catch (WebDriverException e) {
				LOGGER.error("ActionHandler.clearText - WebDriverException : " + e);
			} catch (Exception e) {
				LOGGER.error("ActionHandler.clearText - Exception : " + e);
			}
			if (elementNotFound) {
				LOGGER.debug(
						"ActionHandler.clearText - Clearing text failed using conventional way. Trying alternate ways..");
				Actions action = new Actions(DriverManager.getDriver());
				action.sendKeys(element, "").perform();
				elementNotFound = false;
				break;
			}
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	public static void pressEnter() {
		try {
			Actions act = new Actions(DriverManager.getDriver());
			act.sendKeys(Keys.ENTER).build().perform();
			LOGGER.debug("ActionHandler.pressEnter - Success");
		} catch (Exception e) {
			LOGGER.error("ActionHandler.pressEnter - Exception : " + e);
		}
	}

	public static void pressEscKey() {
		try {
			Actions act = new Actions(DriverManager.getDriver());
			act.sendKeys(Keys.ESCAPE).build().perform();
			LOGGER.debug("ActionHandler.pressEscKey - Success");
		} catch (Exception e) {
			LOGGER.error("ActionHandler.pressEscKey - Exception : " + e);
		}
	}

	public static void selectByValue(WebElement element, String option) {

		boolean elementNotFound = false;
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			LOGGER.debug("ActionHandler.selectByValue - Trying to select option [" + option + "] for the weblement :"
					+ element);
			Select dropdown = new Select(element);
			dropdown.selectByValue(option);
			LOGGER.debug("ActionHandler.selectByValue - Success");
		} catch (TimeoutException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByValue - Timeout waiting for " + configWaitTimeInSeconds + " seconds",
					e);
		} catch (NoSuchElementException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByValue - NoSuchElementException  " + e);
		} catch (WebDriverException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByValue - WebDriverException :" + e);
		} catch (Exception e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByValue - Exception :" + e);
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	public static void selectByVisibleText(WebElement element, String text) {

		boolean elementNotFound = false;
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			LOGGER.debug("ActionHandler.selectByVisibleText - Trying to select option with text [" + text
					+ "] for the weblement :" + element);
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(text);
			LOGGER.debug("ActionHandler.selectByVisibleText - Success");
		} catch (TimeoutException e) {
			elementNotFound = true;
			LOGGER.error(
					"ActionHandler.selectByVisibleText - Timeout waiting for " + configWaitTimeInSeconds + " seconds",
					e);
		} catch (NoSuchElementException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByVisibleText - NoSuchElementException  " + e);
		} catch (WebDriverException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByVisibleText - WebDriverException :" + e);
		} catch (Exception e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByVisibleText - Exception :" + e);
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	public static void selectByIndex(WebElement element, int index) {

		boolean elementNotFound = false;
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			LOGGER.debug("ActionHandler.selectByIndex - Trying to select option at index [" + index
					+ "] for the weblement :" + element);
			Select dropdown = new Select(element);
			dropdown.selectByIndex(index);
			LOGGER.debug("ActionHandler.selectByIndex - Success");
		} catch (TimeoutException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByIndex - Timeout waiting for " + configWaitTimeInSeconds + " seconds",
					e);
		} catch (NoSuchElementException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByIndex - NoSuchElementException  " + e);
		} catch (WebDriverException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByIndex - WebDriverException :" + e);
		} catch (Exception e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.selectByIndex - Exception :" + e);
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	public static void switchToframe(WebElement element) {
		try {
			LOGGER.debug("ActionHandler.switchToframe - In switchtoframe method");
			DriverManager.getDriver().switchTo().frame(element);
		} catch (WebDriverException e) {
			LOGGER.error("ActionHandler.switchToframe - WebDriverException :" + e);
		} catch (Exception e) {
			LOGGER.error("ActionHandler.switchToframe - Exception :" + e);
		}
	}

	public static void switchbackFromframe() {
		try {
			DriverManager.getDriver().switchTo().defaultContent();
		} catch (WebDriverException e) {
			LOGGER.error("ActionHandler.switchbackFromframe - WebDriverException :" + e);
		} catch (Exception e) {
			LOGGER.error("ActionHandler.switchbackFromframe - Exception :" + e);
		}
	}

	public static void wait(int seconds) {

		try {
			LOGGER.info("ActionHandler.wait - Sleeping for  [" + seconds + "] seconds");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			LOGGER.error("ActionHandler.wait - InterruptedException occured: ", e);
		}
	}

	public static WebElement waitForElement(WebElement elementToWaitFor) {
		return waitForElement(elementToWaitFor, null);
	}

	public static WebElement waitForElement(WebElement elementToWaitFor, Integer waitTimeInSeconds) {
		if (null == waitTimeInSeconds) {
			waitTimeInSeconds = Utility.getDefaultWaitTime();
		}

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds);
		return wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
	}

	/**
	 * Return parent element
	 * 
	 * @param getParent
	 * @return WebElement
	 */
	public static WebElement getParent(WebElement element) {
		return element.findElement(By.xpath(".."));
	}

	/**
	 * perform tab action from current element
	 * 
	 * @param element
	 */
	public static void pressTabKey(WebElement element) {
		try {
			element.sendKeys(Keys.TAB);
			LOGGER.debug("ActionHandler.pressTabKey - Success");
		} catch (Exception e) {
			LOGGER.error("ActionHandler.pressTabKey - Exception : " + e);
		}
	}

	/**
	 * perform tab action in back from current element
	 * 
	 * 
	 */
	public static void pressBackTabKey() {
		try {
			Actions action = new Actions(DriverManager.getDriver());
			action.keyDown(Keys.SHIFT).sendKeys(Keys.TAB).keyUp(Keys.SHIFT).build().perform();
			LOGGER.debug("ActionHandler.pressBackTabKey - Success");
		} catch (Exception e) {
			LOGGER.error("ActionHandler.pressBackTabKey - Exception : " + e);
		}
	}

	/**
	 * Scroll element into visible area
	 * 
	 * @param scrollToView
	 */
	public static void scrollToView(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			LOGGER.error("ActionHandler.scrollToView - Exception : " + e);
		}
	}

	/**
	 * Return list of elements return type List
	 * 
	 * @param listOfElements
	 */
	public static List<WebElement> listOfElements(String locator, String locateby) {

		LOGGER.debug("ActionHandler.listOfElements - Trying to locate the webElements for :" + locator);
		boolean elementNotFound = false;
		List<WebElement> webElements = null;
		try {

			switch (locateby) {
			case "xpath": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
				break;
			}
			case "css": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locator)));
				break;
			}
			case "id": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locator)));
				break;
			}
			case "linktext": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locator)));
				break;
			}
			case "partiallinktext": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locator)));
				break;
			}
			case "classname": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locator)));
				break;
			}
			case "name": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locator)));
				break;
			}
			case "tagname": {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locator)));
				break;
			}
			default: {
				webElements = (List<WebElement>) (new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds))
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
				break;
			}
			}
			LOGGER.info("ActionHandler.listOfElements - Total Webelements located :::::" + webElements.size());

		} catch (Exception e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.listOfElements - Exception :" + e);
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}

		return webElements;

	}

	public static String getAttributeValue(WebElement element, String elementAttributeName) {
		int waitTimeInSeconds = 5;
		String attributeValue = "";
		boolean elementNotFound = false;
		try {
			LOGGER.debug("ActionHandler.getAttributeValue - Trying to fetch attributevalue " + elementAttributeName
					+ " for webelement  :" + element);

			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), waitTimeInSeconds);

			attributeValue = element.getAttribute(elementAttributeName);

			LOGGER.debug("AssertHandler.getAttributeValue : " + attributeValue + " - Done");

		} catch (TimeoutException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.getAttributeValue - TimeoutException : " + e);
		} catch (NoSuchElementException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.getAttributeValue - NoSuchElementException : " + e);
		} catch (WebDriverException e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.getAttributeValue - WebDriverException : " + e);
		} catch (Exception e) {
			elementNotFound = true;
			LOGGER.error("ActionHandler.getAttributeValue - Exception : " + e);
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
		return attributeValue;
	}

	public static WebElement locateElement(String locator, String locateby) {
		WebElement webElement = null;
		switch (locateby) {
		case "css": {
			webElement = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
			break;
		}
		case "id": {
			webElement = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
			break;
		}
		case "linktext": {
			webElement = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
			break;
		}
		case "partiallinktext": {
			webElement = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locator)));
			break;
		}
		case "classname": {
			webElement = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
			break;
		}
		case "name": {
			webElement = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
			break;
		}
		case "tagname": {
			webElement = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locator)));
			break;
		}
		case "xpath": {
			webElement = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			break;
		}
		default: {
			webElement = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			break;
		}
		}

		return webElement;
	}

	public static void pageScrollDown() {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		for (int i = 0; i < 3; i++) {
			pageArrowUp();
		}
	}

	public static void pageArrowUp() {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.sendKeys(Keys.ARROW_UP).build().perform();
	}

	public static void pageCompleteScrollDown() {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.sendKeys(Keys.chord(Keys.CONTROL, Keys.END)).build().perform();
	}

	public static void pageCompleteScrollUp() {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME)).build().perform();
	}

	public static String getElementText(WebElement element) {

		if (null != element.getText()) {
			return element.getText();
		}
		return element.getAttribute("value");
	}

	public static void backSpace(WebElement element) {

		boolean elementNotFound = true;
		for (int i = 0; i < 3; i++) {
			try {
				// Wait for the element to be visible
				LOGGER.debug("ActionHandler.backSpace - Trying to clear text for the weblement :" + element);
				WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), configWaitTimeInSeconds);
				wait.until(ExpectedConditions.elementToBeClickable(element));

				// if (element.isDisplayed() && element.isEnabled()) {
				for (int j = 0; j < 15; j++) {
					element.sendKeys(Keys.BACK_SPACE);
				}
				LOGGER.debug("ActionHandler.backSpace - Success");
				elementNotFound = false;
				break;
				// }
				// wait(3);

			} catch (TimeoutException e) {
				LOGGER.error("ActionHandler.backSpace - TimeoutException : " + e);
			} catch (NoSuchElementException e) {
				LOGGER.error("ActionHandler.backSpace - NoSuchElementException : " + e);
			} catch (WebDriverException e) {
				LOGGER.error("ActionHandler.backSpace - WebDriverException : " + e);
			} catch (Exception e) {
				LOGGER.error("ActionHandler.backSpace - Exception : " + e);
			}
			if (elementNotFound) {
				LOGGER.debug(
						"ActionHandler.backSpace - backSpace failed using conventional way. Trying alternate ways..");
				Actions action = new Actions(DriverManager.getDriver());
				for (int j = 0; j < 15; j++) {
					action.sendKeys(element, Keys.BACK_SPACE).perform();
				}
				elementNotFound = false;
				break;
			}
		}
		if (elementNotFound) {
			Utility.takeScreenShot();
		}
	}

	public static void clearDate() {

		wait(2);
		for (int i = 0; i < 2; i++) {
			try {
				DriverManager.getDriver().switchTo().activeElement().clear();
				break;
			} catch (InvalidElementStateException ie) {
				continue;
			}
		}
		wait(2);
	}

	public static void pageDown() {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		wait(2);

	}

	public static void pageUp() {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.sendKeys(Keys.PAGE_UP).build().perform();
		wait(2);
	}

	public static void scrollDown() {

		Actions actions = new Actions(DriverManager.getDriver());
		for (int j = 0; j < 3; j++) {
			actions.sendKeys(Keys.ARROW_DOWN).build().perform();
		}

		wait(3);
		System.out.println("After scrollDown");
	}

	public static void pageTop() {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.UP).perform();
		actions.keyDown(Keys.CONTROL).release().perform();
		wait(2);
	}

	public static void pageBottom() {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.DOWN).perform();
		actions.keyDown(Keys.CONTROL).release().perform();
		wait(2);
	}

	public static void pressTab() {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.sendKeys(Keys.TAB).perform();
		wait(2);
	}

	public static void clearBrowserCache() throws InterruptedException {
		LOGGER.info("Clearing browser cache...");
		DriverManager.getDriver().manage().deleteAllCookies();
		wait(5);
	}

	public static void clearAndSetText(WebElement element, String text) {
		Actions navigator = new Actions(DriverManager.getDriver());
		navigator.click(element).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT)
				.sendKeys(Keys.BACK_SPACE).sendKeys(text).perform();
	}

	public static void clickElement(WebElement element) {
		Actions action = new Actions(DriverManager.getDriver());
		action.click(element).build().perform();
	}

}
