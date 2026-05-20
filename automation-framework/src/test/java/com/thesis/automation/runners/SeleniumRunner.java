package java.com.thesis.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "automation.selenium.stepDefinitions", // Pekar mot Playwright stepDefs
        plugin = {"pretty", "html:target/cucumber-playwright-report.html"}
)
public class SeleniumRunner {
}
