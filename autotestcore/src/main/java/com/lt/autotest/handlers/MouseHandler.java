package com.lt.autotest.handlers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.autotest.utils.Utility;
import com.lt.base.TestBase;

public class MouseHandler {

	private static int configWaitTimeInSeconds = Utility.getDefaultWaitTime();
	private static final Logger LOGGER = LoggerFactory.getLogger(MouseHandler.class);

	public static void hover(WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), configWaitTimeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));

			LOGGER.debug("MouseHandler.hover - Trying to hover on the web-element :" + element);
			Actions actions = new Actions(TestBase.getDriver());
			actions.moveToElement(element).build().perform();
			LOGGER.debug("MouseHandler.hover - Done");
		} catch (Exception e) {
			LOGGER.error("MouseHandler.hover - Exception :" + e);
		}
	}

	public static void moveAndClick(WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), configWaitTimeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));

			LOGGER.debug("MouseHandler.moveAndClick - Trying to move and click on the web-element :" + element);
			Actions actions = new Actions(TestBase.getDriver());
			actions.moveToElement(element).click().build().perform();
			LOGGER.debug("MouseHandler.moveAndClick - Done");
		} catch (Exception e) {
			LOGGER.error("MouseHandler.moveAndClick - Exception :" + e);
		}
	}
}
