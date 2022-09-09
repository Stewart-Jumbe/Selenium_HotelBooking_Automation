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
    private final By arrivalDateTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[1]/div[2]");
    private final By stayLengthTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[2]/div[2]");
    private final By departureTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[3]/div[2]");
    private final By numberOfAdultsTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[4]/div[2]");
    private final By roomTypeTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[5]/div[2]");
    private final By rateTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[6]/div[2]/text()");
    private final By guaranteeOptionTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[7]/div[2]");
    private final By roomPriceTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[8]/div[2]");
    private final By extraServicesTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[9]/div[2]");
    private final By vatTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[10]/div[2]");
    private final By totalCostTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[11]/div[2]/h3");
    private final By requiredDepositTextLocator = By.xpath("/html/body/div[1]/div[3]/div[3]/div[1]/div[2]/div[12]/div[2]");

    public BookingReviewPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public String getArrivalDateTextLocator() {
        return getAttribute(arrivalDateTextLocator, "innerText");
    }

    public String getStayLengthTextLocator() {
        return getAttribute(stayLengthTextLocator, "innerText");
    }

    public String getDepartureTextLocator() {
        return getAttribute(departureTextLocator, "innerText");
    }

    public String getNumberOfAdultsTextLocator() {
        return getAttribute(numberOfAdultsTextLocator, "innerText");
    }

    public String getRoomTypeTextLocator() {
        return getAttribute(roomTypeTextLocator, "innerText");
    }

    public String getRateTextLocator() {
        return getAttribute(rateTextLocator, "innerText");
    }

    public String getGuaranteeOptionTextLocator() {
        return getAttribute(guaranteeOptionTextLocator, "innerText");
    }

    public String getRoomPriceTextLocator() {
        return getAttribute(roomPriceTextLocator, "innerText");
    }

    public String getExtraServicesTextLocator() {
        return getAttribute(extraServicesTextLocator, "innerText");
    }

    public String getVatTextLocator() {
        return getAttribute(vatTextLocator, "innerText");
    }

    public String getTotalCostTextLocator() {
        return getAttribute(totalCostTextLocator, "innerText");
    }

    public String getRequiredDepositTextLocator() {
        return getAttribute(requiredDepositTextLocator, "innerText");
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
