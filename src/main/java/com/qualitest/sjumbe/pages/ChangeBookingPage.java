package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ChangeBookingPage extends BasePageObject {
    //Attributes
    private final By arrivalDateLocator = By.xpath(("//input[@title='Arrival']"));
    private final By numberOfNightsLocator = By.xpath("//input[@title='Nights']");
    private final By numberOfAdultsLocator = By.xpath("//input[@title='Adults']");
    private final By searchForAvailableRoomsButton = By.xpath("//input[@value='Search for available rooms']");
    private String dateIteratedAsString;

    public ChangeBookingPage(WebDriver driver, Logger log) {
        super(driver, log);
    }


    public void changeArrivalDate(String date) {

        //Entering incremented date
        click(arrivalDateLocator);
        log.info("entering date " + date);
        clearValues(arrivalDateLocator);
        type(date, arrivalDateLocator);


        log.info("clicking Search For Available Rooms Button");
        click(searchForAvailableRoomsButton);
    }


    /*
    Increments Arrival date when Deluxe room is not available on original date
     */
    public String incrementArrivalDate() {

        //Getting current Arrival date
        String currentDate = getAttribute(arrivalDateLocator, "value");
        System.out.println("***CURRENT DAY FROM LOCATOR: " + currentDate);
        String[] currentDateArray = currentDate.split(" ", 3);

        String monthAsString = currentDateArray[1];
        int year = Integer.parseInt(currentDateArray[2]);
        int day = Integer.parseInt(currentDateArray[0]);

        Map<String, Integer> monthToIntMap = new HashMap<String, Integer>() {{
            put("Jan", 1);
            put("Feb", 2);
            put("Mar", 3);
            put("Apr", 4);
            put("May", 5);
            put("Jun", 6);
            put("Jul", 7);
            put("Aug", 8);
            put("Sep", 9);
            put("Oct", 10);
            put("Nov", 11);
            put("Dec", 12);
        }};

        int month = monthToIntMap.get(monthAsString);

        LocalDate dateToIterate = LocalDate.of(year, month, day);
        LocalDate dateIterated = dateToIterate.plusDays(1l);//incrementing by 1 day
        String dateIteratedAsString = formatDateAsddMMMyyyy(dateIterated);
        changeArrivalDate(dateIteratedAsString);

        return dateIteratedAsString;


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

    public String getDateIteratedAsString() {
        return dateIteratedAsString;
    }
}


