package com.project.academy.pages;

import com.project.academy.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ReviewYourTripPage extends BasePage {

    private static final String TOTAL_PRICE = "packagePriceTotal";
    private static final String DEPARTURE_INFO = "importantInfoRuleOut0";
    private static final String RETURN_INFO = "importantInfoRuleIn0";
    private static final String PRICE_GUARANTEE = "priceGuarantee";
    private static final String CONTINUE_BOOKING_BUTTON = "bookButton";

    //Without @FinBy because elements may not appear.
    private WebElement totalPrice;
    private WebElement priceGuarantee;
    private WebElement departureInformation;
    private WebElement returnInformation;
    private WebElement continueBookingButton;

    /**
     * Constructor.
     * @param driver Web driver.
     */
    ReviewYourTripPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if total price element is present.
     * @return True if element is present. False if not.
     */
    public boolean isTotalPricePresent() {
        totalPrice = findElement(By.className(TOTAL_PRICE));
        if(totalPrice!=null){
            return totalPrice.isEnabled();
        }
        return false;
    }

    /**
     * Check if price guarantee element is present.
     * @return True if element is present. False if not.
     */
    public boolean isPriceGuaranteePresent() {
        priceGuarantee = findElement(By.className(PRICE_GUARANTEE));
        if(priceGuarantee!=null){
            return priceGuarantee.isEnabled();
        }
        return false;
    }

    /**
     * Check if departure information element is present.
     * @return True if element is present. False if not.
     */
    public boolean isDepartureInfoPresent() {
        departureInformation = findDepartureInfo(By.className(DEPARTURE_INFO));
        if(departureInformation!=null){
            return departureInformation.isEnabled();
        }
        return false;
    }

    /**
     * Check if return information element is present.
     * @return True if element is present. False if not.
     */
    public boolean isReturnInfoPresent() {
        returnInformation = findElement(By.className(RETURN_INFO));
        if(returnInformation!=null){
            return returnInformation.isEnabled();
        }
        return false;
    }

    /**
     * Check if continue booking element is present.
     * @return True if element is present. False if not.
     */
    public boolean isContinueBookingButtonPresent() {
        continueBookingButton = findElement(By.id(CONTINUE_BOOKING_BUTTON));
        if(continueBookingButton!=null){
            return continueBookingButton.isDisplayed();
        }
        return false;
    }

    /**
     * Click on continue booking button.
     * @return New WhosTravelingPage.
     */
    public WhosTravelingPage continueBooking() {
        click(continueBookingButton);
        return new WhosTravelingPage(getDriver());
    }

}
