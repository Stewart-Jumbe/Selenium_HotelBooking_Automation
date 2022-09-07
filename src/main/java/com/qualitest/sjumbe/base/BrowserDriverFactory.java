package com.qualitest.sjumbe.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;


public class BrowserDriverFactory extends TestUtilities {
    /**
     * This class will handle browser creation
     */

    //Attributes
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private Logger log;


    //Constructor
    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    //Methods
    public WebDriver createDriver() {

        // Create driver
        log.info("Create driver: " + browser);

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver.set( new ChromeDriver());
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
                driver.set(new EdgeDriver());
                break;


            default:
                log.info("The browser " + browser + "is unknown, starting chrome instead.");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver.set(new ChromeDriver());
                break;
        }
        //opening the choosen browser
        return driver.get();
    }

    //Opens browser with pre-existing profile
    // see https://qttechacademy.udemy.com/course/advanced-selenium-webdriver/learn/lecture/12168714#notes
    public WebDriver createChromeWithProfile( String profile){
        log.info("Starting chrome driver with profile: "+ profile);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=src/main/resources/Profiles/");//Path to profiles

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }

    // used for emulating web site on mobile device
    //see https://qttechacademy.udemy.com/course/advanced-selenium-webdriver/learn/lecture/12168722#notes
    public WebDriver createChromeWithMobileEmulation(String deviceName) {
        log.info("Starting driver with " + deviceName + " emulation]");
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }
}
