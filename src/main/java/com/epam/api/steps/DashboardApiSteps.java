package com.epam.api.steps;

import com.epam.api.config.CustomResponse;
import com.epam.api.config.RequestConverter;
import com.epam.api.dto.dashboard.CreateDashboardDto;
import com.epam.api.dto.dashboard.DashboardIdDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.api.config.UnifiedRequests.*;
import static com.epam.api.dto.dashboard.transform.TransformToDto.transformToDashboardIdDto;

public class DashboardApiSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardApiSteps.class);
    public String dashboardName;
    public DashboardIdDto dashboardId;
    public CreateDashboardDto createDashboard;

    @Step
    public void setupData(String url, RequestConverter requestConverter) {
        LOGGER.info("Step: setupData");
        createDashboard = new CreateDashboardDto("none", generateDashboardName());
        CustomResponse setupResponse = sendPost(requestConverter, url, createDashboard);

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(setupResponse.getStatusCode(), HttpStatus.SC_CREATED);

        dashboardId = transformToDashboardIdDto(setupResponse.getResponseBody());
    }

    @Step
    public void deleteData(String url, RequestConverter requestConverter) {
        LOGGER.info("Step: deleteCreatedData");
        CustomResponse setupResponse = sendGet(requestConverter, url);

        if (setupResponse.getStatusCode() == HttpStatus.SC_OK) {
            CustomResponse deleteResponse = sendDelete(requestConverter, url);
            ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
            responseValidationApiSteps.validateResponseCode(deleteResponse.getStatusCode(), HttpStatus.SC_OK);
        }
    }

    public String generateDashboardName() {
        String generatedString = RandomStringUtils.random(5, true, true);
        return dashboardName = "TEST" + generatedString;
    }

}
