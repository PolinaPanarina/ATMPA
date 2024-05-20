package com.epam.api.steps;

import com.epam.api.config.CustomResponse;
import com.epam.api.config.RequestConverter;
import com.epam.api.restassured.RequestSpec;
import com.epam.api.restassured.RestAssuredRequestConverter;
import com.epam.services.properties.PropertiesReader;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.api.config.UnifiedRequests.sendGet;
import static com.epam.api.restassured.RequestSpec.API_PROPERTIES_FILE_NAME;
import static com.epam.staticdata.enums.PropertiesEnum.RP_LOGIN_API_PATH;

public class LoginPageApiSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPageApiSteps.class);
    ResponseValidationApiSteps responseValidationApiSteps;

    @Step
    public void verifyStatusCodeOfTheLoginPageOpening() {
        LOGGER.info("Step: verifyStatusCodeOfTheLoginPageOpening");
        RequestConverter requestConverter = new RestAssuredRequestConverter();
        CustomResponse response = sendGet(requestConverter, String.format(PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME,
                RP_LOGIN_API_PATH.getValue()), "/dashboard"));

        responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_OK);
    }
}
