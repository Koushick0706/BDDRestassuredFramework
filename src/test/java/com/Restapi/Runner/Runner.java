package com.Restapi.Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features/REST",glue = {"com.Restapi.Step_def"},
dryRun = false,
plugin={"pretty","html:target/cucumber-report-html",
		"json:target/cucumber.json",
		"junit:target/cucumber.xml"},
monochrome = true,
tags = "@All")
public class Runner {

}