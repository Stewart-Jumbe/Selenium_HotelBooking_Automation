package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public class BookingHomePage extends BasePageObject {

    //Attributes

    private final By arrivalDateBox = By.id("date-start");
    private final By numberOfNightsBox = By.id("to-place");
    private final By numberOfAdultsBox = By.xpath("//input[@name='wbe_product[adult_count]']");
    private final By numberOfChildrenBox = By.xpath("//input[@name='wbe_product[children_count]']");
    private final By bookNowbutton = By.xpath("//input[@value='Book now !']");
    String Url = "https://www.clock-software.com/demo-clockpms/index.html";

    //Constructor matching parent
    public BookingHomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage() {
        log.info("Opening clock-software booking page");
        openUrl("https://www.clock-software.com/demo-clockpms/index.html");
        log.info("Page opened :-)");
    }

    public void enterArrivalDate() {

        LocalDate today = LocalDate.now();// arrival date is whatever the current date is

        clearValues(arrivalDateBox);
        log.info("entering date " + today);
        click(arrivalDateBox);

        type(formatDateAsddMMyyyy(today), arrivalDateBox);
    }

    public void enterNumberOfNights(int num_of_nights) {

        //clearing values in the box
        clearValues(numberOfNightsBox);

        log.info("enter Nights as an int, e.g 1");

        log.info("entering number of nights as " + num_of_nights);
        click(numberOfNightsBox);
        typeNum((double) num_of_nights, numberOfNightsBox);

    }


    public void enterNumberOfAdults(int num_of_adults) {
        //clearing values in the box
        clearValues(numberOfAdultsBox);

        log.info("enter Nights as an int, e.g 1");

        log.info("entering number of nights as " + num_of_adults);
        click(numberOfAdultsBox);
        typeNum((double) num_of_adults, numberOfAdultsBox);

    }

    public void bookNow() {
        click(bookNowbutton);
    }
}
