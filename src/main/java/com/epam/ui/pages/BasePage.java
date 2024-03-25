package com.epam.ui.pages;

import com.epam.driversettings.WebDriverFactory;
import com.epam.services.properties.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.epam.staticdata.enums.PropertiesEnum.BROWSER;

public class BasePage {
    public WebDriver driver;
    public static final String PROPERTY_FILE_NAME = "browser.properties";

    public BasePage() {
        this.driver = WebDriverFactory.getDriver(PropertiesReader.getProperty(PROPERTY_FILE_NAME, BROWSER.getValue()));
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
