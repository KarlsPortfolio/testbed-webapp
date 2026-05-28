package com.thesis.automation.selenium.pages;

import com.thesis.automation.selenium.models.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    // Locators
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("submit-btn");
    private final By errorMessage = By.id("login-error");
    private final By navLoginButton = By.id("login-btn");



    public LoginPage(WebDriver driver) {

        super(driver); // 🎯 Automatically gets a clean, isolated 5-second wait
            }



    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

    }

    public boolean isLoginScreenDisplayed() {
        try {
            // Uses the inherited parent 'wait' engine to watch the DOM
            wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            return driver.findElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public StartPage loginAsValidUser(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new StartPage(driver);
    }
}


