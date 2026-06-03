package com.thesis.automation.selenium.stepDefinitions;

import com.thesis.automation.selenium.factory.CustomerFactory;
import com.thesis.automation.selenium.pages.CartModal;
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
    CartModal cartModal;

    @And("I have a completely empty shopping cart")
    public void iHaveACompletelyEmptyShoppingCart()  {

        this.cartModal = this.startPage.openCartModal();


        this.cartModal.clearEntireCartSilently();


        assertTrue(this.cartModal.isCartEmpty().isDisplayed());

        this.cartModal.closeCartButton();



    }

    @When("I add the following books to my cart:")
    public void iAddTheFollowingBooksToMyCart(List<Map<String, String>> booksTable) {

        StartPage activeStartPage = new StartPage();

        for (Map<String, String> row : booksTable) {


                // Extract values using your Gherkin column headers as the keys
                String title = row.get("bookTitle");
                int quantity = Integer.parseInt(row.get("quantity"));

                for (int i = 0; i < quantity; i++) {
                    activeStartPage.addToCart(title);
                }
            }
        }



    @And("I proceed to the checkout portal")
    public void iProceedToTheCheckoutPortal() {
        this.checkoutPage = this.checkoutPage.navigateToCheckoutPage();

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
    public void iCompleteTheShippingFormUsingAValidProfile()  {
        checkoutPage = new CheckoutPage();
        this.checkoutPage.fillCheckoutFieldsCustomer(CustomerFactory.createValidCustomer(), false);
    }
}
