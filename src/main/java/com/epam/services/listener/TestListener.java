package com.epam.services.listener;

import com.epam.driversettings.WebDriverFactory;
import com.epam.services.properties.PropertiesReader;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.epam.staticdata.enums.PropertiesEnum.BROWSER;

public class TestListener implements AfterEachCallback, BeforeEachCallback, BeforeAllCallback, TestWatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestListener.class);
    public static final String PROPERTY_FILE_NAME = "browser.properties";

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        LOGGER.info(String.format("Starting test: %s.%s", extensionContext.getRequiredTestClass().getName(), extensionContext.getRequiredTestMethod().getName()));
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        LOGGER.info(String.format("Finished test: %s.%s", extensionContext.getRequiredTestClass().getName(), extensionContext.getRequiredTestMethod().getName()));
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        LOGGER.info(String.format("Configuring test class: %s", extensionContext.getDisplayName()));
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LOGGER.info(String.format("Test \"%s\" disabled because of: %s", context.getDisplayName(), reason));
        WebDriverFactory.quitDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOGGER.info(String.format("Test \"%s\" successfully finished", context.getDisplayName()));
        WebDriverFactory.quitDriver();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOGGER.info(String.format("Test \"%s\" aborted because of: %s", context.getDisplayName(), cause.getMessage()));
        WebDriverFactory.quitDriver();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOGGER.info(String.format("Test \"%s\" failed with exception: %s", context.getDisplayName(), cause.getMessage()));
        takeScreenshot(context);
        WebDriverFactory.quitDriver();
    }

    void takeScreenshot(ExtensionContext context) {
        WebDriver driver = WebDriverFactory.getDriver(PropertiesReader.getProperty(PROPERTY_FILE_NAME, BROWSER.getValue()));
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String testName = context.getDisplayName();
        try {
            FileUtils.copyFile(source, new File("./screenshots/" + testName + ".png"));
            LOGGER.info("screenshot saved to:" + "./screenshots/" + testName + ".png");
            System.out.println("Screenshot taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}