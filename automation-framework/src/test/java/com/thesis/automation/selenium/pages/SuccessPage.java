package com.thesis.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SuccessPage extends BasePage{

    public SuccessPage() {
        super(); // Automatically initialize
}

//Selectors
    By successMsg = By.id("order-success-message");


    public WebElement getSuccessMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
    }

}