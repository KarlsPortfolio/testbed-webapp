package com.thesis.automation.selenium.stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setUp() {
        // Hook captures the System Property cleanly at launch
        String baseUrl = System.getProperty("site.url", "https://karlsportfolio.github.io/testbed-webapp/");

        // Read configuration inputs from your Maven CLI execution switches
        String browser = System.getProperty("browser", "chrome");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");

                // 🚀 CI STABILITY HACKS: Prevents Chrome from crashing inside Ubuntu runners
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("-headless");
            }
            driver = new FirefoxDriver(options);

        } else {
            throw new IllegalArgumentException("Unsupported browser framework target: " + browser);
        }

        if (!isHeadless) {
            driver.manage().window().maximize();
        }

        // 💡 The browser navigates immediately right at birth
        driver.get(baseUrl);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // 💡 Allows separate step definition files to share this single browser instance
    public static WebDriver getDriver() {
        return driver;
    }
}