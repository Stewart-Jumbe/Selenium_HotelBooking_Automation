package com.qualitest.sjumbe.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v85.systeminfo.model.VideoDecodeAcceleratorCapability;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BasePageObject {
    /**
     * this page contains the methods that call on the Webdriver class
     */

    //Attributes
    protected WebDriver driver;
    protected Logger log;


    //Constructor
    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Open page with given URL
     */
    protected void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Find Element using given locator
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Click on element with given locator when its visible
     */
    protected void click(By locator) {
        waitForVisibilityOf(locator, 1);
        find(locator).click();
    }

    /**
     * Type given text into element with given locator
     */
    protected void type(String text, By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).sendKeys(text);
    }

    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        //timeOutInSeconds is optional
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    /**
     * Wait for a specific ExpectedContidion for the given amount of time in seconds
     */
    //explicit wait
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        //timeOutInSeconds can be null, i.e we dont have to provide an input
        //if timeOutInSeconds is null then it will be assigned 30 seconds by default
        //condition will be the visibilityOfElementLocated
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(condition);
    }


    //read text method,
    //Methods that will also be used in our tests are public so that the test classes can access them

    /**
     * Read text given a locator
     */
    public String getText(By locator) {
        return find(locator).getText();
    }

    /**
     * Get URL of current page from browser
     */
    public String getCurrrentUrl() {
        return driver.getCurrentUrl();

    }

    /**
     * Find a all elements with the same locator
     */
    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Wait for Alert to appear and switch to it
     */
    protected Alert switchToAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }


    /**
     * Get title of Current page
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get source of current page
     */
    public String getCurrentPageSource() {
        return driver.getPageSource();
    }

    /**
     * Finds new Window and switches to it given its title
     */
    public void switchToWindowWithTitle(String expectedTitle) {
        //Switching to new Window
        String firstWindow = driver.getWindowHandle();

//        Finding all windows and putting them in a set list
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> windowsIterator = allWindows.iterator();

        while (windowsIterator.hasNext()) {

            //storing the window index
            String windowHandle = windowsIterator.next();

            //checking if the current index is not equal to the initial index
            if (!windowHandle.equals(firstWindow)) {
                //Switching to new Window
                driver.switchTo().window(windowHandle);

                //Getting the current Window title and checking it agains 'New Window'
                if (getCurrentPageTitle().equals(expectedTitle)) {
                    break;
                }
            }
        }
    }

    /**
     * Switch to iFrame using its locator
     */
    protected void switchToFrame(By frameLocator) {
        driver.switchTo().frame(find(frameLocator));
    }

    /**
     * Press Key on locator
     */
    protected void pressKey(By locator, Keys key) {
        find(locator).sendKeys(key);
    }

    /**
     * Press Key using Actions class
     */
    public void pressKeyWithActions(Keys key){
        log.info("Pressing " +key.name() + " using Actions class");
        Actions action = new Actions(driver);
        action.sendKeys(key).build().perform();
    }

    /**
     * Scroll to the bottom
     */
    public void scrollToBottom(){
        log.info("Scrolling to the bottom of the page");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    /**
     * Perform Drag and Drop 'from' element 'to' element
     */
    protected void performDragandDrop(By from, By to){
        //Actions action = new Actions (driver);
        //action.dragAndDrop(find(from), find(to).build.perform();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(
                "function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                        + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
                        + "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                        + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n"
                        + "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                        + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                        + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                        + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n"
                        + "}\n" + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                        + "var dragStartEvent =createEvent('dragstart');\n"
                        + "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n"
                        + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                        + "var dragEndEvent = createEvent('dragend');\n"
                        + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                        + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                        + "simulateHTML5DragAndDrop(source,destination);",
                find(from), find(to));
    }

    /**
     * Perform mouse hover over element
     */
    protected void hoverOverElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    /** Add Cookie */
    public void setCookie(Cookie ck){
        log.info("Adding coockie "+ ck.getName());
        driver.manage().addCookie(ck);
        log.info("Cookie added");
    }

    /** Get cookie value using cookie name*/
    public String getCookie(String name){
        log.info("Getting value of cookie ");
        return driver.manage().getCookieNamed(name).getValue();
    }

}

