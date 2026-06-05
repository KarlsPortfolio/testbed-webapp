package com.thesis.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartModal extends BasePage {
    // 💡 Locators strictly confined to the inside of the cart modal overlay
    private final By modalContainer = By.id("cart-modal");
    private final By cartItemName = By.className("cart-item-title");
    private final By checkoutButton = By.id("checkout-btn");
    private final By cartModalHeader = By.className("cart-modal__header");


    public CartModal() {
        super(10); // Inherits global driver and sets custom 10-second wait engine



    }


    private final By cartCountBadge = By.id("cart-count-badge");
    private final By modalCartBookTitle = By.className("cart-modal__item-title");
    private final By modalCartItems = By.className("cart-modal__item");
    private final By emptyCartMessage = By.id("empty-cart-message");
    private final By closeCartBtn = By.id("close-cart");
    private final By cartSubtotal = By.id("cart-total");



    public String getFirstItemName() {
        return driver.findElement(cartItemName).getText();
    }

    public WebElement isCartEmpty(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(emptyCartMessage));
    }

    public void closeCartButton(){
        wait.until(ExpectedConditions.elementToBeClickable(closeCartBtn)).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalContainer));
    }

    public void clearEntireCartSilently() {

        // Locate the dynamic list of whatever remove buttons are currently visible
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[id^='remove-item-']"));

        // Natively loop until the UI list container is drained
        while (!removeButtons.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0))).click();
            // Wait for the DOM to update, then refresh the list snapshot
            wait.until(ExpectedConditions.stalenessOf(removeButtons.get(0)));
            removeButtons = driver.findElements(By.cssSelector("[id^='remove-item-']"));
        }

    }

    public void increaseQuantityInCart(String bookTitle, String amount) {
        List<WebElement> books = driver.findElements(modalCartItems);

        By cartQuantityField = null;


        for(WebElement book : books){
            String currentTitle = wait.until(ExpectedConditions.presenceOfElementLocated(modalCartBookTitle)).getText();

            if(currentTitle.equalsIgnoreCase(bookTitle)){
                System.out.println("Book title exists!");

                String bookId = book.getAttribute("id");
                String itemID[] = bookId.split("-");

                System.out.println(itemID[2]);

                char id = bookId.charAt(bookId.length()-1);


                cartQuantityField = By.id("cart-qty-"+ id);


            }



        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(cartQuantityField)).click();

        driver.findElement(cartQuantityField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(cartQuantityField).sendKeys(amount);




    }

    public boolean removeBookFromCart(String bookTitle){
        List<WebElement> books = driver.findElements(modalCartItems);

        By cartRemoveItemBtn = null;
        By cartItemId = null;
        for(WebElement book : books){
            String currentTitle = wait.until(ExpectedConditions.presenceOfElementLocated(modalCartBookTitle)).getText();

            if(currentTitle.equalsIgnoreCase(bookTitle)){
                System.out.println("Book title exists!");

                String bookId = book.getAttribute("data-book-id");
                String bookIds = book.getAttribute("id");
                String itemID[] = bookIds.split("-");


                char id = bookIds.charAt(bookIds.length()-1);

                cartRemoveItemBtn = By.id("remove-item-" + id);
                cartItemId = By.id("cart-item-" + id);

            }

        }
        wait.until(ExpectedConditions.elementToBeClickable(cartRemoveItemBtn)).click();
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(cartItemId));

    }

    public String getSubTotal(String expectedSubtotal)  {


        driver.findElement(cartModalHeader).click();

        // Selenium will now comfortably block execution until the UI completes the recalculation.
        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartSubtotal, expectedSubtotal));

        return driver.findElement(cartSubtotal).getText().trim();
    }



    public String checkBookInCart(String bookTitle){
        return wait.until(ExpectedConditions.presenceOfElementLocated(modalCartBookTitle)).getText();

    }

}
