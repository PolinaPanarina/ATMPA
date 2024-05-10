package com.epam.api.steps;

import com.epam.api.dto.dashboard.ResponseMessageDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseValidationApiSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseValidationApiSteps.class);

    @Step
    public void validateResponseCode(Response resp, int expectedStatusCode) {
        LOGGER.info("validateResponseCode is equal to: " + expectedStatusCode);
        assertThat(resp.statusCode()).as("Status code is equal to '%s'", expectedStatusCode)
                .isEqualTo(expectedStatusCode);
    }

    @Step
    public void validateResponseMessage(Response resp, String expectedMessage) {
        LOGGER.info("validateMessage is equal to: " + expectedMessage);
        assertThat(resp.getBody().as(ResponseMessageDto.class).getMessage()).as("Response message is equal to " +
                "'%s'", expectedMessage).isEqualTo(expectedMessage);
    }
}
