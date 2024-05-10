package com.epam.api.steps;

import com.epam.api.config.RequestSpec;
import com.epam.api.dto.dashboard.CreateDashboardDto;
import com.epam.api.dto.dashboard.DashboardIdDto;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardApiSteps extends BaseApiSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardApiSteps.class);
    public ResponseValidationApiSteps responseValidationApiSteps;
    public DataValidationApiSteps dataValidationApiSteps;
    public Response setupResponse;
    public String dashboardName;
    public DashboardIdDto dashboardId;
    public CreateDashboardDto createDashboard;


    public void setupData(String url) {
        LOGGER.info("Step: setupData");

        createDashboard = new CreateDashboardDto("none", generateDashboardName());
        setupResponse = sendPostRequest(RequestSpec.buildWithBody(url, createDashboard));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(setupResponse, HttpStatus.SC_CREATED);

        dashboardId = setupResponse.getBody().as(DashboardIdDto.class);
    }

    public void deleteData(String url, int dashboardId) {
        LOGGER.info("Step: deleteCreatedData");
        Response response = sendGetRequest(RequestSpec.buildWithDasboardId(url, dashboardId));
        if (response.statusCode() == HttpStatus.SC_OK) {
            setupResponse = sendDeleteRequest(RequestSpec.buildWithDasboardId(url, dashboardId));
            ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
            responseValidationApiSteps.validateResponseCode(setupResponse, HttpStatus.SC_OK);
        }
    }

    public String generateDashboardName() {
        String generatedString = RandomStringUtils.random(5, true, true);
        return dashboardName = "TEST" + generatedString;
    }

}
