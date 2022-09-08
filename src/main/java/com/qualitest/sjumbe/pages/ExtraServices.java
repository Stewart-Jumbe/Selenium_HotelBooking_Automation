package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExtraServices extends BasePageObject {

    //Attributes

    private final By airportTransferLocatorFull = By.xpath("/html/body/div/form/div[2]/div/div/div[2]/div[1]/div[3]/input");
    private final By businessServiceLocator = By.xpath("/html/body/div[1]/form/div[3]/div/div/div[2]/div[1]/div[3]/input");
    private final By dryCleaningLocator = By.xpath("/html/body/div[1]/form/div[4]/div/div/div[2]/div[1]/div[3]/input");
    private final By fitnessLocator = By.xpath("/html/body/div[1]/form/div[5]/div/div/div[2]/div[1]/div[3]/input");
    private final By noThanks = By.xpath("//*[@id=\"new_form\"]/div[6]/div/span/a");
    private final By addSelectedServicesLocator = By.xpath("//input[@type='submit']");
    private final By frame = By.xpath("//*[@id='clock_pms_iframe_1']");

    public ExtraServices(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void addAirportTransfer(int number) {

        waitForVisibilityOf(airportTransferLocatorFull, 2);
        click(airportTransferLocatorFull);
        typeNum((double) number, airportTransferLocatorFull);
        log.info("Added " + number + " " + "Airport Transfer(s)");

    }

    public void addBusinessServices(int number) {


        waitForVisibilityOf(businessServiceLocator, 2);
        click(businessServiceLocator);
        typeNum((double) number, businessServiceLocator);
        log.info("Added " + number + " " + "Business service(s)");

    }

    public void addSelectedServices() {
        click(addSelectedServicesLocator);
        log.info("Clicked Add The Selected Services");
        driver.switchTo().defaultContent();//getting out of the iframe
    }


}
