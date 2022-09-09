package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingReviewPage extends BasePageObject {

    //Attributes
    private final By removeRoomLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/a");
    private final By addMoreRoomsLocator = By.xpath("//*[contains(text(),'Add more rooms')]");
    private final By previousCustomerLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/a");
    private final By previousPageButtonLocator = By.xpath("/html/body/div[1]/div[1]/div[1]/a");

    //Contact info locators
    private final By emailAddressLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[2]/div[2]/div[1]/div[1]/div/input");
    private final By lastNameLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[2]/div[2]/div[1]/div[2]/div/input");
    private final By firstNameLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[2]/div[2]/div[1]/div[3]/div/input");

    private final By phoneNumberLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[2]/div[2]/div[1]/div[4]/div/input");
    private final By bookingForSomeoneElseLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[2]/div[2]/div[3]/div/div/label/input[2]");

    //Payment Method
    private final By creditCardButtonLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[4]/div[2]/div/div[3]/label/input");

    //Finalise box locators
    private final By notesLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[5]/div[2]/div[1]/div/textarea");
    private final By bookingAgreedLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[5]/div[2]/div[2]/div/div/label/input[2]");
    private final By createBookingLocator = By.xpath("/html/body/div[1]/div[3]/div[2]/form/div[5]/div[2]/div[3]/input");

    //iframe locator
    private final By iframeLocator = By.xpath("/html/body/div/div/div[3]/div/div/div/div/iframe");

    //booked room details
    private final By roomDetailsLocator = By.xpath("//div[@class='col-sm-5']");

    public BookingReviewPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void addContactInfo(String firstName, String lastName, String emailAddress, String phoneNumber, String ownBooking) {

        switchToFrame(iframeLocator);
        click(emailAddressLocator);
        type(emailAddress, emailAddressLocator);
        log.info("entered email");

        click(firstNameLocator);
        type(firstName, firstNameLocator);

        click(lastNameLocator);
        type(lastName, lastNameLocator);

        click(phoneNumberLocator);
        type(phoneNumber, phoneNumberLocator);

        String firstLetter = ownBooking.toLowerCase().substring(0, 1);

        if (firstLetter == "n") {
            click(bookingForSomeoneElseLocator);
        }
    }

    public void selectCreditCard() {
        click(creditCardButtonLocator);
    }

    public void agreeWithHotelPolicy() {
        click(bookingAgreedLocator);
    }

    public void createBooking() {
        click(createBookingLocator);
    }


}
