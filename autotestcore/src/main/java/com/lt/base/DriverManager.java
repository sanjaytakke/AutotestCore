package com.lt.base;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.lt.autotest.constants.Browsers;

public abstract class DriverManager {
	
	public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
		
	public void configureBrowser(Browsers browser) {
		WebDriver dr = null;
		switch (browser) {

		case CHROME:
			dr = createChromeDriver(browser);
			break;

		case EDGE:
			dr = createEdgeDriver(browser);
			break;
			
		case IE:
			dr = createIEDriver(browser);
			break;

		default:
			Assert.fail("BrowserFactory.getBrowser - Invalid browser selected.");
			break;

		}
		setWebDriver(dr);
	}
	
	//public abstract void setupTest(String testName) throws MalformedURLException;
	
	protected abstract WebDriver createChromeDriver(Browsers browser);
	
	protected abstract WebDriver createIEDriver(Browsers browser);
	
	protected abstract WebDriver createEdgeDriver(Browsers browser);
	
	public static WebDriver getDriver() {
		return driver.get();
	}

	public void setWebDriver(WebDriver dr) {
		driver.set(dr);
	}

}
