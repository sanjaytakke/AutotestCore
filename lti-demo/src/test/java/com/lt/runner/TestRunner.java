package com.lt.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.lt.tests", "com.lt.utilities" }, plugin = {
		"pretty", "html:target/report.html", "json:target/cucumber.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, tags = "@ETE_2", dryRun = false)
// )
public class TestRunner {

}
