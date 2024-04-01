package com.epam.services.listener;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TestListener implements AfterEachCallback, BeforeEachCallback, BeforeAllCallback, TestWatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestListener.class);

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
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOGGER.info(String.format("Test \"%s\" successfully finished", context.getDisplayName()));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOGGER.info(String.format("Test \"%s\" aborted because of: %s", context.getDisplayName(), cause.getMessage()));
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOGGER.info(String.format("Test \"%s\" failed with exception: %s", context.getDisplayName(), cause.getMessage()));
    }
}