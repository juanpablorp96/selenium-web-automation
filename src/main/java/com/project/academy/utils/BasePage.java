package com.project.academy.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.pmw.tinylog.Logger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.project.academy.utils.CustomWait.NORMAL_WAIT_SECONDS;

public class BasePage {

    protected CustomWait customWait;
    private WebDriver driver;

    /**
     * Constructor.
     * Initialize web elements.
     * @param driver Web driver
     */
    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        customWait = new CustomWait();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected void dispose() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Click on a web element and log the action.
     * @param element Web element.
     */
    protected void click(WebElement element) {
        Logger.info("Click on: " + element.getText());
        element.click();
    }

    /**
     *Fill out a field with a value.
     * @param element Web element.
     * @param data Value to send.
     */
    protected void sendKeys(WebElement element, String data) {
        Logger.info("Fill out: " + data);
        element.sendKeys(data);
    }

    /**
     * Select the given day on a datepicker.
     * @param datepickerDayLocator Datepicker locator.
     * @param day Day number.
     */
    protected void selectDayDatepicker(String datepickerDayLocator, String day) {
        List<WebElement> dates = getDriver().findElements(By.className(datepickerDayLocator));
        for (WebElement element : dates) {
            if (element.getText().contains(day)) {
                click(element);
                break;
            }
        }
    }

    /**
     *Check if on each element of WebElement list is present a given WebElement.
     * @param elementList Web elements list.
     * @param className Web element Class name locator.
     * @return True if elementList.size() == numberOfElements, false if not.
     */
    protected boolean isElementsPresent(List<WebElement> elementList, String className) {
        int numberOfElements = 0;
        for (WebElement element : elementList) {
            WebElement webElement = element.findElement(By.className(className));
            if (webElement.isDisplayed()) {
                numberOfElements++;
            }
        }
        return elementList.size() == numberOfElements;
    }

    /**
     *Select an option of a dropdown.
     * @param dropdown Web element dropdown.
     * @param value Value to be selected on the dropdown.
     */
    protected void selectOnDropdown(WebElement dropdown, String value) {
        Logger.info("Select from dropdown: " + value);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    /**
     * Find the longest flight duration.
     * @param durations WenElement list of flight durations.
     * @return The longest flight duration.
     */
    protected Duration getLongestDuration(List<WebElement> durations) {
        List<Duration> durationsList = new ArrayList<>();
        for (WebElement element : durations) {
            String durationText = element.getText();
            durationText = durationText.replaceAll("\\s+", "");
            Duration duration = Duration.parse("PT" + durationText);
            durationsList.add(duration);
        }
        Collections.sort(durationsList);
        return durationsList.get(durationsList.size() - 1);
    }

    /**
     * Wait an element to have the expected text.
     * @param expectedDuration Expected duration.
     *                         //TODO
     */
    protected boolean isFirstDurationFlightHotel(List<WebElement> durations, Duration expectedDuration) {
        String durationEx = expectedDuration.toString();
        durationEx = durationEx.replaceAll("PT", "");
        durationEx = durationEx.replaceAll("H", "h ");
        durationEx = durationEx.replaceAll("M", "m");
        String finalDurationEx = durationEx;
        try{
            ExpectedCondition<Boolean> elementTextEqualsString = arg0 -> durations.get(0).getText().equals(finalDurationEx);
            customWait.waitEqualStrings(getDriver(), elementTextEqualsString, NORMAL_WAIT_SECONDS);
            return false;
        }
        catch (TimeoutException timeOut){
            return true;
        }
    }

    /**
     * Check if flight durations list is desc sorted.
     * @param flightDurations WebElement list of flight durations.
     * @return True if list is desc sorted, false if not.
     */
    protected boolean isSorted(List<WebElement> flightDurations) {
        for (int i = 0; i < flightDurations.size() - 1; i++) {
            String duration1 = flightDurations.get(i).getText();
            String duration2 = flightDurations.get(i + 1).getText();
            duration1 = duration1.replaceAll("\\s+", "");
            duration2 = duration2.replaceAll("\\s+", "");
            Duration d1 = Duration.parse("PT" + duration1);
            Duration d2 = Duration.parse("PT" + duration2);
            if (d1.compareTo(d2) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Select an specific result given the index.
     * @param flightResults WebElement list of flight results.
     * @param resultNumber Index of element in the list.
     * @param selectButtonLocator Select button locator.
     */
    protected void selectResult(List<WebElement> flightResults, int resultNumber, String selectButtonLocator) {
        List<WebElement> selectButton = flightResults.get(resultNumber).findElements(By.className(selectButtonLocator));
        if (selectButton.size() == 1) {
            click(selectButton.get(0));
        } else {
            click(selectButton.get(0));
            customWait.waitElementToBeClickable(getDriver(), selectButton.get(1), NORMAL_WAIT_SECONDS);
            click(selectButton.get(1));
        }
    }

    /**
     * Find WebElement if exist.
     * @param locator Web element locator.
     * @return WebElement or null.
     */
    protected WebElement findElement(By locator){
        WebElement element;
        try{
            element = getDriver().findElement(locator);
            return element;
        }
        catch (NoSuchElementException noElement){
            return null;
        }
    }

    /**
     * Find departure info element if exist.
     * @param locator Web element locator.
     * @return WebElement or null.
     */
    protected WebElement findDepartureInfo(By locator){
        WebElement element = findElement(locator);
        if(element!=null){
            if(element.getText().contains("Departure")){
                return element;
            }
        }
        return null;
    }

}
