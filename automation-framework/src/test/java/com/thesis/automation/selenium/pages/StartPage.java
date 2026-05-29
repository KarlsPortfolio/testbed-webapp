package com.thesis.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

    // 💡 Inheriting from BasePage
    public class StartPage extends BasePage {

        //private final By logoutButton = By.id("logout-btn");
        private final By loginSuccessMsg = By.id("header-user-name");
        private final By storeTabActive = By.cssSelector("#store-tab.header__nav-btn--active");


        public StartPage() {
            super(10); // Automatically initializes the driver and WebDriverWait from the parent
        }

        public void clickLogout() {
            // Use the inherited 'wait' engine natively to guard the action
            wait.until(ExpectedConditions.elementToBeClickable(navLogoutButton)).click();
        }

        public WebElement presenceOfLogoutBtn() {

                return wait.until(ExpectedConditions.elementToBeClickable(navLogoutButton));

        }

        public Boolean invisibilityOfLogoutBtn() {

            return wait.until(ExpectedConditions.invisibilityOfElementLocated(navLogoutButton));

        }

        public WebElement presenceOfLoginBtn() {
            // Use the inherited 'wait' engine natively to guard the action
            return wait.until(ExpectedConditions.elementToBeClickable(navLoginButton));
        }

        public String getLoginGreetMsg() {
            // Use the inherited 'wait' engine natively to guard the action
            return wait.until(ExpectedConditions.presenceOfElementLocated(loginSuccessMsg)).getText();
        }

        public WebElement isStoreActive() {
            // Use the inherited 'wait' engine natively to guard the action
            return wait.until(ExpectedConditions.presenceOfElementLocated(storeTabActive));
        }

        public CartModal openCartModal() {
            driver.findElement(navCartIconButton).click();
            return new CartModal();
        }


    }

