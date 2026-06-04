package com.thesis.automation.playwright.pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.thesis.automation.playwright.stepDefinitions.Hooks;

public abstract class BasePage {
    protected Page page;

    //  LOCATORS
    protected final Locator navLoginButton;
    protected final Locator navCartIconButton;
    protected final Locator navCheckoutTab;
    protected final Locator cartCountBadge;
    protected final Locator navLogoutButton;


    public BasePage() {
        this.page = Hooks.getPage();

        //Finds <button> containing text "Login"
        this.navLoginButton = page.locator("header")
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Login"));

        //Finds <button> containing text "Login"
        this.navLogoutButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout"));

        // Finds <button> with attribute aria-label="Open cart"
        this.navCartIconButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open cart"));

        // Finds <button> containing text "Checkout"
        this.navCheckoutTab = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Checkout"));

        // Spans lack generic structural roles, target via id/text node queries natively
        this.cartCountBadge = page.locator("#cart-count-badge");
    }

    public Locator getCartCountBadge() {
        return cartCountBadge;
    }

    public Locator getNavLogoutButton() {
        return navLogoutButton;
    }

    public Locator getNavLoginButton() {
        return navLoginButton;
    }

    public StartPage logOutUser() {
        navLogoutButton.click();
        return new StartPage();
    }


    public LoginPage navigateToLoginPage() {
        navLoginButton.click();
        return new LoginPage();
    }

    public CheckoutPage navigateToCheckoutPage() {
        navCheckoutTab.click();
        return new CheckoutPage();
    }


    public CartModal openCartModal() {
        navCartIconButton.click();
        return new CartModal();
    }
}
