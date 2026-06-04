package com.thesis.automation.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Page;


public class CartModal extends BasePage {

    //  LOCATORS
    private final Locator modalContainer;
    private final Locator modalHeader;
    private final Locator cartSubtotal;
    private final Locator closeCartBtn;
    private final Locator emptyCartMessage;

    public CartModal() {
        super(); // Initializes the shared parent context

        // Locate overall structural overlay containers
        this.modalContainer = page.locator("#cart-modal");
        this.modalHeader = page.locator(".cart-modal__header");
        this.cartSubtotal = page.locator("#cart-total");

        // Locate interactive action handlers
        this.closeCartBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close cart modal"));
        this.emptyCartMessage = page.locator("#empty-cart-message");
    }

    public Locator getEmptyCartMessage() {
        return emptyCartMessage;
    }



    public void closeCartButton() {
        closeCartBtn.click();

    }

    public Locator getModalContainer() {
        return modalContainer;
    }


    public void clearEntireCartSilently() {
        Locator removeButtons = page.locator("button[id^='remove-item-']");
        while (removeButtons.count() > 0) {
            removeButtons.first().click();
        }
    }

    /**
     * Target the list row containing your book title text node,
     * find the inner input field, and update it.
     */
    public void increaseQuantityInCart(String bookTitle, String amount) {
        Locator targetRow = page.locator(".cart-modal__item")
                .filter(new Locator.FilterOptions().setHasText(bookTitle));

        // Locate the spin box/quantity field inside that row context
        Locator qtyField = targetRow.locator("input[id^='cart-qty-']");

        // .fill() auto-focuses, clears out the old quantity, types the new amount, and blurs
        qtyField.fill(amount);
    }


    public void removeBookFromCart(String bookTitle) {
        Locator targetRow = page.locator(".cart-modal__item")
                .filter(new Locator.FilterOptions().setHasText(bookTitle));

        // Usage of  Locator Chaining natively
        targetRow.locator("button[id^='remove-item-']").click();
    }

    public Locator getBookRowInCart(String bookTitle) {
        return page.locator(".cart-modal__item")
                .filter(new Locator.FilterOptions().setHasText(bookTitle));
    }

    public void triggerSubtotalRecalculation() {
        modalHeader.click();
    }

    public Locator getSubtotalLocator() {
        return cartSubtotal;
    }

    public Locator checkBookInCart(String bookTitle) {
        // Targets the item label tracking that title string inside the cart modal list grid container view context
        return page.locator(".cart-modal__item-title")
                .filter(new Locator.FilterOptions().setHasText(bookTitle));

    }
}