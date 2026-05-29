package com.thesis.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // Locators
    private final By usernameField = By.id("login-username");
    private final By passwordField = By.id("login-password");
    private final By loginButton = By.id("login-submit");
    private final By errorMessage = By.id("login-error");
    //private final By navLoginButton = By.id("login-btn");


    public LoginPage() {

        super(); // 🎯 Automatically gets a clean, isolated 5-second wait
    }


    public void logins(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
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
        //Clearing fields before text input to support multiple iterations
        driver.findElement(usernameField).clear();
        driver.findElement(passwordField).clear();

        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new StartPage();
    }

    public void loginWithInvalidCredentials(String username, String password) {
        //Clearing fields before text input to support multiple iterations
        driver.findElement(usernameField).clear();
        driver.findElement(passwordField).clear();

        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();

    }


}


