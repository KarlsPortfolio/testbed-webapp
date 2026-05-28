package com.thesis.automation.selenium.stepDefinitions;

import com.thesis.automation.selenium.pages.LoginPage;
import com.thesis.automation.selenium.pages.StartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationSteps {

    // 1. Instantiated cleanly at birth using the active, thread-isolated driver from Hooks
    private LoginPage loginPage;
    private StartPage startPage;


    // ==========================================
    // AUTHENTICATION STEPS
    // ==========================================

    @Given("I am on the login page")
    public void verifyIAmOnTheLoginPage() {

        // 1. Fetch the driver (which Hooks already pointed at the base URL home page)
        startPage = new StartPage();

        // 2. Quietly execute the required navigation step in the background
        this.loginPage = startPage.navigateToLoginPage();


        assertTrue(loginPage.isLoginScreenDisplayed(),"The bookstore application failed to render the login page layout!");

    }

    @When("I login with valid credentials {string} {string}")
    public void iLoginWithValidCredentials(String username, String password) {
        this.startPage = loginPage.loginAsValidUser(username,password);


    }

    @Then("I should be redirected to landing page")
    public void iShouldBeRedirectedToLandingPage() {
        // Write code here that turns the phrase above into concrete actions

    }

    @And("the logout button should be displayed")
    public void theLogoutButtonShouldBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions

    }



    @When("I attempt to log in with credentials {string} {string}")
    public void iAttemptToLogInWithCredentials(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("I should see error message stating {string}")
    public void iShouldSeeErrorMessageStating(String arg0) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Given("I am securely logged into the e-store application")
    public void iAmSecurelyLoggedIntoTheEStoreApplication() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("the login button should be displayed")
    public void theLoginButtonShouldBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions

    }

    @And("the logout button should not be displayed")
    public void theLogoutButtonShouldNotBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions

    }

}
