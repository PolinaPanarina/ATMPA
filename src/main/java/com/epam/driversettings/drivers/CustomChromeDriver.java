package com.epam.driversettings.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomChromeDriver {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomChromeDriver.class);

    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        LOGGER.info(String.format("Initializing new driver: %s", CustomChromeDriver.class.getSimpleName()));
        return new ChromeDriver(setupChromeOptions());
    }

    public ChromeOptions setupChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--headless");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        LOGGER.info(String.format("Setting up Chrome options: %s", options));
        return options;
    }

}

