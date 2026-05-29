package com.thesis.automation.selenium.pages;


import com.thesis.automation.selenium.stepDefinitions.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    //Selectors
    //protected final By navLoginButton = By.cssSelector("[data-testid='login-submit']");
    protected final By navLoginButton = By.cssSelector("#auth-button[name='login-btn']");
    protected final By navLogoutButton = By.cssSelector("#auth-button[name='logout-btn']");
    protected final By navCartIconButton = By.id("nav-cart-icon");

    // 💡 Constructor 1: The standard default (Takes 0 arguments)
    public BasePage() {
        // Automatically calls Constructor 2 below, passing a default of 5 seconds
        this(5);
    }

    // 💡 Constructor 2: The flexible time controller (Takes only the timeout value)
    public BasePage(int timeoutInSeconds) {
        this.driver = Hooks.getDriver(); // Pulls the shared driver from the global vault
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public LoginPage navigateToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(navLoginButton)).click();
        return new LoginPage();
    }
}
