package com.thesis.automation.selenium.stepDefinitions;

import com.thesis.automation.selenium.factory.CustomerFactory;
import com.thesis.automation.selenium.pages.CheckoutPage;
import com.thesis.automation.selenium.pages.LoginPage;
import com.thesis.automation.selenium.pages.StartPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CheckoutSteps {

    // 1. Instantiated cleanly at birth using the active, thread-isolated driver from Hooks
    private LoginPage loginPage;
    private StartPage startPage = new StartPage();
    private CheckoutPage checkoutPage;



    @And("I have items in my shopping cart")
    public void iHaveItemsInMyShoppingCart() {
        // Write code here that turns the phrase above into concrete actions

    }

    @And("I am proceeding through the checkout process")
    public void iAmProceedingThroughTheCheckoutProcess() {
        this.checkoutPage = this.startPage.navigateToCheckoutPage();

    }

    @When("I submit the shipping form with a missing {string}")
    public void iSubmitTheShippingFormWithAMissing(String missingField) {
        this.checkoutPage.fillCheckoutFieldsCustomer(CustomerFactory.createInvalidatedProfile(missingField), true);

    }

    @Then("the checkout submission should be blocked")
    public void theCheckoutSubmissionShouldBeBlocked() {
        assertFalse(this.checkoutPage.isSubmitBtnActive());

    }

    @And("the system should flag the {string} with the message {string}")
    public void theSystemShouldFlagTheWithTheMessage(String missingField, String expectedErrorMessage) {
        assertEquals(expectedErrorMessage,this.checkoutPage.getErrorMessage(missingField.toLowerCase()));


    }

    @And("I have the following items in my shopping cart:")
    public void iHaveTheFollowingItemsInMyShoppingCart(List<Map<String, String>> booksTable) throws InterruptedException {

        //StartPage startPage = new StartPage();
        for (Map<String, String> row : booksTable) {

            // 2. Extract values using your Gherkin column headers as the keys
            String title = row.get("bookTitle");
            int quantity = Integer.parseInt(row.get("quantity"));

            for (int i = 0; i < quantity; i++) {
                this.startPage.addToCart(title);
            }
        }
    }


}

