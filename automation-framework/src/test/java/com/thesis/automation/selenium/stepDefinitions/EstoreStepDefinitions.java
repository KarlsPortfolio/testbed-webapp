package com.thesis.automation.selenium.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.util.List;
import java.util.Map;

public class EstoreStepDefinitions {


    // ==========================================
    // CART MANAGEMENT STEPS
    // ==========================================



    @And("I am viewing the book catalog")
    public void iAmViewingTheBookCatalog() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I add the book {string} to my cart")
    public void iAddTheBookToMyCart(String arg0) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("my shopping cart badge should display {string}")
    public void myShoppingCartBadgeShouldDisplay(String arg0) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Given("I have the book {string} in my cart")
    public void iHaveTheBookInMyCart(String arg0) {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I remove the book {string} from my cart")
    public void iRemoveTheBookFromMyCart(String arg0) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("my shopping cart should be completely empty")
    public void myShoppingCartShouldBeCompletelyEmpty() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I change the quantity of {string} to {string}")
    public void iChangeTheQuantityOfTo(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("my cart subtotal should dynamically update for {string} items")
    public void myCartSubtotalShouldDynamicallyUpdateForItems(String arg0) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Given("I have multiple books in my shopping cart")
    public void iHaveMultipleBooksInMyShoppingCart() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I clear all items from my cart")
    public void iClearAllItemsFromMyCart() {
        // Write code here that turns the phrase above into concrete actions

    }

    // ==========================================
    // CHECKOUT STEPS
    // ==========================================


    @And("I have items in my shopping cart")
    public void iHaveItemsInMyShoppingCart() {
        // Write code here that turns the phrase above into concrete actions

    }

    @And("I am proceeding through the checkout process")
    public void iAmProceedingThroughTheCheckoutProcess() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I submit the shipping form with a missing {string}")
    public void iSubmitTheShippingFormWithAMissing(String arg0) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("the checkout submission should be blocked")
    public void theCheckoutSubmissionShouldBeBlocked() {
        // Write code here that turns the phrase above into concrete actions

    }

    @And("the system should flag the {string} with the message {string}")
    public void theSystemShouldFlagTheWithTheMessage(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions

    }

    // ==========================================
    // END-TO-END JOURNEY STEPS
    // ==========================================

    @And("I have a completely empty shopping cart")
    public void iHaveACompletelyEmptyShoppingCart() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I add the following books to my cart:")
    public void iAddTheFollowingBooksToMyCart(List<Map<String, String>> booksTable) {
        // Write code here that turns the phrase above into concrete actions
        // 1. Loop through each row of the Gherkin table
        for (Map<String, String> row : booksTable) {

            // 2. Extract values using your Gherkin column headers as the keys
            String title = row.get("bookTitle");
            int quantity = Integer.parseInt(row.get("quantity"));

            // 3. Print or pass these directly to your Page Object Model layer
            System.out.println("Processing E2E Item -> Title: " + title + " | Qty: " + quantity);

            // Example POM loop integration:
            // for (int i = 0; i < quantity; i++) {
            //     catalogPage.addBookToCart(title);
            // }
        }

    }

    @And("I proceed to the checkout portal")
    public void iProceedToTheCheckoutPortal() {
        // Write code here that turns the phrase above into concrete actions

    }

    @And("I complete the shipping form using the {string} profile")
    public void iCompleteTheShippingFormUsingTheProfile(String arg0) {
        // Write code here that turns the phrase above into concrete actions

    }

    @And("I finalize the transaction by placing the order")
    public void iFinalizeTheTransactionByPlacingTheOrder() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("I should be redirected to the order confirmation summary page")
    public void iShouldBeRedirectedToTheOrderConfirmationSummaryPage() {
        // Write code here that turns the phrase above into concrete actions

    }
}
