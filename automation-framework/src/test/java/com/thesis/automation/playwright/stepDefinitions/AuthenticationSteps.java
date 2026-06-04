package com.thesis.automation.playwright.stepDefinitions;

import com.thesis.automation.playwright.pages.LoginPage;
import com.thesis.automation.playwright.pages.StartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class AuthenticationSteps {

StartPage startPage;
LoginPage loginPage;


    @Given("I am on the login page")
    public void verifyIAmOnTheLoginPage() {

//        // Fetch the driver (which Hooks already pointed at the base URL home page)
        startPage = new StartPage();
//
//        //Quietly execute the required navigation step in the background
       this.loginPage = startPage.navigateToLoginPage();
//
//
        assertThat(loginPage.getUsernameField()).isVisible();
        assertThat(loginPage.getPasswordField()).isVisible();
        assertThat(loginPage.getSubmitButton()).isVisible();



    }

    @When("I login with valid credentials {string} {string}")
    public void iLoginWithValidCredentials(String username, String password) {
       this.startPage = loginPage.loginAsValidUser(username,password);


    }

    @Then("I should be redirected to landing page")
    public void iShouldBeRedirectedToLandingPage() {
        assertThat(this.startPage.getStoreTab()).containsClass(("header__nav-btn--active"));

    }

    @And("the logout button should be displayed")
    public void theLogoutButtonShouldBeDisplayed() {
   assertThat(startPage.getNavLogoutButton()).isVisible();

    }



    @When("I attempt to log in with credentials {string} {string}")
    public void iAttemptToLogInWithCredentials(String username, String password) {
       this.loginPage = startPage.navigateToLoginPage();
        this.loginPage.loginWithInvalidCredentials(username,password);

    }

    @Then("I should see error message stating {string}")
    public void iShouldSeeErrorMessageStating(String expected) {

        assertThat(this.loginPage.getErrorMessage()).containsText(expected);

    }

    @Given("I am securely logged into the e-store application")
    public void iAmSecurelyLoggedIntoTheEStoreApplication() throws InterruptedException {
//        // Fetch the driver (which Hooks already pointed at the base URL home page)
        startPage = new StartPage();
//
//        // Quietly execute the required navigation step in the background
        this.loginPage = startPage.navigateToLoginPage();
//
        this.startPage = loginPage.loginAsValidUser("validUser","validPassword");

    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        this.startPage.logOutUser();

    }

    @Then("the login button should be displayed")
    public void theLoginButtonShouldBeDisplayed() {
    assertThat(loginPage.getNavLoginButton()).isVisible();


    }

    @And("the logout button should not be displayed")
    public void theLogoutButtonShouldNotBeDisplayed() {
    assertThat(loginPage.getNavLogoutButton()).isHidden();

    }

}
