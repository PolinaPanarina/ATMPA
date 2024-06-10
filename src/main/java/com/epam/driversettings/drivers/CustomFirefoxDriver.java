package com.epam.driversettings.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFirefoxDriver {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFirefoxDriver.class);

    public WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        LOGGER.info(String.format("Initializing new driver: %s", CustomFirefoxDriver.class.getSimpleName()));
        return new FirefoxDriver(setupFirefoxOptions());
    }

    public FirefoxOptions setupFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setHeadless(true);
        LOGGER.info(String.format("Setting up Chrome options: %s", options));
        return options;
    }

}

