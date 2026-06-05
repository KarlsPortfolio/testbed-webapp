package com.thesis.automation.playwright.pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.thesis.automation.playwright.stepDefinitions.Hooks;


public class StartPage extends BasePage {

    // LOCATORS
    private final Locator loginSuccessMsg;
    private final Locator storeTab;

    public StartPage() {
        super(); // Smoothly initializes the inherited page engine from BasePage

        // Target via its unique semantic placement or native text container
        this.loginSuccessMsg = page.locator("#header-user-name");
        this.storeTab = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Store"));
    }

    public Locator getStoreTab() {
        return storeTab;
    }

    public void addToCart(String bookTitle) {
        page.locator(".book-card")
                .filter(new Locator.FilterOptions().setHasText(bookTitle))
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to Cart"))
                .click();
    }

    public void clickLogout() {
        navLogoutButton.click();
    }

}
