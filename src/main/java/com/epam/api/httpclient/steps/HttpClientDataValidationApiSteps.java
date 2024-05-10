package com.epam.api.httpclient.steps;

import com.epam.api.dto.dashboard.DashboardDataDto;
import io.qameta.allure.Step;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpClientDataValidationApiSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientDataValidationApiSteps.class);

    @Step
    public void validateData(HttpResponse resp, DashboardDataDto dashboardData) {
        LOGGER.info("validate data from response");
        String responseBody;
        try {
            responseBody = EntityUtils.toString(resp.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(responseBody).contains(dashboardData.getDashboardName());
        assertThat(responseBody).contains(dashboardData.getOwner());
    }

    @Step
    public void validateResponseCode(HttpResponse resp, int expectedStatusCode) {
        LOGGER.info("validate response code");
        assertThat(resp.getStatusLine().getStatusCode()).as("Status code is equal to '%s'", expectedStatusCode)
                .isEqualTo(expectedStatusCode);
    }
}
