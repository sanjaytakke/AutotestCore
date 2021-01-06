package com.lt.autotest.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.autotest.constants.CommonConstants;
import com.lt.base.DriverManager;
import com.lt.base.TestBase;

public class Utility {

	private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

	public static int getDefaultWaitTime() {

		return CommonConstants.DEFAULTWAITTIME;
	}

	public static final String takeScreenShot() {
		StringBuilder sb = new StringBuilder();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.SCREENSHOTFORMAT);

			String str = fetchHomeDir().replace("\\", "/");
			sb.append(str);
			sb.append("/screenshots/");
			sb.append(sdf.format(Utility.fetchCurrentDate()));
			sb.append(CommonConstants.SCREENSHOTFILEEXTENSION);
			File source = ((TakesScreenshot) TestBase.getDriver()).getScreenshotAs(OutputType.FILE);
			LOGGER.debug("screenshot file: " + source + ", path -->" + sb.toString());

			FileUtils.copyFile(source, new File(sb.toString()));
		} catch (Exception ioException) {
			LOGGER.error("Utility.takeScreenShot - IOException occurred: ", ioException);
		}
		return sb.toString();
	}

	public final static String fetchHomeDir() {

		return System.getProperty("user.dir");
	}

	/**
	 * This method returns the current date of the system.
	 * 
	 * @return date
	 */
	public final static Date fetchCurrentDate() {

		return new Date();
	}

	/**
	 * This method returns true if the directory structure is like Windows style.
	 * Else it returns false.
	 * 
	 * @return true if the directory structure is like Windows style. Else return
	 *         false.
	 */
	public final static boolean isWindowsStylePath() {

		if (System.getProperty("file.separator").contains("\\")) {
			return true;
		}
		return false;
	}

	public static void flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].style.backgroundColor='rgb(255,255,0)'", element);
		js.executeScript("arguments[0].style.backgroundColor='" + bgColor + "'", element);
	}


}
