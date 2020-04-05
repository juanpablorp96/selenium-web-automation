package com.project.academy.pages;

import com.project.academy.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.project.academy.utils.CustomWait.LONG_WAIT_SECONDS;
import static com.project.academy.utils.CustomWait.NORMAL_WAIT_SECONDS;

public class SelectReturnPage extends BasePage {

    private static final String FLIGHT_MODULE_LIST = "flightModuleList";
    private static final String SELECT_BUTTON = "t-select-btn";
    private static final String FLIGHT_RESULTS = "flight-module";
    private static final String FLIGHT_HOTEL_POPUP = "forcedChoiceNoThanks";

    @FindBy(id = FLIGHT_MODULE_LIST)
    private WebElement flightModuleList;
    @FindBy(className = SELECT_BUTTON)
    private WebElement selectButton;

    SelectReturnPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Select third flight result.
     */
    public void selectThirdResult() {
        customWait.waitElementToBeClickable(getDriver(), selectButton, NORMAL_WAIT_SECONDS);
        List<WebElement> flightResults = flightModuleList.findElements(By.className(FLIGHT_RESULTS));
        selectResult(flightResults, 2, SELECT_BUTTON);
    }

    /**
     * If flight + hotel pop-up exist, click on No thanks.
     * @return New ReviewYourTripPage.
     */
    public ReviewYourTripPage flightHotelPopUp(){
        WebElement noThanksLinkText = findElement(By.id(FLIGHT_HOTEL_POPUP));
        if(noThanksLinkText!=null){
            customWait.waitElementToBeClickable(getDriver(), noThanksLinkText, NORMAL_WAIT_SECONDS);
            click(noThanksLinkText);
        }
        List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        customWait.waitUrlContains(getDriver(), LONG_WAIT_SECONDS);
        customWait.waitPageLoad(getDriver(), LONG_WAIT_SECONDS);
        return new ReviewYourTripPage(getDriver());
    }
}
