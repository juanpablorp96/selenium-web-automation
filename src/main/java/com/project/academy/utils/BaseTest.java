package com.project.academy.utils;

import com.project.academy.pages.TravelocityHomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    private TravelocityHomePage travelocityHomePage;

    /**
     * Initialize a new instance of the web driver.
     * @param browser Web browser to initialize the driver.
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) {
        Driver driver = new Driver(browser);
        travelocityHomePage = new TravelocityHomePage(driver.getDriver());
    }

    /**
     * Quit the web driver.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        travelocityHomePage.dispose();
    }

    protected TravelocityHomePage getTravelocityHomePage() {
        return travelocityHomePage;
    }

}
