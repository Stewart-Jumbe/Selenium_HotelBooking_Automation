package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChangeBookingPage extends BasePageObject {
    //Attributes
    private final By arrivalDateLocator = By.xpath("/html/body/div[1]/div[5]/div/form/div[1]/div[1]/div/input");
    private final By numberOfNightsLocator = By.xpath("/html/body/div[1]/div[5]/div/form/div[1]/div[2]/div/input");
    private final By numberOfAdultsLocator = By.xpath("/html/body/div[1]/div[5]/div/form/div[2]/div/input");
    private final By seachForAvailableRoomsButton = By.xpath("/html/body/div[1]/div[5]/div/form/div[6]/div/input");

    public ChangeBookingPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void incrementArrivalDate() {
        //Getting current Arrival date
        String currentDate = getText(arrivalDateLocator);

        //incrementing date by 1;
        int currentDateAsInt = Integer.parseInt(currentDate.substring(0, 2));
        String stringPartCurrentDate = currentDate.substring(2);
        int incrementedDate = ++currentDateAsInt;
        String incrementedDateAsString = String.valueOf(incrementedDate);

        //Entering incremented date
        click(arrivalDateLocator);
        log.info("entering date " + incrementedDateAsString + stringPartCurrentDate);
        type(incrementedDateAsString + stringPartCurrentDate, arrivalDateLocator);

        //Searching for availble rooms again
        log.info("clicking Search For Available Rooms Button");
        click(seachForAvailableRoomsButton);

    }


    public void updateNumberOfNights(int num_of_nights) {

        //clearing values in the box
        clearValues(numberOfNightsLocator);

        log.info("enter Nights as an int, e.g 1");

        log.info("entering number of nights as " + num_of_nights);
        click(numberOfNightsLocator);
        typeNum((double) num_of_nights, numberOfNightsLocator);

    }


    public void enterNumberOfAdults(int num_of_adults) {
        //clearing values in the box
        clearValues(numberOfAdultsLocator);

        log.info("enter Nights as an int, e.g 1");

        log.info("entering number of nights as " + num_of_adults);
        click(numberOfAdultsLocator);
        typeNum((double) num_of_adults, numberOfAdultsLocator);

    }
}


