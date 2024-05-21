package epam.api;

import com.epam.api.config.CustomResponse;
import com.epam.api.config.RequestConverter;
import com.epam.api.dto.dashboard.CreateDashboardDto;
import com.epam.api.dto.dashboard.DashboardIdDto;
import com.epam.api.dto.dashboard.GetDashboardByIdResponseDto;
import com.epam.api.dto.dashboard.UpdateDashboardReqDto;
import com.epam.api.httpclient.HttpClientRequestConverter;
import com.epam.api.restassured.RestAssuredRequestConverter;
import com.epam.api.steps.DashboardApiSteps;
import com.epam.api.steps.DataValidationApiSteps;
import com.epam.api.steps.ResponseValidationApiSteps;
import com.epam.services.properties.PropertiesReader;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.epam.api.config.UnifiedRequests.*;
import static com.epam.api.dto.dashboard.transform.TransformToDto.transformToDashboardIdDto;
import static com.epam.api.dto.dashboard.transform.TransformToDto.transformToResponseMessageDto;
import static com.epam.api.restassured.RequestSpec.API_PROPERTIES_FILE_NAME;
import static com.epam.staticdata.enums.PropertiesEnum.RP_API_DASHBOARD_PATH;
import static com.epam.staticdata.enums.PropertiesEnum.RP_API_DASHBOARD_PATH_N;
import static org.assertj.core.api.Assertions.assertThat;

public class DashboardApiTests extends BaseApiTest {
    public static final String PROPERTY_FILE_NAME = "data.properties";
    public static final String DASHBOARD_URL = PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME, RP_API_DASHBOARD_PATH.getValue());
    public static final String DASHBOARD_WRONG_URL = PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME, RP_API_DASHBOARD_PATH_N.getValue());
    public static DashboardApiSteps dashboardApiSteps;
    RequestConverter requestConverter;

    static Stream<RequestConverter> converterStream() {
        return Stream.of(new RestAssuredRequestConverter(), new HttpClientRequestConverter());
    }

    @BeforeAll
    public static void setUpAPi() {
        dashboardApiSteps = new DashboardApiSteps();
    }

    @AfterEach
    public void deleteCreatedData() {
        dashboardApiSteps.deleteData(DASHBOARD_URL + "/" + dashboardApiSteps.dashboardId.getId(), requestConverter);
    }

    @ParameterizedTest
    @MethodSource("converterStream")
    public void verifyDashboardDataByDashboardId(RequestConverter requestConverterForTest) {
        requestConverter = requestConverterForTest;
        dashboardApiSteps.setupData(DASHBOARD_URL, requestConverter);
        String url = DASHBOARD_URL + "/" + dashboardApiSteps.dashboardId.getId();

        CustomResponse response = sendGet(requestConverter, url);
        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_OK);

        GetDashboardByIdResponseDto getDashboardByIdResponseDto = new GetDashboardByIdResponseDto.Builder()
                .description(dashboardApiSteps.createDashboard.getDescription())
                .owner(PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardOwner2"))
                .id(dashboardApiSteps.dashboardId.getId())
                .name(dashboardApiSteps.createDashboard.getName())
                .build();

        DataValidationApiSteps dataValidationApiSteps = new DataValidationApiSteps();
        dataValidationApiSteps.validateCreatedDashboard(response.getResponseBody(), getDashboardByIdResponseDto);
    }

    @ParameterizedTest
    @MethodSource("converterStream")
    public void verifyErrorWhenSearchingDataByWrongUrl(RequestConverter requestConverterForTest) {
        requestConverter = requestConverterForTest;

        CustomResponse response = sendGet(requestConverterForTest, DASHBOARD_WRONG_URL);

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_FORBIDDEN);

        dashboardApiSteps.dashboardId = new DashboardIdDto(0);
    }

    @ParameterizedTest
    @MethodSource("converterStream")
    public void verifyDashboardCreationWithRandomName(RequestConverter requestConverterForTest) {
        requestConverter = requestConverterForTest;
        dashboardApiSteps.setupData(DASHBOARD_URL, requestConverter);

        dashboardApiSteps.createDashboard.setName(dashboardApiSteps.generateDashboardName());
        CreateDashboardDto createDashboard = new CreateDashboardDto("none",
                dashboardApiSteps.createDashboard.getName());

        CustomResponse response = sendPost(requestConverterForTest, DASHBOARD_URL, createDashboard);

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_CREATED);
        assertThat(transformToDashboardIdDto(response.getResponseBody()).getId()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("converterStream")
    public void verifyErrorWhilePostingExistentDashboard(RequestConverter requestConverterForTest) {
        requestConverter = requestConverterForTest;
        dashboardApiSteps.setupData(DASHBOARD_URL, requestConverter);
        CreateDashboardDto createDashboard = new CreateDashboardDto("none", dashboardApiSteps.dashboardName);

        CustomResponse response = sendPost(requestConverterForTest, DASHBOARD_URL, createDashboard);

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_CONFLICT);
    }

    @ParameterizedTest
    @MethodSource("converterStream")
    public void verifyErrorByPostingDashboardWithEmptyBody(RequestConverter requestConverterForTest) {
        requestConverter = requestConverterForTest;
        CustomResponse response = sendPost(requestConverterForTest, DASHBOARD_URL, "");

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @ParameterizedTest
    @MethodSource("converterStream")
    public void verifyDashboardDelete(RequestConverter requestConverterForTest) {
        requestConverter = requestConverterForTest;
        dashboardApiSteps.setupData(DASHBOARD_URL, requestConverter);
        CustomResponse response = sendDelete(requestConverterForTest, DASHBOARD_URL + "/" +
                dashboardApiSteps.dashboardId.getId());

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_OK);
    }

    @ParameterizedTest
    @MethodSource("converterStream")
    public void verifyErrorCodeWhileDeletingDashboardWithNonExistentId(RequestConverter requestConverterForTest) {
        requestConverter = requestConverterForTest;
        CustomResponse response = sendDelete(requestConverterForTest, DASHBOARD_URL + "/" + 0);

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);

        dashboardApiSteps.dashboardId = new DashboardIdDto(0);
    }


    @ParameterizedTest
    @MethodSource("converterStream")
    public void verifyDashboardNameUpdate(RequestConverter requestConverterForTest) {
        requestConverter = requestConverterForTest;
        dashboardApiSteps.setupData(DASHBOARD_URL, requestConverter);

        String expectedResponseMessage = "Dashboard with ID = '" + dashboardApiSteps.dashboardId.getId()
                + "' successfully updated";

        UpdateDashboardReqDto updateDashboardReq = new UpdateDashboardReqDto(dashboardApiSteps.generateDashboardName());
        CustomResponse response = sendPut(requestConverterForTest, DASHBOARD_URL + "/" +
                dashboardApiSteps.dashboardId.getId(), updateDashboardReq);

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_OK);
        responseValidationApiSteps.validateResponseMessage(transformToResponseMessageDto(response.getResponseBody()),
                expectedResponseMessage);
    }

    @ParameterizedTest
    @MethodSource("converterStream")
    public void verifyErrorWhileChangingNonExistentDashboard(RequestConverter requestConverterForTest) {
        requestConverter = requestConverterForTest;
        dashboardApiSteps.setupData(DASHBOARD_URL, requestConverter);

        UpdateDashboardReqDto updateDashboardReq = new UpdateDashboardReqDto("");
        CustomResponse response = sendPut(requestConverterForTest, DASHBOARD_URL + "/" +
                dashboardApiSteps.dashboardId.getId(), updateDashboardReq);

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }
}

