package com.project.academy.tests;

import com.project.academy.dataProvider.Provider;
import com.project.academy.pages.*;
import com.project.academy.pojo.Parameter;
import com.project.academy.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TravelocityTest extends BaseTest {

    @Test(dataProvider = "dataTravelocity", dataProviderClass = Provider.class)
    public void testTravelocity(Parameter parameter) {
        TravelocityHomePage travelocityHomePage = getTravelocityHomePage();

        SelectDeparturePage selectDeparturePage = travelocityHomePage.searchFlight(parameter.getFlyingFrom(),
                parameter.getFlyingTo(), parameter.getDepartureDay(), parameter.getReturnDay(), parameter.getMonthsInFuture());
        Assert.assertTrue(selectDeparturePage.isSortByOptionsPresent(parameter.getSortByOptions()),
                "Sort by dropdown don't have all the options");
        Assert.assertTrue(selectDeparturePage.isSelectButtonInResults(),
                "Select button is not present in all results");
        Assert.assertTrue(selectDeparturePage.isDurationInResults(),
                "Flight duration is not present in all results");
        Assert.assertTrue(selectDeparturePage.isFlightDetailAndBaggageFeesInResults(),
                "Flight detail and baggage fees is not present in all results");
        Assert.assertTrue(selectDeparturePage.isSortedByDurationLongest(),
                "Result are not sorted by duration desc");

        SelectReturnPage selectReturnPage = selectDeparturePage.selectFirstResult();
        selectReturnPage.selectThirdResult();

        /**
         * Soft assertions because the elements may not appear depending of the flight dates, and is not critical for the business.
         */
        SoftAssert softAssertion= new SoftAssert();
        ReviewYourTripPage reviewYourTripPage = selectReturnPage.flightHotelPopUp();
        softAssertion.assertTrue(reviewYourTripPage.isTotalPricePresent(),
                "Total price is not present in REVIEW YOUR TRIP page");
        softAssertion.assertTrue(reviewYourTripPage.isPriceGuaranteePresent(),
                "Price guarantee is not present in REVIEW YOUR TRIP page");
        softAssertion.assertTrue(reviewYourTripPage.isDepartureInfoPresent(),
                "Departure information is not present in REVIEW YOUR TRIP page");
        softAssertion.assertTrue(reviewYourTripPage.isReturnInfoPresent(),
                "Return information is not present in REVIEW YOUR TRIP page");
        Assert.assertTrue(reviewYourTripPage.isContinueBookingButtonPresent(),
                "Continue booking button is not present in REVIEW YOUR TRIP page");

        WhosTravelingPage whosTravelingPage = reviewYourTripPage.continueBooking();
        softAssertion.assertTrue(whosTravelingPage.isFirstNameFieldPresent(),
                "First name field is not present in WHO'S TRAVELING page");
        softAssertion.assertTrue(whosTravelingPage.isPhoneNumberFieldPresent(),
                "Phone number field is not present in WHO'S TRAVELING page");
        softAssertion.assertTrue(whosTravelingPage.isCountryCodeDropdownPresent(),
                "Country code dropdown is not present in WHO'S TRAVELING page");
        softAssertion.assertTrue(whosTravelingPage.isProtectYourFlightSectionPresent(),
                "Protect your flight section is not present in WHO'S TRAVELING page");
        softAssertion.assertTrue(whosTravelingPage.isGetMoneyBackSectionPresent(),
                "Get your money back section is not present in WHO'S TRAVELING page");
        softAssertion.assertTrue(whosTravelingPage.isCompleteBookingButtonPresent(),
                "Complete booking button is not present in WHO'S TRAVELING page");
        softAssertion.assertAll();
    }
}
