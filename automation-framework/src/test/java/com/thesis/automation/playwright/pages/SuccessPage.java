package com.thesis.automation.playwright.pages;

import com.microsoft.playwright.Locator;
import com.thesis.automation.playwright.stepDefinitions.Hooks;

public class SuccessPage extends BasePage {

    // LOCATORS
    private final Locator successMsg;

    public SuccessPage() {
        super(); // Smoothly binds the inherited page engine from BasePage

        // Target using your dedicated test-id attribute or standard structural ID
        this.successMsg = page.locator("#order-success-message");
    }


    public String getSuccessMessageText() {
        return successMsg.textContent().trim();
    }


    public Locator getSuccessMessageLocator() {
        return successMsg;
    }
}
