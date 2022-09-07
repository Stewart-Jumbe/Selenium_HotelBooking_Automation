package com.qualitest.sjumbe.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.File;
import java.util.List;

public class TestUtilities extends BaseTest {
    /**
     * This class contains protected methods which can be used in test classes
     */
    public void sleep(long s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "files")
    protected static Object[][] files() {
        return new Object[][]{
                {1, "index.html"},
                {2, "randompic.jpg"},
                {3, "text.txt"}
        };
    }


    /**
     * Take screenshot
     */
    protected void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodaysDate()
                + File.separator + testSuiteName
                + File.separator + testName
                + File.separator + getSystemTime()
                + " " + fileName + ".png";

        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Todays date in yyyyMMdd format
     */
    private static String getTodaysDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    /** Current time in HHmmssSSS */
    private String getSystemTime(){
        return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }

    /**
     * Get logs from browser console
     */
    protected List<LogEntry> getBrowserLogs(){
        LogEntries log = driver.manage().logs().get("browser");
        List<LogEntry> logList = log.getAll();
        return logList;
    }

}
