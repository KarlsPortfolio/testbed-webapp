package com.thesis.automation.selenium.pages;

import com.thesis.automation.selenium.models.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage{


    public CheckoutPage() {

        super(); // 🎯 Automatically gets a clean, isolated 5-second wait
    }


    // Locators
    private final By fullNameField = By.id("full-name");
    private final By emailField = By.id("email");
    private final By addressField = By.id("address");
    private final By cityField = By.id("city");
    private final By zipCodeField = By.id("zip-code");
    private final By creditCardField = By.id("credit-card");
    private final By cvvField = By.id("cvv");
    private final By submitBtn = By.id("place-order-button");

    public void fillCheckoutFieldsCustomer(Customer customer, Boolean forceBlurClick){
        wait.until(ExpectedConditions.elementToBeClickable(fullNameField)).sendKeys(customer.getFullName());
        driver.findElement(emailField).sendKeys(customer.getEmail());
        driver.findElement(addressField).sendKeys(customer.getAddress());
        driver.findElement(cityField).sendKeys(customer.getCity());
        driver.findElement(zipCodeField).sendKeys(customer.getZipCode());
        driver.findElement(creditCardField).sendKeys(customer.getCreditCard());
        driver.findElement(cvvField).sendKeys(customer.getCvv());

        //  Force focus out of the final field using a keyboard tab
        driver.findElement(cvvField).sendKeys(Keys.TAB);

        if(forceBlurClick){
            driver.findElement(submitBtn).click();
        }


    }

    public SuccessPage clickSubmitButton(){
        driver.findElement(submitBtn).click();

        return new SuccessPage();
    }

    public boolean isSubmitBtnActive(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(submitBtn)).isEnabled();

    }

    public String getErrorMessage(String missingField){
        String dynamicId = missingField+"-error";
        By errorMessage = By.id(dynamicId);
        return wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage)).getText();

    }

}
