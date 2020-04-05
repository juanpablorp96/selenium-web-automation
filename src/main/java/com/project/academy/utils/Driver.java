package com.project.academy.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class Driver {

    private WebDriver driver;

    /**
     * Constructor.
     * Choose a case given the Web browser name.
     * @param browser Web browser.
     */
    Driver(String browser) {
        switch (browser) {

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

        }
    }

    WebDriver getDriver() {
        return this.driver;
    }
}
