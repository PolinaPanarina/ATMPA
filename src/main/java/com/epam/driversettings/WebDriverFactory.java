package com.epam.driversettings;

import com.epam.driversettings.drivers.CustomChromeDriver;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    private static WebDriver driver;
    public static final String CHROME = "chrome";

    private static WebDriver initializeDriver(String browserType) {
        if (driver == null) {
            switch (browserType.toLowerCase()) {
                case CHROME:
                    driver = new CustomChromeDriver().getDriver();
                    return driver;
                default:
                    throw new IllegalArgumentException("Browser wasn't provided or unsupported browser provided: " + browserType);
            }
        }
        return driver;
    }

    public static WebDriver getDriver(String browserType) {
        if (driver == null) {
            driver = WebDriverFactory.initializeDriver(browserType);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
