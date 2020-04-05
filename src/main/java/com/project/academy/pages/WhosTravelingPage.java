package com.project.academy.pages;

import com.project.academy.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WhosTravelingPage extends BasePage {

    private static final String FIRST_NAME_FIELD = "firstname[0]";
    private static final String PHONE_NUMBER_FIELD = "phone-number[0]";
    private static final String COUNTRY_CODE_DROPDOWN = "country_code[0]";
    private static final String PROTECT_FLIGHT = "insurance";
    private static final String GET_MONEY_BACK = "assurance";
    private static final String COMPLETE_BOOKING = "complete-booking";

    private WebElement firstNameField;
    private WebElement phoneNumberField;
    private WebElement countryCodeDropdown;
    private WebElement protectYourFlightSection;
    private WebElement getMoneyBackSection;
    private WebElement completeBookingButton;

    /**
     * Constructor.
     * @param driver Web driver.
     */
    WhosTravelingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if firs name field is present.
     * @return True if field is present. False if not.
     */
    public boolean isFirstNameFieldPresent() {
        firstNameField = findElement(By.id(FIRST_NAME_FIELD));
        if(firstNameField!=null){
            return firstNameField.isDisplayed();
        }
        return false;
    }

    /**
     * Check if phone number field is present.
     * @return True if field is present. False if not.
     */
    public boolean isPhoneNumberFieldPresent() {
        phoneNumberField = findElement(By.id(PHONE_NUMBER_FIELD));
        if(phoneNumberField!=null){
            return phoneNumberField.isDisplayed();
        }
        return false;
    }

    /**
     * Check if country code dropdown is present.
     * @return True if element is present. False if not.
     */
    public boolean isCountryCodeDropdownPresent() {
        countryCodeDropdown = findElement(By.id(COUNTRY_CODE_DROPDOWN));
        if(countryCodeDropdown!=null){
            return countryCodeDropdown.isDisplayed();
        }
        return false;
    }

    /**
     * Check if protect your flight element is present.
     * @return True if element is present. False if not.
     */
    public boolean isProtectYourFlightSectionPresent() {
        protectYourFlightSection = findElement(By.id(PROTECT_FLIGHT));
        if(protectYourFlightSection!=null){
            return protectYourFlightSection.isDisplayed();
        }
        return false;
    }

    /**
     * Check if get your money back element is present.
     * @return True if element is present. False if not.
     */
    public boolean isGetMoneyBackSectionPresent() {
        getMoneyBackSection = findElement(By.id(GET_MONEY_BACK));
        if(getMoneyBackSection!=null){
            return getMoneyBackSection.isDisplayed();
        }
        return false;
    }

    /**
     * Check if complete booking button is present.
     * @return True if element is present. False if not.
     */
    public boolean isCompleteBookingButtonPresent() {
        completeBookingButton = findElement(By.id(COMPLETE_BOOKING));
        if(completeBookingButton!=null){
            return completeBookingButton.isDisplayed();
        }
        return false;
    }
}
