package com.project.academy.pages;

import com.project.academy.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.project.academy.utils.CustomWait.NORMAL_WAIT_SECONDS;

public class SelectDeparturePage extends BasePage {

    private static final String SORT_BY = "sortDropdown";
    private static final String SORT_OPTIONS = "*[data-opt-id]";
    private static final String FLIGHT_MODULE_LIST = "flightModuleList";
    private static final String FLIGHT_RESULTS_CSS = "*[data-test-id='offer-listing']";
    private static final String FLIGHT_DURATIONS = "duration-emphasis";
    private static final String FIRST_FLIGHT_DURATION = "duration-emphasis";
    private static final String SELECT_BUTTON = "t-select-btn";
    private static final String FEEDBACK_LINK = "feedbackLink";
    private static final String FLIGHT_DETAILS = "flight-details-link";
    private static final String DURATION_LONGEST = "duration:desc";

    @FindBy(id = SORT_BY)
    private WebElement sortBy;
    @FindBy(css = SORT_OPTIONS)
    private List<WebElement> sortOptions;
    @FindBy(id = FLIGHT_MODULE_LIST)
    private WebElement flightModuleList;
    @FindBy(css = FLIGHT_RESULTS_CSS)
    private List<WebElement> flightResults;
    @FindBy(className = FLIGHT_DURATIONS)
    private List<WebElement> flightDurations;
    @FindBy(className = FIRST_FLIGHT_DURATION)
    private WebElement firstFlightDuration;
    @FindBy(className = SELECT_BUTTON)
    private WebElement selectButton;
    @FindBy(id = FEEDBACK_LINK)
    private WebElement feedbackLink;

    /**
     * Constructor.
     * @param driver Web driver.
     */
    SelectDeparturePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if the given options are present on the dropdown options.
     * @param options Options expect to be on the sort by dropdown.
     * @return True if all options are in the dropdown values. False if not.
     */
    public boolean isSortByOptionsPresent(String options) {
        customWait.waitPageLoad(getDriver(), NORMAL_WAIT_SECONDS);
        String totalOptions = "";
        String[] values = options.split("\\|");
        customWait.waitElementToBeClickable(getDriver(), sortBy, NORMAL_WAIT_SECONDS);
        for (WebElement el : sortOptions) {
            totalOptions = totalOptions + el.getText();
        }
        for (String option : values) {
            if (!totalOptions.contains(option)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if all flight results have the select button.
     * @return True if all results have the select button. False if not.
     */
    public boolean isSelectButtonInResults() {
        return isElementsPresent(flightResults, SELECT_BUTTON);
    }

    /**
     * Check if all flight results have duration.
     * @return True if all results have duration. False if not.
     */
    public boolean isDurationInResults() {
        return isElementsPresent(flightResults, FLIGHT_DURATIONS);
    }

    /**
     * Check if all flight results have flight details and baggage fees.
     * @return True if all results have flifht details and baggage fees. False if not.
     */
    public boolean isFlightDetailAndBaggageFeesInResults() {
        return isElementsPresent(flightResults, FLIGHT_DETAILS);
    }

    /**
     * Check if results are sorted by duration desc.
     * @return True if result are sorted by duration desc. False if not.
     */
    public boolean isSortedByDurationLongest() {
        Duration longestDuration = getLongestDuration(flightDurations);
        selectOnDropdown(sortBy, DURATION_LONGEST);
        if(isFirstDurationFlightHotel(flightDurations, longestDuration)){
            List<WebElement> listWithoutHotelPromotion = new ArrayList<>(flightDurations);
            listWithoutHotelPromotion.remove(0);
            return isSorted(listWithoutHotelPromotion);
        }
        return isSorted(flightDurations);
    }

    /**
     * Select the first result.
     * @return New SelectReturnPage.
     */
    public SelectReturnPage selectFirstResult() {
        selectResult(flightResults, 0, SELECT_BUTTON);
        return new SelectReturnPage(getDriver());
    }


}
