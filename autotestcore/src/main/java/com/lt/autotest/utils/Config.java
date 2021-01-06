package com.lt.autotest.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class Config {

	private static Properties props = new Properties();
	private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

	static {
		try {
			String propFileName = "config.properties";
			props.load(new FileInputStream("src/test/resources/properties/" + propFileName));
		} catch (FileNotFoundException e1) {
			LOGGER.error("Config - FileNotFoundException for config.properties: " + e1);
		} catch (Exception e) {
			LOGGER.error("Config - Exception: " + e);
		}
	}

	public static String getPropertyValue(String property) throws IOException {
		String result = props.getProperty(property);
		if (StringUtils.isBlank(result)) {
			LOGGER.error("Config.getPropertyValue - property missing:" + property);
			Assert.fail("Config.getPropertyValue - Property " + property + " not specified");
		}
		return result;
	}

}
