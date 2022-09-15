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
    private final By backButtonLocator = By.xpath("/html/body/div[1]/div[1]/div[1]/a");

    private final By availableRoomContainerLocator = By.xpath("//div[@class='bookable-container bookable-location-3864']");
    private final By selectRoomButtonLocator = By.xpath("//a[@class='btn btn-success '] ");
    private final By deluxeRoomLocator = By.xpath("//*[@id='bookable_container_15343']");

    public AvailableRooms(WebDriver driver, Logger log) {
        super(driver, log);
    }


    public void checkAvailableRooms() {
        switchToFrame(frame);
        //finding all room webelements


        Boolean deluxeIsPresent = false;
        do {
            log.info("Getting all available rooms");

            //A list containing all the room containers on the page (e.g Deluxe room container, contains the title + attributes of available rooms)
            List<WebElement> roomContainersList = findAll(availableRoomContainerLocator);

            //printing available rooms to log
            for (int i = 0; i < roomContainersList.size(); i++) {
                //Boolean to be used as condition for if and while loop
                String roomName = roomContainersList.get(i).getAttribute("innerText");
                Boolean deluxeIsFound = roomName.contains("Deluxe");

                if (roomName.contains("Deluxe")) {
                    deluxeIsPresent = true;//needed to exit while loop
                    log.info("Delux room is present is " + deluxeIsPresent);
                    log.info("this room is available: "
                            + "\n*******************************" + "\n"
                            + roomContainersList.get(i).getAttribute("innerText")
                            + "\n*******************************");

                } else if (i == roomContainersList.size() - 1 && roomName.contains("Deluxe") == false) {
                    log.info("Deluxe rooms unavailable automatically changing booking date by 1 day");
                    log.info("Deluxe room is present is " + deluxeIsFound);

                    //going to ChangeBookingPage and incrementing booked date by 1
                    click(backButtonLocator);
                    ChangeBookingPage changeBookingPage = new ChangeBookingPage(driver, log);
                    changeBookingPage.incrementArrivalDate();
                    roomContainersList = findAll(availableRoomContainerLocator);//repeated to avoid staleElement exception

                }
            }
        } while (deluxeIsPresent == false);// date will be incremented until deluxe is found in the innerText Attribute

    }


    public void selectDeluxeApartment() {
        List<WebElement> selectRoomButtonsList = findAll(selectRoomButtonLocator);
        WebElement mostExpensiveDeluxeRoom = selectRoomButtonsList.get(selectRoomButtonsList.size() - 1);
        //checking if deluxe room option is available
        mostExpensiveDeluxeRoom.click();
        log.info("clicked most expensive room");
    }

    public void goBackToChangeBookingPage() {
        click(backButtonLocator);
    }


}
