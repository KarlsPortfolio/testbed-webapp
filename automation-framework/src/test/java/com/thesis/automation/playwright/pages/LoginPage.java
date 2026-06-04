package com.thesis.automation.playwright.pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    // 🎯 USER-FIRST ACCESSIBLE LOCATORS
    // Bound seamlessly via your HTML's matching "for" and "id" accessibility labels!
    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator submitButton;
    private final Locator errorMessage;

    public LoginPage() {
        super(); // Initializes the shared parent engine

        // Locate input elements using their descriptive labels
        this.usernameField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username"));

        // Passwords have a unique distinct cryptographic ARIA role context
        this.passwordField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));

        // Pinpoints the specific login action submission button inside the view layout
        this.submitButton = page.locator("form")
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Login"));

        // Content blocks leverage plain text node structural selectors
        this.errorMessage = page.locator("#login-error");
    }

    public Locator getUsernameField() {
        return usernameField;
    }

    public Locator getPasswordField() {
        return passwordField;
    }

    public Locator getSubmitButton() {
        return submitButton;
    }

    public Locator getErrorMessage() {
        // Auto-waits for the container to become visible and populate text
        return errorMessage;
    }

    public StartPage loginAsValidUser(String username, String password) {
        // AUTO-CLEARING: .fill() implicitly empties the element before typing!
        usernameField.fill(username);
        passwordField.fill(password);
        submitButton.click();

        // Fluid transition handoff
        return new StartPage();
    }

    public void loginWithInvalidCredentials(String username, String password) {
        usernameField.fill(username);
        passwordField.fill(password);
        submitButton.click();
    }
}
