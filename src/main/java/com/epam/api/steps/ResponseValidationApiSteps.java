package com.epam.api.steps;

import com.epam.api.dto.dashboard.ResponseMessageDto;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseValidationApiSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseValidationApiSteps.class);

    @Step
    public void validateResponseCode(int respStatusCode, int expectedStatusCode) {
        LOGGER.info("validateResponseCode is equal to: " + expectedStatusCode);
        assertThat(respStatusCode).as("Status code is equal to '%s'", expectedStatusCode)
                .isEqualTo(expectedStatusCode);
    }

    @Step
    public void validateResponseMessage(ResponseMessageDto respMessage, String expectedMessage) {
        LOGGER.info("validateMessage is equal to: " + expectedMessage);
        assertThat(respMessage.getMessage()).as("Response message is equal to " + "'%s'", expectedMessage)
                .isEqualTo(expectedMessage);
    }
}
