package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChangeBookingPage extends BasePageObject {
    //Attributes
    private final By arrivalDateLocator = By.xpath("/html/body/div[1]/div[4]/div/form/div[1]/div[1]/div/input");

    private final By numberOfNightsLocator = By.xpath("/html/body/div[1]/div[4]/div/form/div[1]/div[2]/div/input");
    private final By numberOfAdultsLocator = By.xpath("/html/body/div[1]/div[4]/div/form/div[2]/div/input");
    private final By searchForAvailableRoomsButton = By.xpath("/html/body/div[1]/div[4]/div/form/div[6]/div/input");
    private final By frame = By.xpath("/html/body/div/div/div[3]/div/div/div/div/iframe");


    public ChangeBookingPage(WebDriver driver, Logger log) {
        super(driver, log);
    }


    public void changeArrivalDate() {

        click(arrivalDateLocator);
        type("13 Sep 2022", arrivalDateLocator);
    }

    /*
    Increments Arrival date when Deluxe room is not available on original date
     */
    public void incrementArrivalDate() {


        //Getting current Arrival date
        String currentDate = getAttribute(arrivalDateLocator, "value");

        //incrementing date by 1;

        int currentDateAsInt;
        String stringPartCurrentDate;
        if (currentDate.length() == 10) {
            currentDateAsInt = Integer.parseInt(currentDate.substring(0, 1));
            stringPartCurrentDate = currentDate.substring(1);

            int incrementedDate = ++currentDateAsInt;
            String incrementedDateAsString = String.valueOf(incrementedDate);
            String newDate = incrementedDateAsString + stringPartCurrentDate;

            //Entering incremented date
            click(arrivalDateLocator);
            log.info("entering date " + newDate);
            clearValues(arrivalDateLocator);
            type(newDate, arrivalDateLocator);


        } else {
            currentDateAsInt = Integer.parseInt(currentDate.substring(0, 2));
            stringPartCurrentDate = currentDate.substring(2);

            int incrementedDate = ++currentDateAsInt;
            String incrementedDateAsString = String.valueOf(incrementedDate);
            String newDate = incrementedDateAsString + stringPartCurrentDate;

            //Entering incremented date
            click(arrivalDateLocator);
            log.info("entering date " + newDate);
            clearValues(arrivalDateLocator);
            type(newDate, arrivalDateLocator);


        }
        log.info("clicking Search For Available Rooms Button");
        click(searchForAvailableRoomsButton);


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


