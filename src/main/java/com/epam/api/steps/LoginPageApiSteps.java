package com.epam.api.steps;

import com.epam.api.config.RequestSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPageApiSteps extends BaseApiSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPageApiSteps.class);
    ResponseValidationApiSteps responseValidationApiSteps;

    @Step
    public void verifyStatusCodeOfTheLoginPageOpening() {
        LOGGER.info("Step: verifyStatusCodeOfTheLoginPageOpening");
        Response response = sendGetRequest(RequestSpec.buildSimpleLoginSpecification());

        responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_OK);
    }
}
