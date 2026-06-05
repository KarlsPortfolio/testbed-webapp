package com.thesis.automation.playwright.stepDefinitions;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.util.Collections;

public class Hooks {
    private static Playwright playwright;
    private static Browser browserInstance;
    private static BrowserContext context;
    private static Page page;

    @Before
    public void setUp() {
        // 1. PRODUCTION DEFAULT: Falls back to the deployed live site when running locally
        //String baseUrl = System.getProperty("site.url", "https://karlsportfolio.github.io/testbed-webapp/");
        String baseUrl = System.getProperty("site.url", "http://127.0.0.1:5500/bookstore-app/");

        // 2. CONFIGURATION SWITCHES: Defaults to chrome/headed for easy local debugging
        String browserChoice = System.getProperty("browser", "chrome");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        // 3. Boot the core Playwright engine
        playwright = Playwright.create();

        // Configure the browser launching parameters
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(isHeadless);

        // 4. Handle window maximizing based on mode
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();

        if (!isHeadless) {
            // 💡 The Playwright Maximization Pattern: Pass the OS-native argument to the window manager
            launchOptions.setArgs(Collections.singletonList("--start-maximized"));
            // Tell the context overlay to nullify the default viewport bounds so it follows the window frame
            contextOptions.setViewportSize(null);
        } else {
            // Clean, standardized container viewport size for CI execution
            contextOptions.setViewportSize(1920, 1080);
        }

        // 5. Native engine assignments (No heavy binary driver managers needed!)
        if (browserChoice.equalsIgnoreCase("firefox")) {
            browserInstance = playwright.firefox().launch(launchOptions);
        } else {
            // Default option: Drives the core Chromium engine
            browserInstance = playwright.chromium().launch(launchOptions);
        }

        // 6. Build the browser lifecycle layers
        context = browserInstance.newContext(contextOptions);
        page = context.newPage();

        // 7. Fire the initial application navigation event
        page.navigate(baseUrl);
    }

    @After
    public void tearDown() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browserInstance != null) browserInstance.close();
        if (playwright != null) playwright.close();
    }

    /**
     * The Global Selector Gateway for the Page Objects
     */
    public static Page getPage() {
        return page;
    }
}