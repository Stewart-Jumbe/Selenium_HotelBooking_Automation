package com.qualitest.sjumbe.pages;

import com.qualitest.sjumbe.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingHomePage extends BasePageObject {

    //Attributes

    private By arrivalDateBox = By.id("date-start");
    private By numberOfNightsBox= By.id("to-place");
    private By numberOfAdultsBox = By.xpath("//input[@name='wbe_product[adult_count]']");
    private By numberOfChildrenBox = By.xpath("//input[@name='wbe_product[children_count]']");

    String Url = "https://www.clock-software.com/demo-clockpms/index.html";

    private By bookNowbutton = By.xpath("//input[@value='Book now !']");

    public void openPage(){
        log.info("Opening clock-software booking page");
        openUrl( "https://www.clock-software.com/demo-clockpms/index.html");
        log.info("Page opened :-)");
    }


    //Constructor matching parent
    public BookingHomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void enterArrivalDate (String ddmmyyyy){
        log.info("enter date in format dd-mm-yyyy, e.g 01-02-2022");

        //clearing values in the box
       clearValues(arrivalDateBox);

        if(ddmmyyyy.length() ==10){
            log.info("entering date "+ddmmyyyy);
            click(arrivalDateBox);
            type(ddmmyyyy,arrivalDateBox);
        }
        log.info("Date format should be dd-mm-yyyy, e.g 01-02-2022");

    }

    public void enterNumberOfNights (int num_of_nights){

        //clearing values in the box
        clearValues(numberOfNightsBox);

        log.info("enter Nights as an int, e.g 1");

            log.info("entering number of nights as "+num_of_nights);
            click(numberOfNightsBox);
            typeNum(num_of_nights,numberOfNightsBox);

    }


    public void enterNumberOfAdults (int num_of_adults){
        //clearing values in the box
        clearValues(numberOfAdultsBox);

        log.info("enter Nights as an int, e.g 1");

        log.info("entering number of nights as "+num_of_adults);
        click(numberOfAdultsBox);
        typeNum(num_of_adults,numberOfAdultsBox);

    }

    public void bookNow(){
        click(bookNowbutton);
    }
}
