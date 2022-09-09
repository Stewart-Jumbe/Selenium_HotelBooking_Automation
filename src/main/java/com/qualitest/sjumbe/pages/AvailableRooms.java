package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AvailableRooms extends BasePageObject {

    //Attributes

    private final By frame = By.id("clock_pms_iframe_1");
    private final By mostExpensiveRoom = By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div[2]/table/tbody/tr[4]/td[3]/span/a");
    private final By deluxeRoomLocator = By.xpath("//*[@id='bookable_container_15343']");
    private final By backButtonLocator = By.xpath("/html/body/div[1]/div[1]/div[1]/a");
    private final By availableRoomsLocator = By.xpath("//div[@class='bookable-container bookable-location-3864']");
    private final By availableRoomTypes = By.xpath("/html/body/div[1]/div[5]/div[1]/div[1]/div/h2/text()");

    public AvailableRooms(WebDriver driver, Logger log) {
        super(driver, log);
    }


    public void checkAvailableRooms() {
        switchToFrame(frame);
        //finding all room webelements
        List<WebElement> rooms = findAll(availableRoomsLocator);

        //printing available rooms to log
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getAttribute("innerText").contains("Deluxe")) {
                log.info("this room is available: " + "\n*******************************" + "\n" + rooms.get(i).getAttribute("innerText") + "\n*******************************");

            } else if (i == rooms.size() - 1 && rooms.get(i).getAttribute("innerText").contains("Deluxe") == false) {
                log.info("Deluxe rooms unavailble please change booking date");
                click(backButtonLocator);
            }

        }
        driver.switchTo().defaultContent();

    }

    public void selectDeluxeAppartment() {
        switchToFrame(frame);
        log.info("switched to iframe");

        //checking if deluxe room option is available
        if (driver.findElement(deluxeRoomLocator).isDisplayed()) {
            waitForVisibilityOf(mostExpensiveRoom, 2);
            log.info("waited for 2 seconds");
            click(mostExpensiveRoom);
            log.info("clicked most expensive room");
        } else {
            log.info("Deluxe Room unavailable please change arrival data");
            click(backButtonLocator);
        }
    }


}
