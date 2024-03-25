package com.epam.ui.steps;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSteps.class);

    public void waitUntilElementIsDisplayed(WebDriver driver, WebElement webElement) {
        LOGGER.info("Step: waitUntilElementIsDisplayed: " + webElement);
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(webElement));
    }
}
