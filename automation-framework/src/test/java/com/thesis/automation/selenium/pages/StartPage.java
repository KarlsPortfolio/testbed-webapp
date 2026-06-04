package com.thesis.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

// 💡 Inheriting from BasePage
public class StartPage extends BasePage {


    private final By loginSuccessMsg = By.id("header-user-name");
    private final By storeTabActive = By.cssSelector("#store-tab.header__nav-btn--active");
    private final By bookTitles = By.className("book-card__title-button");



    public StartPage() {
        super(10); // Automatically initializes the driver and WebDriverWait from the parent
    }

    public void addToCart(String bookTitle) {
        List<WebElement> books = driver.findElements(bookTitles);

        By addToCartButton = null;
        for (WebElement book : books) {

            if (book.getText().equalsIgnoreCase(bookTitle)) {
                System.out.println("Book title exists!");

                String bookId = book.getAttribute("data-book-id");

                addToCartButton = By.id("add-to-cart-" + bookId);

            }

        }

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

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
        return wait.until(ExpectedConditions.presenceOfElementLocated(loginSuccessMsg)).getText();
    }

    public WebElement isStoreActive() {
        // Use the inherited 'wait' engine natively to guard the action
        return wait.until(ExpectedConditions.presenceOfElementLocated(storeTabActive));
    }


}

