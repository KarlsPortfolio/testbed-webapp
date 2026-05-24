package com.thesis.automation.playwright.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "automation.playwright.stepDefinitions", // Pointing towards Playwright stepDefs
        plugin = {"pretty", "html:target/cucumber-playwright-report.html"}
)

public class PlaywrightRunner {
}
