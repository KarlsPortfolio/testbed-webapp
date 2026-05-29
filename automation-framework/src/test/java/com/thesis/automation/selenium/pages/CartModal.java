package com.thesis.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartModal extends BasePage {
    // 💡 Locators strictly confined to the inside of the cart modal overlay
    private final By modalContainer = By.id("cart-modal-container");
    private final By cartItemName = By.className("cart-item-title");
    private final By checkoutButton = By.id("checkout-btn");

    public CartModal() {
        super(); // Inherits global driver and default 5-second wait engine

        // 💡 CRITICAL: Guardrail ensures the modal is physically open before proceeding
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalContainer));
    }

    public String getFirstItemName() {
        return driver.findElement(cartItemName).getText();
    }

    public CheckoutPage proceedToCheckout() {
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(); // Transitions the UI to the checkout form state
    }

}
