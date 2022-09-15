package com.qualitest.sjumbe.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;


//Adding listener, which will automatically print comments of the tests that are running
@Listeners({com.qualitest.sjumbe.base.TestListener.class})
public class BaseTest {
    /**
     * This class contains methods that are used in all test classes
     */

    //Attribute
    protected WebDriver driver;//Can be accessed in this class/package or subclass (child class) https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html#:~:text=The%20protected%20modifier%20specifies%20that,its%20class%20in%20another%20package.
    protected Logger log;

    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    @Parameters({"browser", "chromeProfile", "deviceName"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(
            Method method, @Optional("chrome") String browser, @Optional String profile, @Optional String deviceName, ITestContext ctx) {

        String testName = ctx.getCurrentXmlTest().getName();//getting currest test name from Test Suite
        log = LogManager.getLogger(testName);

        //passing browser parameter as String, and log to the BrowserDriverFactory instance
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);

        if (profile != null) {
            driver = factory.createChromeWithProfile(profile);
        } else if (deviceName != null) {
            driver = factory.createChromeWithMobileEmulation(deviceName);
        } else {
            driver = factory.createDriver();
        }


        driver.manage().window().maximize();

        this.testSuiteName = ctx.getSuite().getName();
        this.testName = testName;
        this.testMethodName = method.getName();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Close driver");
        // Close browser
        //driver.quit();
    }

}

