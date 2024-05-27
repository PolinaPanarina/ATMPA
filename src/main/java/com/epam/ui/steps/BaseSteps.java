package com.epam.ui.steps;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class BaseSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseSteps.class);

    public void waitUntilElementIsDisplayed(WebDriver driver, WebElement webElement) {
        LOGGER.info("waitUntilElementIsDisplayed: " + webElement);
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementToBeClickable(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitUntilElementHasValue(WebDriver driver, WebElement webElement, String value) {
        LOGGER.info("waitUntilElementHasValue: " + value);
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.textToBePresentInElementValue(webElement, value));
    }

    public void waitUntilElementHasText(WebDriver driver, WebElement webElement, String value) {
        LOGGER.info("waitUntilElementHasValue: " + value);
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.textToBePresentInElement(webElement, value));
    }

    public boolean isElementScrolledIntoView(WebDriver driver, WebElement webElement) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return window.getComputedStyle(arguments[0]).getPropertyValue('overflow') === 'auto' || " +
                        "window.getComputedStyle(arguments[0]).getPropertyValue('overflow') === 'scroll' || " +
                        "arguments[0].getBoundingClientRect().top >= 0 && " +
                        "arguments[0].getBoundingClientRect().left >= 0 && " +
                        "arguments[0].getBoundingClientRect().bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                        "arguments[0].getBoundingClientRect().right <= (window.innerWidth || document.documentElement.clientWidth);",
                webElement);
    }

    public void clickOnTheElement(WebDriver driver, WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    public void scrollToTheElement(WebDriver driver, WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        if (!isElementScrolledIntoView(driver, webElement)) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        }
    }
}
