package com.epam.driversettings;

import com.epam.driversettings.drivers.CustomChromeDriver;
import com.epam.driversettings.drivers.CustomFirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";

    public static final Logger LOGGER = LoggerFactory.getLogger(WebDriverFactory.class);

    private static WebDriver initializeDriver(String browserType) {
        LOGGER.info("initializeDriver");
        return switch (browserType) {
            case CHROME -> new CustomChromeDriver().getDriver();
            case FIREFOX -> new CustomFirefoxDriver().getDriver();
            default -> throw new IllegalArgumentException("Unsupported browser provided: " + browserType);
        };

    }

    public static WebDriver getDriver(String browserType) {
        if (driver.get() == null) {
            driver.set(WebDriverFactory.initializeDriver(browserType));
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            LOGGER.info("quite driver");
            driver.get().quit();
            driver.remove();
        }
        LOGGER.info("driver quited");
    }

}
