package epam.api.httpclient;

import com.epam.api.dto.dashboard.*;
import com.epam.api.httpclient.config.BaseHttpClientApiConfig;
import com.epam.api.httpclient.steps.HttpClientDataSetupApiSteps;
import com.epam.api.httpclient.steps.HttpClientDataValidationApiSteps;
import com.epam.api.httpclient.steps.HttpClientResponseValidationSteps;
import com.epam.services.listener.TestListener;
import com.epam.services.properties.PropertiesReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;

import static com.epam.api.config.RequestSpec.API_PROPERTIES_FILE_NAME;
import static com.epam.staticdata.enums.PropertiesEnum.RP_API_DASHBOARD_PATH;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TestListener.class)
public class DashboardTest {

    public static final String DASHBOARD_URL = PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME, RP_API_DASHBOARD_PATH.getValue());
    public static BaseHttpClientApiConfig baseHttpClientApiSteps;
    public static HttpClientDataSetupApiSteps httpClientDataSetupApiSteps;
    public static HttpClientResponseValidationSteps httpClientResponseValidationSteps;

    @BeforeAll
    public static void setUpAPi() {
        baseHttpClientApiSteps = new BaseHttpClientApiConfig();
        httpClientDataSetupApiSteps = new HttpClientDataSetupApiSteps();
    }

    @BeforeEach
    public void setupTestData() {
        httpClientDataSetupApiSteps.setupData(DASHBOARD_URL);
    }

    @AfterEach
    public void deleteCreatedData() {
        httpClientDataSetupApiSteps.deleteData(DASHBOARD_URL, httpClientDataSetupApiSteps.dashboardId.getId());
    }

    @ParameterizedTest
    @MethodSource("com.epam.testData.DashboardDataProvider#dashboardData")
    public void verifyDashboardData(DashboardDataDto dashboardData) {
        HttpResponse response = baseHttpClientApiSteps.sendGetRequest(DASHBOARD_URL);

        HttpClientResponseValidationSteps responseValidationApiSteps = new HttpClientResponseValidationSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_OK);

        HttpClientDataValidationApiSteps httpClientDataValidationApiSteps = new HttpClientDataValidationApiSteps();
        httpClientDataValidationApiSteps.validateData(response, dashboardData);
    }

    @Test
    public void verifyDashboardCreationWithRandomName() {
        HttpResponse httpResponse;
        DashboardIdDto dashboardId;

        httpClientDataSetupApiSteps.createDashboard.setName(httpClientDataSetupApiSteps.generateDashboardName());
        CreateDashboardDto createDashboard = new CreateDashboardDto("none", httpClientDataSetupApiSteps.createDashboard.getName());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            httpResponse = httpClientDataSetupApiSteps.sendPostRequest(DASHBOARD_URL, objectMapper.writeValueAsString(createDashboard));
            String responseBody = EntityUtils.toString(httpResponse.getEntity());
            ObjectMapper objectMapperFromResponse = new ObjectMapper();
            dashboardId = objectMapperFromResponse.readValue(responseBody, DashboardIdDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpClientDataValidationApiSteps httpClientDataValidationApiSteps = new HttpClientDataValidationApiSteps();
        httpClientDataValidationApiSteps.validateResponseCode(httpResponse, HttpStatus.SC_CREATED);
        assertThat(dashboardId).isNotNull();
    }

    @Test
    public void verifyDashboardNameUpdate() {
        HttpResponse response;
        ResponseMessageDto responseMessageDto;
        String expectedResponseMessage = "Dashboard with ID = '" + httpClientDataSetupApiSteps.dashboardId.getId()
                + "' successfully updated";
        ObjectMapper objectMapper = new ObjectMapper();

        UpdateDashboardReqDto updateDashboardReq = new UpdateDashboardReqDto(httpClientDataSetupApiSteps
                .generateDashboardName());
        try {
            response = httpClientDataSetupApiSteps.sendPutRequest(DASHBOARD_URL, objectMapper.writeValueAsString(updateDashboardReq),
                    httpClientDataSetupApiSteps.dashboardId.getId());
            String responseBody = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapperFromResponse = new ObjectMapper();
            responseMessageDto = objectMapperFromResponse.readValue(responseBody, ResponseMessageDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HttpClientDataValidationApiSteps httpClientDataValidationApiSteps = new HttpClientDataValidationApiSteps();
        httpClientDataValidationApiSteps.validateResponseCode(response, HttpStatus.SC_OK);

        httpClientResponseValidationSteps = new HttpClientResponseValidationSteps();
        httpClientResponseValidationSteps.validateResponseMessage(responseMessageDto, expectedResponseMessage);
    }
}
