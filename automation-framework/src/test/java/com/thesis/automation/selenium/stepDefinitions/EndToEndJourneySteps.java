package com.thesis.automation.selenium.stepDefinitions;

import com.thesis.automation.selenium.factory.CustomerFactory;
import com.thesis.automation.selenium.pages.CheckoutPage;
import com.thesis.automation.selenium.pages.StartPage;
import com.thesis.automation.selenium.pages.SuccessPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndToEndJourneySteps {

    StartPage startPage = new StartPage();
    CheckoutPage checkoutPage;
    SuccessPage successPage;

    @And("I have a completely empty shopping cart")
    public void iHaveACompletelyEmptyShoppingCart() {
        this.startPage.clearEntireCartSilently();

        assertTrue(this.startPage.isCartEmpty().isDisplayed());

        this.startPage.closeCartButton();

    }

    @When("I add the following books to my cart:")
    public void iAddTheFollowingBooksToMyCart(List<Map<String, String>> booksTable) {
        // Write code here that turns the phrase above into concrete actions
        // 1. Loop through each row of the Gherkin table
        for (Map<String, String> row : booksTable) {


                // 2. Extract values using your Gherkin column headers as the keys
                String title = row.get("bookTitle");
                int quantity = Integer.parseInt(row.get("quantity"));

                for (int i = 0; i < quantity; i++) {
                    this.startPage.addToCart(title);
                }
            }
        }



    @And("I proceed to the checkout portal")
    public void iProceedToTheCheckoutPortal() {
        this.checkoutPage = this.startPage.navigateToCheckoutPage();

    }

    @And("I complete the shipping form using the {string} profile")
    public void iCompleteTheShippingFormUsingTheProfile() {



    }

    @And("I finalize the transaction by placing the order")
    public void iFinalizeTheTransactionByPlacingTheOrder() {
        this.successPage = this.checkoutPage.clickSubmitButton();

    }

    @Then("I should be redirected to the order confirmation summary page")
    public void iShouldBeRedirectedToTheOrderConfirmationSummaryPage() {
        assertTrue(this.successPage.getSuccessMessage().isDisplayed());



    }

    @And("I complete the shipping form using a valid profile")
    public void iCompleteTheShippingFormUsingAValidProfile() throws InterruptedException {
        checkoutPage = new CheckoutPage();
        this.checkoutPage.fillCheckoutFieldsCustomer(CustomerFactory.createValidCustomer(), false);
    }
}
