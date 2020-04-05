package com.project.academy.pages;

import com.project.academy.utils.BasePage;
import com.project.academy.utils.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class TravelocityHomePage extends BasePage {

    private static final String TRAVELOCITY_URL = "https://www.travelocity.com/";
    private static final String FLIGHTS_BUTTON = "tab-flight-tab-hp";
    private static final String ROUNDTRIP_BUTTON = "flight-type-roundtrip-label-hp-flight";
    private static final String FLYING_FROM = "flight-origin-hp-flight";
    private static final String FLYING_TO = "flight-destination-hp-flight";
    private static final String DEPARTING = "flight-departing-hp-flight";
    private static final String NEXT_MONTH = "datepicker-next";
    private static final String RETURNING = "flight-returning-hp-flight";
    private static final String SEARCH_BUTTON = "gcw-submit";
    private static final String DATEPICKER_DAY = "datepicker-day-number";

    @FindBy(id = FLIGHTS_BUTTON)
    private WebElement flightsButton;
    @FindBy(id = ROUNDTRIP_BUTTON)
    private WebElement roundtripButton;
    @FindBy(id = FLYING_FROM)
    private WebElement flyingFrom;
    @FindBy(id = FLYING_TO)
    private WebElement flyingTo;
    @FindBy(id = DEPARTING)
    private WebElement departing;
    @FindBy(className = NEXT_MONTH)
    private WebElement nextMonthButton;
    @FindBy(id = RETURNING)
    private WebElement returning;
    @FindBy(className = SEARCH_BUTTON)
    private WebElement searchButton;

    /**
     * Constructor.
     * @param driver Web driver.
     */
    public TravelocityHomePage(WebDriver driver) {
        super(driver);
        driver.get(TRAVELOCITY_URL);
    }

    /**
     * Method to fill out flight information to then search a flight.
     *
     * @param flyingFromValue The origin place of the flight.
     * @param flyingToValue   The destination place of the flight.
     * @param departureDay    Departure day of the flight.
     * @param returnDay       Return day of the flight.
     * @return New SelectDeparturePage.
     */
    public SelectDeparturePage searchFlight(String flyingFromValue, String flyingToValue, String departureDay,
                                            String returnDay, String monthsInFuture) {
        int monthsInFutureInt = Integer.parseInt(monthsInFuture);
        click(flightsButton);
        click(roundtripButton);
        click(flyingFrom);
        sendKeys(flyingFrom, flyingFromValue);
        click(flyingTo);
        sendKeys(flyingTo, flyingToValue);
        click(departing);
        click(nextMonthButton);
        selectDayDatepicker(DATEPICKER_DAY, departureDay);
        customWait.waitElementToBeClickable(getDriver(), returning, CustomWait.SHORT_WAIT_SECONDS);
        click(returning);
        for(int i=0; i<monthsInFutureInt; i++){
            click(nextMonthButton);
        }
        selectDayDatepicker(DATEPICKER_DAY, returnDay);
        click(searchButton);
        return new SelectDeparturePage(getDriver());
    }
}
