package com.project.academy.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CustomWait {

    /**
     * Time in seconds for waits methods.
     */
    public static final long SHORT_WAIT_SECONDS = 5;
    public static final long NORMAL_WAIT_SECONDS = 15;
    public static final long LONG_WAIT_SECONDS = 40;

    /**
     * Wait an element to be clickable.
     * @param driver Web driver.
     * @param element Web element.
     * @param waitTime Time to wait in seconds.
     */
    public void waitElementToBeClickable(WebDriver driver, WebElement element, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait until page url contains a given String.
     * @param driver Web driver.
     * @param waitTime Time to wait in seconds.
     */
    public void waitUrlContains(WebDriver driver, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.urlContains("https://www.travelocity.com/Flight-Information?"));
    }

    /**
     * Wait until the page load.
     * @param driver Web driver.
     * @param waitTime Time to wait in seconds.
     */
    public void waitPageLoad(WebDriver driver, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Wait until the given expected condition.
     * @param driver Web driver.
     * @param expectedCondition Expected condition (boolean).
     * @param waitTime Time to wait in seconds.
     * @return WebDriverWait.
     */
    public WebDriverWait waitEqualStrings(WebDriver driver, ExpectedCondition expectedCondition, long waitTime){
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(expectedCondition);
        return wait;
    }

}
