package com.lt.autotest.handlers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.autotest.utils.Utility;
import com.lt.base.TestBase;

public class VerifyHandler {

	private static int configWaitTimeInSeconds = Utility.getDefaultWaitTime();
	private static final Logger LOGGER = LoggerFactory.getLogger(VerifyHandler.class);

	public static boolean verifyElementPresent(WebElement element) {
		int waitTimeInSeconds = 5;
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), waitTimeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			status = element.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		} catch (Exception e) {
			status = false;
		}
		LOGGER.debug("VerifyHandler.verifyElementPresent - " + status);
		return status;
	}

	public static boolean verifyTitleContainsText(String text) {

		LOGGER.info("VerifyHandler.verifyTitleContainsText - Verifying the title contains the text [" + text + "]");
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), configWaitTimeInSeconds);
			wait.until(ExpectedConditions.titleContains(text));
			status = true;
		} catch (NoSuchElementException e) {
			status = false;
		} catch (Exception e) {
			status = false;
		}
		LOGGER.debug("VerifyHandler.verifyTitleContainsText - " + status);
		return status;
	}

	public static boolean verifyElementHasFocus(WebElement element) {
		int waitTimeInSeconds = 5;
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), waitTimeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			status = element.equals(TestBase.getDriver().switchTo().activeElement());
		} catch (NoSuchElementException e) {
			status = false;
		} catch (Exception e) {
			status = false;
		}
		LOGGER.debug("VerifyHandler.verifyElementHasFocus - " + status);
		return status;
	}

	public static boolean verifyChkBoxRadioButtonSelected(WebElement element) {
		int waitTimeInSeconds = 5;
		boolean status = false;
		try {
			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), waitTimeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			status = element.isSelected();
		} catch (NoSuchElementException e) {
			status = false;
		} catch (Exception e) {
			status = false;
		}
		LOGGER.debug("VerifyHandler.verifyChkBoxRadioButtonSelected - " + status);
		return status;
	}

}
