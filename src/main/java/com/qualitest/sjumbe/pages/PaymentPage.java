package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePageObject {


    //Attributes
    private final By creditCardNumberLocator = By.xpath("//*[@id='cardNumber']");
    private final By brandDropdownLocator = By.xpath("/html/body/div[1]/div/div/div/form/div[2]/div/select");
    private final By expiryMonthDropdownLocator = By.xpath("/html/body/div[1]/div/div/div/form/div[4]/div[1]/select");
    private final By expiryYearDropdownLocator = By.xpath("/html/body/div[1]/div/div/div/form/div[4]/div[2]/select");
    private final By countryDropdownLocator = By.xpath("/html/body/div[1]/div/div/div/form/div[9]/div/select");
    private final By billingAddressLocator = By.xpath("/html/body/div[1]/div/div/div/form/div[5]/div/input");
    private final By zipCodeLocator = By.xpath("/html/body/div[1]/div/div/div/form/div[6]/div/input");
    private final By stateLocator = By.xpath("/html/body/div[1]/div/div/div/form/div[8]/div/input");
    private final By cityLocator = By.xpath("/html/body/div[1]/div/div/div/form/div[7]/div/input");

    private final By payButtonLocator = By.xpath("/html/body/div[1]/div/div/div/form/div[10]/div/button");

    public PaymentPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void enterCardNumber(String cardNumber) {

        click(creditCardNumberLocator);
        log.info("Card number length is " + String.valueOf(cardNumber).length());
        if (String.valueOf(cardNumber).length() == 16) {
            log.info("Entering credit card number " + cardNumber);
            type(String.valueOf(cardNumber), creditCardNumberLocator);
        }
    }

    public void selectCreditCardBrand(String brandName) {

        //TODO add switch statement to handle different card brand names
        selectOption(brandName, brandDropdownLocator);

    }

    public void selectCardExpiryMonthAndYear(int mm, int yyyy) {
        if (mm >= 0 && mm <= 12) {
            selectOption(String.valueOf(mm), expiryMonthDropdownLocator);
        } else {
            log.info("number need be to be between 1 and 12");
        }

        selectOption(String.valueOf(yyyy), expiryYearDropdownLocator);
    }

    public void selectCountry(String countryCode) {
        selectOption(countryCode.toUpperCase(), countryDropdownLocator);
    }

    public void enterBillingAddress(String address, String zipCode, String city, String state, String countryCode) {

        click(billingAddressLocator);
        type(address, billingAddressLocator);
        log.info("billing address " + address + " entered");

        click(zipCodeLocator);
        type(zipCode, zipCodeLocator);
        log.info("Zip code " + zipCode + " entered");

        click(cityLocator);
        type(city, cityLocator);
        log.info("City " + city + " entered");

        click(stateLocator);
        type(state, stateLocator);
        log.info("state " + state + " entered");

        selectCountry(countryCode);
    }

    public void payDeposit() {
        log.info("clicking pay button");
        click(payButtonLocator);
    }


}
