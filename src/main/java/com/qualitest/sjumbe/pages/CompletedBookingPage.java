package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletedBookingPage extends BasePageObject {

    //Attributes
    private final By thankYouMessageLocator = By.xpath("/html/body/div[1]/div[2]/h1");
    private final By checkYourEmailMessageLocator = By.xpath("/html/body/div[1]/div[2]/h3[1]");


    public CompletedBookingPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public String getThankYouBookingText() {
        return getAttribute(thankYouMessageLocator, "innerText");
    }

    public String getCheckYourEmailMessage() {
        return getAttribute(checkYourEmailMessageLocator, "innerText");
    }
}
