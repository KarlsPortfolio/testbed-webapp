package com.thesis.automation.selenium.stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Hooks {

    // ThreadLocal acts as a secure isolation vault for multi-threaded parallel execution
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @Before
    public void setUp() {
        // Hook captures the System Property cleanly at launch
        String baseUrl = System.getProperty("site.url", "https://karlsportfolio.github.io/testbed-webapp/");

        // Read configuration inputs from your Maven CLI execution switches
        String browser = System.getProperty("browser", "chrome");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        WebDriver localDriver;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            localDriver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("-headless");
            }
            localDriver = new FirefoxDriver(options);

        } else {
            throw new IllegalArgumentException("Unsupported browser framework target: " + browser);
        }

        if (!isHeadless) {
            localDriver.manage().window().maximize();
        }

        // 💡 The browser navigates immediately right at birth
        localDriver.get(baseUrl);

        // 💡 Bind this specific browser instance to the active CPU thread memory space
        driverThreadLocal.set(localDriver);
    }

    @After
    public void tearDown() {
        WebDriver localDriver = driverThreadLocal.get();
        if (localDriver != null) {
            localDriver.quit();
        }
        // 💡 Clean up the thread allocation to prevent system memory leaks
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }

    /**
     * 💡 Global Utility Accessor Method
     * This allows your Page Objects and Step Definition classes to securely grab
     * the exact driver session assigned to their current running test execution.
     */
    public static WebDriver getDriver() {
        WebDriver activeDriver = driverThreadLocal.get();
        if (activeDriver == null) {
            throw new IllegalStateException("WebDriver context has not been initialized on this thread yet!");
        }
        return activeDriver;
    }
}