package com.thesis.automation.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.thesis.automation.playwright.models.Customer;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {


    private final Locator fullNameField;
    private final Locator emailField;
    private final Locator addressField;
    private final Locator cityField;
    private final Locator zipCodeField;
    private final Locator cardNumberField; //
    private final Locator cvvField;
    private final Locator submitBtn;

    public CheckoutPage() {
        super(); // Initializes the shared parent context

        // Locate each checkout field via its visible, user-facing label text
        this.fullNameField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Full Name"));
        this.emailField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email"));
        this.addressField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Address"));
        this.cityField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("City"));
        this.zipCodeField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Zip Code"));
        this.cardNumberField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Card Number"));
        this.cvvField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("CVV"));

        // Pinpoints the final structural action button
        this.submitBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Place Order"));
    }

    public Locator getSubmitBtn() {
        return submitBtn;
    }

    public void fillCheckoutFieldsCustomer(Customer customer) {
        fullNameField.fill(customer.getFullName());
        emailField.fill(customer.getEmail());
        addressField.fill(customer.getAddress());
        cityField.fill(customer.getCity());
        zipCodeField.fill(customer.getZipCode());
        cardNumberField.fill(customer.getCreditCard());
        cvvField.fill(customer.getCvv());

    }

    public SuccessPage clickSubmitButton() {
        submitBtn.click();
        return new SuccessPage(); // Handles smooth workflow route transition
    }


    public void forceClickSubmitOrderBtn(){
        page.locator("#place-order-button").click(new Locator.ClickOptions().setForce(true));
    }

    public Locator getErrorMessageLocator(String missingField) {
        String sanitizedId = missingField.toLowerCase();
        String dynamicIdSelector = "#" + sanitizedId + "-error";

        forceClickSubmitOrderBtn();

        return page.locator(dynamicIdSelector);
    }

}