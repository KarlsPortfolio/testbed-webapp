package com.thesis.automation.playwright.stepDefinitions;
import com.thesis.automation.playwright.factory.CustomerFactory;
import com.thesis.automation.playwright.pages.CheckoutPage;
import com.thesis.automation.playwright.pages.StartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutSteps {

    //Instantiated cleanly at birth using the active, thread-isolated driver from Hooks
    private StartPage startPage = new StartPage();
    private CheckoutPage checkoutPage;


    @And("I am proceeding through the checkout process")
    public void iAmProceedingThroughTheCheckoutProcess() {
        this.checkoutPage = this.startPage.navigateToCheckoutPage();

    }

    @When("I submit the shipping form with a missing {string}")
    public void iSubmitTheShippingFormWithAMissing(String missingField) {
        this.checkoutPage.fillCheckoutFieldsCustomer(CustomerFactory.createInvalidatedProfile(missingField));

    }

    @Then("the checkout submission should be blocked")
    public void theCheckoutSubmissionShouldBeBlocked() {
        assertThat(this.checkoutPage.getSubmitBtn()).isDisabled();

    }

    @And("the system should flag the {string} with the message {string}")
    public void theSystemShouldFlagTheWithTheMessage(String missingField, String expectedErrorMessage) {
//        assertEquals(expectedErrorMessage,this.checkoutPage.getErrorMessage(missingField.toLowerCase()));
        assertThat(this.checkoutPage.getErrorMessageLocator(missingField)).hasText(expectedErrorMessage);


    }

    @And("I have the following items in my shopping cart:")
    public void iHaveTheFollowingItemsInMyShoppingCart(List<Map<String, String>> booksTable) {

        for (Map<String, String> row : booksTable) {
//
//            // Extract values using your Gherkin column headers as the keys
            String title = row.get("bookTitle");
            int quantity = Integer.parseInt(row.get("quantity"));
//
            for (int i = 0; i < quantity; i++) {
                this.startPage.addToCart(title);
            }
        }
    }


}

