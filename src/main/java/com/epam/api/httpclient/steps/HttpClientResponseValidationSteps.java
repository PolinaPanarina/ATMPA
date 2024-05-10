package com.epam.api.httpclient.steps;

import com.epam.api.dto.dashboard.ResponseMessageDto;
import io.qameta.allure.Step;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpClientResponseValidationSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientResponseValidationSteps.class);

    @Step
    public void validateResponseCode(HttpResponse resp, int expectedStatusCode) {
        LOGGER.info("validateResponseCode is equal to: " + expectedStatusCode);
        assertThat(resp.getStatusLine().getStatusCode()).as("Status code is equal to '%s'", expectedStatusCode)
                .isEqualTo(expectedStatusCode);
    }

    @Step
    public void validateResponseMessage(ResponseMessageDto resp, String expectedMessage) {
        LOGGER.info("validateMessage is equal to: " + expectedMessage);
        assertThat(resp.getMessage()).as("Response message is equal to " +
                "'%s'", expectedMessage).isEqualTo(expectedMessage);
    }
}
