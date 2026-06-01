package com.thesis.automation.selenium.stepDefinitions;

import com.thesis.automation.selenium.pages.LoginPage;
import com.thesis.automation.selenium.pages.StartPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//public class CartManagementSteps {
//    // 1. Instantiated cleanly at birth using the active, thread-isolated driver from Hooks
//    private LoginPage loginPage;
//    private StartPage startPage;
//
//
//
//    @And("I am viewing the book catalog")
//    public void iAmViewingTheBookCatalog() {
//        startPage = new StartPage();
//
//        assertTrue(this.startPage.isStoreActive().isDisplayed(),"The bookstore application failed to render the start page layout!");
//
//    }
//
//    @When("I add the book {string} to my cart")
//    public void iAddTheBookToMyCart(String bookTitle) throws InterruptedException {
//        this.startPage.addToCart(bookTitle);
//
//
//
//    }
//
//    @Then("my shopping cart badge should display {string}")
//    public void myShoppingCartBadgeShouldDisplay(String expected) {
//       int actual = Integer.parseInt(this.startPage.getCartCount());
//       assertEquals(Integer.parseInt(expected), actual);
//
//
//    }
//
//    @Given("I have the book {string} in my cart")
//    public void iHaveTheBookInMyCart(String bookTitle) {
//        startPage = new StartPage();
//        this.startPage.addToCart(bookTitle);
//        String expected = bookTitle;
//
//        assertEquals(expected,this.startPage.checkBookInCart(bookTitle));
//
//
//    }
//
//    @When("I remove the book {string} from my cart")
//    public void iRemoveTheBookFromMyCart(String bookTitle) {
//        assertTrue(this.startPage.removeBookFromCart(bookTitle));
//
//
//
//    }
//
//    @Then("my shopping cart should be completely empty")
//    public void myShoppingCartShouldBeCompletelyEmpty() {
//        assertTrue(this.startPage.isCartEmpty().isDisplayed());
//
//    }
//
//    @When("I change the quantity of {string} to {string}")
//    public void iChangeTheQuantityOfTo(String bookTitle, String amount) throws InterruptedException {
//
//
//       int actual = Integer.parseInt(this.startPage.increaseQuantityInCart(bookTitle,amount));
//
//       assertEquals(Integer.parseInt(amount), actual);
//
//
//
//    }
//
//    @Then("my cart subtotal should dynamically update for {string} items")
//    public void myCartSubtotalShouldDynamicallyUpdateForItems(String amount) {
//        int actual = Integer.parseInt(this.startPage.getCartCount());
//        assertEquals(Integer.parseInt(amount), actual);
//
//
//    }
//
//
//
//    @When("I clear all items from my cart")
//    public void iClearAllItemsFromMyCart() throws InterruptedException {
//        this.startPage.clearEntireCartSilently();
//        Thread.sleep(10000);
//        assertTrue(this.startPage.isCartEmpty().isDisplayed());
//        Thread.sleep(10000);
//
//
//
//
//    }
//
//    @Given("the following items are in my cart:")
//    public void theFollowingItemsAreInMyCart(List<Map<String, String>> booksTable) {
//        StartPage startPage = new StartPage();
//        for (Map<String, String> row : booksTable) {
//
//            // 2. Extract values using your Gherkin column headers as the keys
//            String title = row.get("bookTitle");
//            int quantity = Integer.parseInt(row.get("quantity"));
//
//            for (int i = 0; i < quantity; i++) {
//             this.startPage.addToCart(title);
//            }
//        }
//    }
//}
