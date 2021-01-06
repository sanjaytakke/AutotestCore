package com.lt.autotest.handlers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.lt.autotest.utils.Utility;
import com.lt.base.TestBase;

public class AssertHandler {

	private static int configWaitTimeInSeconds = Utility.getDefaultWaitTime();
	private static final Logger LOGGER = LoggerFactory.getLogger(AssertHandler.class);

	public static void assertElementPresent(WebElement element) {

		try {
			// Wait for the element to be selectable
			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), configWaitTimeInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			LOGGER.debug(
					"AssertHandler.assertElementPresent - Trying to assert the presence of the webelement :" + element);
			assert element.isDisplayed();
			LOGGER.debug("AssertHandler.assertElementPresent - " + element.isDisplayed());
		} catch (StaleElementReferenceException e) {
			LOGGER.error(
					"AssertHandler.assertElementPresent - StaleElementReference Exception : Re trying to assert the weblement :"
							+ element);
			Utility.takeScreenShot();
			assert false;
		} catch (TimeoutException e) {
			LOGGER.error(
					"AssertHandler.assertElementPresent - Timeout waiting for " + configWaitTimeInSeconds + " seconds");
			Utility.takeScreenShot();
			assert false;
		} catch (NoSuchElementException e) {
			LOGGER.error("AssertHandler.assertElementPresent - NoSuchElementException : " + e);
			Utility.takeScreenShot();
			assert false;
		} catch (WebDriverException e) {
			LOGGER.error("AssertHandler.assertElementPresent - WebDriverException :" + e);
			Utility.takeScreenShot();
			assert false;
		} catch (Exception e) {
			LOGGER.error("AssertHandler.assertElementPresent - Exception :" + e);
			Utility.takeScreenShot();
			assert false;
		}

	}

	public static void assertTextEqualsOnElement(WebElement element, String expectedText) {
		for (int i = 0; i < 5; i++) {
			try {
				WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), configWaitTimeInSeconds);
				wait.until(ExpectedConditions.visibilityOf(element));
				LOGGER.debug("AssertHandler.assertTextEqualsOnElement - Trying to assert [" + expectedText
						+ "]  on the weblement :" + element);
				Assert.assertEquals(expectedText.toLowerCase(), element.getText().toLowerCase());
				LOGGER.debug("AssertHandler.assertTextEqualsOnElement - Done");
				break;
			} catch (StaleElementReferenceException e) {
				LOGGER.error(
						"AssertHandler.assertTextEqualsOnElement - StaleElementReference Exception : Re trying to assert the weblement :"
								+ element);
				assert false;
			} catch (TimeoutException e) {
				LOGGER.error("AssertHandler.assertTextEqualsOnElement - Timeout waiting for " + configWaitTimeInSeconds
						+ " seconds");
				assert false;
			} catch (NoSuchElementException e) {
				LOGGER.error("AssertHandler.assertTextEqualsOnElement - NoSuchElementException : " + e);
				assert false;
			} catch (WebDriverException e) {
				LOGGER.error("AssertHandler.assertTextEqualsOnElement - WebDriverException :" + e);
				assert false;
			} catch (Exception e) {
				LOGGER.error("AssertHandler.assertTextEqualsOnElement - Exception :" + e);
				assert false;
			}
		}
	}

	public static void assertString(String expected, String actual) {

		try {
			LOGGER.debug("AssertHandler.assertString - Trying to assert [" + expected + "]  on the string :" + actual);
			Assert.assertEquals(expected.toLowerCase(), actual.toLowerCase());
			LOGGER.debug("AssertHandler.assertString - Done");
		} catch (Exception e) {
			LOGGER.error("AssertHandler.assertString - Exception :" + e);
			assert false;
		}
	}

	public static void assertTextContainsOnElement(WebElement element, String expectedText) {
		for (int i = 0; i < 5; i++) {

			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), configWaitTimeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			LOGGER.debug("AssertHandler.assertTextContainsOnElement - Trying to assert text [" + expectedText
					+ "]  contains on the weblement :" + element);
			String text = element.getText().replaceAll("(\\r|\\n|\\t)", "");

		}
	}
}
