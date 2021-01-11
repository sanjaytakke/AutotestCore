package com.lt.utilities;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.autotest.utils.Config;
import com.lt.base.TestBase;
import com.lt.constants.Constants;

public class CommonFunctions extends TestBase {

	Logger LOGGER = LoggerFactory.getLogger(CommonFunctions.class);
	public String applicationEnv = null;

	public void openUrl() {
		
		try {
		if (null != System.getenv("APPLICATION_ENV")) {
			applicationEnv = System.getenv("APPLICATION_ENV");
			scenario.log("Selecting Environment variable URL : "+applicationEnv);
			
		}else {
			applicationEnv = Config.getPropertyValue("application.env");
			scenario.log("Selecting Environment from Config file : "+applicationEnv);
		}
		
		if("test.env".equalsIgnoreCase(applicationEnv)) {	
			getDriver().get(Constants.TESTENVIRONMENT);
			scenario.log("URL : "+Constants.TESTENVIRONMENT);
		}else if("uat.env".equalsIgnoreCase(applicationEnv)) {
			getDriver().get(Constants.UATENVIRONMENT);
			scenario.log("URL : "+Constants.UATENVIRONMENT);
		}
		} catch (Exception e) {
			LOGGER.error("CommonFunctions-openUrl : " + e.toString());
		}
	}
}
