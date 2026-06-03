package com.thesis.automation.playwright.stepDefinitions;

import com.thesis.automation.selenium.pages.LoginPage;
import com.thesis.automation.selenium.pages.StartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationSteps {




    @Given("I am on the login page")
    public void verifyIAmOnTheLoginPage() {

//        // Fetch the driver (which Hooks already pointed at the base URL home page)
//        startPage = new StartPage();
//
//        //Quietly execute the required navigation step in the background
//        this.loginPage = startPage.navigateToLoginPage();
//
//
//        assertTrue(loginPage.isLoginScreenDisplayed(),"The bookstore application failed to render the login page layout!");

    }

    @When("I login with valid credentials {string} {string}")
    public void iLoginWithValidCredentials(String username, String password) {
//        this.startPage = loginPage.loginAsValidUser(username,password);


    }

    @Then("I should be redirected to landing page")
    public void iShouldBeRedirectedToLandingPage() {
//        assertTrue(this.startPage.isStoreActive().isDisplayed(),"The bookstore application failed to render the start page layout!");

    }

    @And("the logout button should be displayed")
    public void theLogoutButtonShouldBeDisplayed() {
//        assertTrue(this.startPage.presenceOfLogoutBtn().isDisplayed(), "The bookstore application failed to render the logout button");

    }



    @When("I attempt to log in with credentials {string} {string}")
    public void iAttemptToLogInWithCredentials(String username, String password) {
//        this.loginPage = startPage.navigateToLoginPage();
//        this.loginPage.loginWithInvalidCredentials(username,password);

    }

    @Then("I should see error message stating {string}")
    public void iShouldSeeErrorMessageStating(String expected) {
//        assertTrue(this.loginPage.getErrorMessage().contains(expected));
    }

    @Given("I am securely logged into the e-store application")
    public void iAmSecurelyLoggedIntoTheEStoreApplication() throws InterruptedException {
//        // Fetch the driver (which Hooks already pointed at the base URL home page)
//        startPage = new StartPage();
//
//        // Quietly execute the required navigation step in the background
//        this.loginPage = startPage.navigateToLoginPage();
//
//        this.startPage = loginPage.loginAsValidUser("validUser","validPassword");

    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
//        this.startPage.clickLogout();

    }

    @Then("the login button should be displayed")
    public void theLoginButtonShouldBeDisplayed() {
//        assertTrue(this.startPage.presenceOfLoginBtn().isDisplayed(), "The bookstore application failed to render the login button");


    }

    @And("the logout button should not be displayed")
    public void theLogoutButtonShouldNotBeDisplayed() {
//        assertTrue(this.startPage.invisibilityOfLogoutBtn(),"The bookstore application failed to hide the logout button");

    }

}
