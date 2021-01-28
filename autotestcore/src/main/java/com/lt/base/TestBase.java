package com.lt.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.*;
import com.lt.autotest.constants.Browsers;
import com.lt.autotest.utils.Utility;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase extends DriverManager {

	public Browsers browser = Browsers.CHROME;
	private static final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);
	public static ExtentReports extentReports;
	public static final ThreadLocal<ExtentTest> extentLogger = new ThreadLocal<>();
	public static Scenario scenario;

	public void setupTest() throws MalformedURLException {

		if (null != System.getenv("SELENIUM_BROWSER")) {
			browser = Browsers.getBrowser(System.getenv("SELENIUM_BROWSER"));
			LOGGER.info("Executing tests on " + browser.toString() + " browser...");
		}
		loggerConfig("testName");
		configureBrowser(browser);
	}

	protected WebDriver createChromeDriver(Browsers browser) {

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("test-type");
		options.addArguments("disable-infobars");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chromeOptions", options);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		if (Utility.isWindowsStylePath()) {
			LOGGER.info("Platform being used for execution is Windows");
		} else {
			LOGGER.info("Platform being used for execution is Linux");
		}

		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}

		return driver;

	}

	protected RemoteWebDriver createIEDriver(Browsers browser) {

		WebDriverManager.iedriver().setup();
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

		capabilities.setCapability("ignoreProtectedModeSettings", true);

		RemoteWebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:49044/wd/hub"), capabilities);
			driver.manage().window().maximize();
		} catch (MalformedURLException e) {
			LOGGER.error("MalformedURL Exception occured: ", e);
		}
		return driver;
	}

	protected WebDriver createEdgeDriver(Browsers browser) {
		WebDriverManager.edgedriver().setup();
		WebDriver driver = null;
		try {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		} catch (Exception e) {
			LOGGER.error("MalformedURL Exception occured: ", e);
		}
		return driver;
	}

	public static void configReport() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("user.dir"));
		sb.append("/test-output/extentReport");
		File file = new File(sb.toString());
		if (!file.exists()) {
			file.mkdirs();
		}
		ExtentSparkReporter extentHtmlReporter = new ExtentSparkReporter(sb.append("/extent-Report.html").toString());
		try {
			extentHtmlReporter
					.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);

	}

	public static ExtentTest getextentLogger() {
		return extentLogger.get();
	}

	public void setextentLogger(ExtentTest exLogger) {
		extentLogger.set(exLogger);
	}

	private void loggerConfig(String testName) {
		configReport();
		ExtentTest exLogger = extentReports.createTest(testName);
		exLogger.log(Status.INFO, "starting test " + testName + "");
		setextentLogger(exLogger);
	}

	public static void addScreenshot() {
		try {
			getextentLogger().addScreenCaptureFromPath(Utility.takeScreenShot());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setupScenario(Scenario scenario) {
		TestBase.scenario = scenario;
	}
}
