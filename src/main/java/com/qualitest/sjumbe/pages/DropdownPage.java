package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


//Constructor
public class DropdownPage extends BasePageObject {

    //Attributes
    private final By dropdownLocator = By.id("dropdown");

    public DropdownPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //Methods

    /**
     * Selects given option from dropdown
     */
    public void selectOption(int optionNum) {

        log.info("Selecting option " + optionNum + " from dropdown");
        WebElement dropdownElement = find(dropdownLocator);
        Select dropdown = new Select(dropdownElement);//We can use the select class because the dropdowns are in the select tag

        //There are three ways to use Select class
        // #1
        //dropdown.selectByIndex(optionNum)

        // #2, "" is added to efffectively convert the int into a string
        dropdown.selectByValue("" + optionNum);

        // #3
        //dropdown.selectByVisibleText("Option " + optionNum)

    }

    /**
     * This method returns selected option in dropdown as a string
     */
    public String getSelectedOption() {
        WebElement dropdownElement = find(dropdownLocator);
        Select dropdownLocator = new Select(dropdownElement);
        String selectedOption = dropdownLocator.getFirstSelectedOption().getText();
        log.info(selectedOption + " is selected in dropdown");
        return selectedOption;

    }


}
