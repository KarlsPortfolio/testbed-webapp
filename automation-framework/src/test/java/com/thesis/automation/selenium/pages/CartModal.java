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
        super(10); // Inherits global driver and default 5-second wait engine

        //  Guardrail ensures the modal is physically open before proceeding

    }

    private final By bookTitles = By.className("book-card__title-button");
    private final By booksArticles = By.className("book-card");
    private final By modalProductPage = By.cssSelector("#pdp-body .button");
    private final By cartCountBadge = By.id("cart-count-badge");
    private final By addToCartBtn = By.id("add-to-cart-");
    private final By modalCartBookTitle = By.className("cart-modal__item-title");
    private final By modalCartItems = By.className("cart-modal__item");
    private final By emptyCartMessage = By.id("empty-cart-message");
    private final By closeCartBtn = By.id("close-cart");
    private final By modalCartQuantityFieldV3 = By.className("cart-modal__qty-input");
    private final By cartSubtotal = By.id("cart-total");



    public String getFirstItemName() {
        return driver.findElement(cartItemName).getText();
    }

    public WebElement isCartEmpty(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(emptyCartMessage));
    }

    public void closeCartButton(){
        driver.findElement(closeCartBtn).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalContainer));
    }

    public void clearEntireCartSilently() {
        wait.until(ExpectedConditions.elementToBeClickable(cartCountBadge)).click();
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

    public void increaseQuantityInCart(String bookTitle, String amount) throws InterruptedException {
        List<WebElement> books = driver.findElements(modalCartItems);

        By cartQuantityField = null;
        By cartQuantityFieldV2 = null;
        By quantityField = null;
        char bookArticleID = 'k';


        for(WebElement book : books){
            String currentTitle = wait.until(ExpectedConditions.presenceOfElementLocated(modalCartBookTitle)).getText();
            //System.out.println(currentTitle);
            if(currentTitle.equalsIgnoreCase(bookTitle)){
                System.out.println("Book title exists!");

                String bookId = book.getAttribute("data-book-id");

                String bookIds = book.getAttribute("id");
                String itemID[] = bookIds.split("-");

                System.out.println(itemID[2]);

                char id = bookIds.charAt(bookIds.length()-1);
                bookArticleID = id;

                cartQuantityField = By.id("cart-qty-"+ id);
                cartQuantityFieldV2 = By.cssSelector("input#cart-qty-"+id);
                quantityField = By.cssSelector("[data-testid='cart-quantity-input-" + id + "']");


            }



        }

        String cssSelector = "[data-testid='cart-quantity-input-" + bookArticleID + "']";
        By qntField = By.cssSelector(cssSelector);

        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityField)).click();

        driver.findElement(qntField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(qntField).sendKeys(amount);

        By qtyField = By.cssSelector(cssSelector);





    }

    public boolean removeBookFromCart(String bookTitle){
        List<WebElement> books = driver.findElements(modalCartItems);

        By cartRemoveItemBtn = null;
        By cartItemId = null;
        for(WebElement book : books){
            String currentTitle = wait.until(ExpectedConditions.presenceOfElementLocated(modalCartBookTitle)).getText();
            //System.out.println(currentTitle);
            if(currentTitle.equalsIgnoreCase(bookTitle)){
                System.out.println("Book title exists!");

                String bookId = book.getAttribute("data-book-id");
                String bookIds = book.getAttribute("id");
                String itemID[] = bookIds.split("-");

                System.out.println(itemID[2]);

                char id = bookIds.charAt(bookIds.length()-1);

                cartRemoveItemBtn = By.id("remove-item-" + id);
                cartItemId = By.id("cart-item-" + id);

            }

        }
        wait.until(ExpectedConditions.elementToBeClickable(cartRemoveItemBtn)).click();
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(cartItemId));



    }

    public String getSubTotal(String expectedSubtotal) throws InterruptedException {


        driver.findElement(cartModalHeader).click();

        // 🛡️ STEP 2: Use your target-driven wait strategy.
        // Selenium will now comfortably block execution until the UI completes the recalculation.
        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartSubtotal, expectedSubtotal));

        return driver.findElement(cartSubtotal).getText().trim();
    }



    public String checkBookInCart(String bookTitle){
        return wait.until(ExpectedConditions.presenceOfElementLocated(modalCartBookTitle)).getText();


    }





}
