package com.thesis.automation.selenium.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EstoreStepDefinitions {

    // ==========================================
    // AUTHENTICATION STEPS
    // ==========================================

    @Given("I am on the login page {string}")
    public void iAmOnTheLoginPage(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I login with valid credentials {string} {string}")
    public void iLoginWithValidCredentials(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("I should be redirected to landing page")
    public void iShouldBeRedirectedToLandingPage() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the logout button should be displayed")
    public void theLogoutButtonShouldBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I attempt to log in with credentials {string} {string}")
    public void iAttemptToLogInWithCredentials(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("I should see error message stating {string}")
    public void iShouldSeeErrorMessageStating(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("I am securely logged into the e-store application")
    public void iAmSecurelyLoggedIntoTheEStoreApplication() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("the login button should be displayed")
    public void theLoginButtonShouldBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the logout button should not be displayed")
    public void theLogoutButtonShouldNotBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    // ==========================================
    // CART MANAGEMENT STEPS
    // ==========================================



    @And("I am viewing the book catalog")
    public void iAmViewingTheBookCatalog() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I add the book {string} to my cart")
    public void iAddTheBookToMyCart(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("my shopping cart badge should display {string}")
    public void myShoppingCartBadgeShouldDisplay(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("I have the book {string} in my cart")
    public void iHaveTheBookInMyCart(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I remove the book {string} from my cart")
    public void iRemoveTheBookFromMyCart(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("my shopping cart should be completely empty")
    public void myShoppingCartShouldBeCompletelyEmpty() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I change the quantity of {string} to {string}")
    public void iChangeTheQuantityOfTo(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("my cart subtotal should dynamically update for {string} items")
    public void myCartSubtotalShouldDynamicallyUpdateForItems(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("I have multiple books in my shopping cart")
    public void iHaveMultipleBooksInMyShoppingCart() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I clear all items from my cart")
    public void iClearAllItemsFromMyCart() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    // ==========================================
    // CHECKOUT STEPS
    // ==========================================


    @And("I have items in my shopping cart")
    public void iHaveItemsInMyShoppingCart() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("I am proceeding through the checkout process")
    public void iAmProceedingThroughTheCheckoutProcess() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I submit the shipping form with a missing {string}")
    public void iSubmitTheShippingFormWithAMissing(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("the checkout submission should be blocked")
    public void theCheckoutSubmissionShouldBeBlocked() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("the system should flag the {string} with the message {string}")
    public void theSystemShouldFlagTheWithTheMessage(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    // ==========================================
    // END-TO-END JOURNEY STEPS
    // ==========================================

    @And("I have a completely empty shopping cart")
    public void iHaveACompletelyEmptyShoppingCart() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I add the following books to my cart:")
    public void iAddTheFollowingBooksToMyCart() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("I proceed to the checkout portal")
    public void iProceedToTheCheckoutPortal() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("I complete the shipping form using the {string} profile")
    public void iCompleteTheShippingFormUsingTheProfile(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("I finalize the transaction by placing the order")
    public void iFinalizeTheTransactionByPlacingTheOrder() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("I should be redirected to the order confirmation summary page")
    public void iShouldBeRedirectedToTheOrderConfirmationSummaryPage() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
