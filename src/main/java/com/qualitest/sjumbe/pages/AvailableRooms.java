package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AvailableRooms extends BasePageObject {

    //Attributes

    private final By frame = By.id("clock_pms_iframe_1");
    private final By mostExpensiveRoom = By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div[2]/table/tbody/tr[4]/td[3]/span/a");


    public AvailableRooms(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void selectDeluxeAppartment() {
        switchToFrame(frame);
        log.info("switched to iframe");
        waitForVisibilityOf(mostExpensiveRoom, 2);
        log.info("waited for 2 seconds");
        click(mostExpensiveRoom);
        log.info("clicked most expensive room");

    }


}
