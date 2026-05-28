package com.thesis.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

    // 💡 Inheriting from BasePage
    public class StartPage extends BasePage {

        private final By logoutButton = By.id("logout-btn");
        private final By loginSuccessMsg = By.id("header-user-name");
        private final By logoutBtn = By.id("logout-btn");

        public StartPage(WebDriver driver) {
            super(driver); // Automatically initializes the driver and WebDriverWait from the parent
        }

        public void clickLogout() {
            // Use the inherited 'wait' engine natively to guard the action
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        }

        public String getLoginGreetMsg() {
            // Use the inherited 'wait' engine natively to guard the action
            return wait.until(ExpectedConditions.presenceOfElementLocated(loginSuccessMsg)).getText();
        }


    }

