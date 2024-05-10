package com.epam.api.httpclient.steps;

import com.epam.api.dto.dashboard.CreateDashboardDto;
import com.epam.api.dto.dashboard.DashboardIdDto;
import com.epam.api.httpclient.config.BaseHttpClientApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpClientDataSetupApiSteps extends BaseHttpClientApiConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientDataSetupApiSteps.class);
    public String dashboardName;
    public DashboardIdDto dashboardId;
    public CreateDashboardDto createDashboard;
    public HttpResponse setupResponse;

    public void setupData(String url) {
        LOGGER.info("Step: setupData");
        createDashboard = new CreateDashboardDto("none", generateDashboardName());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            setupResponse = sendPostRequest(url, objectMapper.writeValueAsString(createDashboard));
            String responseBody = EntityUtils.toString(setupResponse.getEntity());
            ObjectMapper objectMapperFromResponse = new ObjectMapper();
            dashboardId = objectMapperFromResponse.readValue(responseBody, DashboardIdDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HttpClientDataValidationApiSteps httpClientDataValidationApiSteps = new HttpClientDataValidationApiSteps();
        httpClientDataValidationApiSteps.validateResponseCode(setupResponse, HttpStatus.SC_CREATED);
    }

    public void deleteData(String url, int dashboardId) {
        LOGGER.info("Step: deleteCreatedData");
        HttpResponse response = sendGetById(url, dashboardId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpResponse deleteResponse = senDeleteRequest(url, dashboardId);
            HttpClientDataValidationApiSteps httpClientDataValidationApiSteps = new HttpClientDataValidationApiSteps();
            httpClientDataValidationApiSteps.validateResponseCode(deleteResponse, HttpStatus.SC_OK);
        }
    }

    public String generateDashboardName() {
        String generatedString = RandomStringUtils.random(5, true, true);
        return dashboardName = "TEST" + generatedString;
    }
}
