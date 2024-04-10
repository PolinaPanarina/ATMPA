package com.epam.services.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info(String.format("Starting test: %s", result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info(String.format("Test \"%s\" successfully finished", result.getName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info(String.format("Test \"%s\" failed with exception: %s", result.getName(), result.getThrowable().getMessage()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info(String.format("Test \"%s\" skipped", result.getName()));
    }

}
