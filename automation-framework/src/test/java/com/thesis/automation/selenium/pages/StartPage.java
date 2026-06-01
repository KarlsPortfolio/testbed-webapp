package com.thesis.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

// 💡 Inheriting from BasePage
    public class StartPage extends BasePage {

        //private final By logoutButton = By.id("logout-btn");
        private final By loginSuccessMsg = By.id("header-user-name");
        private final By storeTabActive = By.cssSelector("#store-tab.header__nav-btn--active");
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





        public StartPage() {
            super(10); // Automatically initializes the driver and WebDriverWait from the parent
        }

        public void addToCart(String bookTitle){
            List<WebElement> books = driver.findElements(bookTitles);

            By addToCartButton = null;
            for(WebElement book : books){

                if(book.getText().equalsIgnoreCase(bookTitle)){
                    System.out.println("Book title exists!");

                    String bookId = book.getAttribute("data-book-id");

                    addToCartButton = By.id("add-to-cart-" + bookId);

                }

            }

          wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

        }
        public void clickLogout() {
            // Use the inherited 'wait' engine natively to guard the action
            wait.until(ExpectedConditions.elementToBeClickable(navLogoutButton)).click();
        }

        public String getCartCount(){
            return wait.until(ExpectedConditions.presenceOfElementLocated(cartCountBadge)).getText();
        }

        public String checkBookInCart(String bookTitle){
            wait.until(ExpectedConditions.elementToBeClickable(cartCountBadge)).click();
            return wait.until(ExpectedConditions.presenceOfElementLocated(modalCartBookTitle)).getText();
        }

        public Boolean removeBookFromCart(String bookTitle){
            List<WebElement> books = driver.findElements(modalCartItems);

            By cartRemoveItemBtn = null;
            By cartItemId = null;
            for(WebElement book : books){
                String currentTitle = wait.until(ExpectedConditions.presenceOfElementLocated(modalCartBookTitle)).getText();

                if(currentTitle.equalsIgnoreCase(bookTitle)){
                    System.out.println("Book title exists!");

                    String bookId = book.getAttribute("id");
                    String itemID[] = bookId.split("-");

                    System.out.println(itemID[2]);

                    char id = bookId.charAt(bookId.length()-1);

                   cartRemoveItemBtn = By.id("remove-item-" + id);
                   cartItemId = By.id("cart-item-" + id);

                }

            }

            wait.until(ExpectedConditions.elementToBeClickable(cartRemoveItemBtn)).click();
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(cartItemId));

        }

        public String increaseQuantityInCart(String bookTitle, String amount)  {
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


                    String bookId = book.getAttribute("id");

                    char id = bookId.charAt(bookId.length()-1);

                    cartQuantityField = By.id("cart-qty-"+ id);


                }


            }


            wait.until(ExpectedConditions.visibilityOfElementLocated(cartQuantityField)).click();

            driver.findElement(cartQuantityField).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            driver.findElement(cartQuantityField).sendKeys(amount);


            wait.until(ExpectedConditions.elementToBeClickable(closeCartBtn)).click();

            return getCartCount();

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

    public void closeCartButton(){
        driver.findElement(closeCartBtn).click();
    }


        public WebElement presenceOfLogoutBtn() {

                return wait.until(ExpectedConditions.elementToBeClickable(navLogoutButton));

        }

        public Boolean invisibilityOfLogoutBtn() {

            return wait.until(ExpectedConditions.invisibilityOfElementLocated(navLogoutButton));

        }

        public WebElement isCartEmpty(){
            return wait.until(ExpectedConditions.presenceOfElementLocated(emptyCartMessage));
        }

        public WebElement presenceOfLoginBtn() {
            // Use the inherited 'wait' engine natively to guard the action
            return wait.until(ExpectedConditions.elementToBeClickable(navLoginButton));
        }

        public String getLoginGreetMsg() {
            return wait.until(ExpectedConditions.presenceOfElementLocated(loginSuccessMsg)).getText();
        }

        public WebElement isStoreActive() {
            // Use the inherited 'wait' engine natively to guard the action
            return wait.until(ExpectedConditions.presenceOfElementLocated(storeTabActive));
        }




    }

