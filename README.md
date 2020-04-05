# Final-Project-Academy-TAE-III
Begin the process of booking a flight till the complete credit card information page.  
Precondition: The user is not logged in.  
Steps:
1. Search for a flight from LAS to LAX, 1 adult (clicking on Flight/Roundtrip). Dates should be at
least two month in the future and MUST be selected using the datepicker calendar.
2. Verify the result page using the following:  
a. There is a box that allow you to order by Price, Departure, Arrival and Duration.  
b. The select button is present on every result  
c. Flight duration is present on every result  
d. The flight detail and baggage fees is present on every result
3. Sort by duration > shorter. Verify the list was correctly sorted.  
a. From this step select elements on the list using the button Select (don’t use the
elements with Hotel Select Flight + Hotel)
4. In the page (Select your departure to Los Angeles), select the first result.
5. In the new page (Select your departure to Las Vegas), select the third result.  
a. if exists the pop-up asking for “flight with a Hotel”, select “no, thanks, maybe later”
6. Verify trip details in the new page, by:  
a. Trip total price is present  
b. Departure and return information is present  
c. Price guarantee text is present  
7. Press Continue Booking button.
8. Verify the “Who’s travelling” page is opened by choosing at least 5 validations to be
performed.  
  
  
#### Dependencies
* Selenium
* TestNG
* WebDriverManager
* Tinylog
  
#### Executing automated test
1. `mvn clean test`
  
#### Project structure

```
/Final-Project-Academy-TAE-III
    /src
        /main
            /java
                /com.project.academy
                    /pages
                        /ReviewYourTripPage.java
                        /SelectDeparturePage.java
                        /SelectReturnPage.java
                        /TravelocityHomePage.java
                        /WhosTravelingPage.java
                    /pojo
                        /Parameter.java
                    /utils
                        /BasePage.java
                        /BaseTest.java
                        /CustomWait.java
                        /Driver.java
        /test
            /java
                /com.project.academy
                    /dataProvider
                        /Provider.java
                    /resources
                        /data_tests_travelocity.json
                        /SuiteTest.xml
                    /tests
                        TravelocityTest
```
