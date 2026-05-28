package com.thesis.automation.selenium.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    //Selectors
    private final By navLoginButton = By.id("login-btn");

    // 💡 Constructor 1: The standard default
    public BasePage(WebDriver driver) {
        this(driver, 5); // Automatically chains to Constructor 2 with a default of 5 seconds
    }

    // 💡 Constructor 2: The flexible controller
    public BasePage(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        // Instantiates a brand-new wait engine tailored exactly to the requested speed
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public LoginPage navigateToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(navLoginButton)).click();
        return new LoginPage(driver);
    }
}
