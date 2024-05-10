package epam.api;

import com.epam.api.config.RequestSpec;
import com.epam.api.dto.dashboard.*;
import com.epam.api.steps.DashboardApiSteps;
import com.epam.api.steps.DataValidationApiSteps;
import com.epam.api.steps.ResponseValidationApiSteps;
import com.epam.services.properties.PropertiesReader;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.epam.api.config.RequestSpec.API_PROPERTIES_FILE_NAME;
import static com.epam.staticdata.enums.PropertiesEnum.RP_API_DASHBOARD_PATH;
import static com.epam.staticdata.enums.PropertiesEnum.RP_API_DASHBOARD_PATH_N;
import static org.assertj.core.api.Assertions.assertThat;

public class DashboardApiTests extends BaseApiTest {

    public static final String PROPERTY_FILE_NAME = "data.properties";
    public static final String DASHBOARD_URL = PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME, RP_API_DASHBOARD_PATH.getValue());
    public static final String DASHBOARD_WRONG_URL = PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME, RP_API_DASHBOARD_PATH_N.getValue());
    public static DashboardApiSteps dashboardApiSteps;

    @BeforeAll
    public static void setUpAPi() {
        dashboardApiSteps = new DashboardApiSteps();
    }

    @BeforeEach
    public void setupTestData() {
        dashboardApiSteps.setupData(DASHBOARD_URL);
    }

    @AfterEach
    public void deleteCreatedData() {
        dashboardApiSteps.deleteData(DASHBOARD_URL, dashboardApiSteps.dashboardId.getId());
    }

    @ParameterizedTest
    @MethodSource("com.epam.testData.DashboardDataProvider#dashboardData")
    public void verifyDashboardData(DashboardDataDto dashboardData) {
        Response response = dashboardApiSteps.sendGetRequest(RequestSpec.buildWithUrl(DASHBOARD_URL));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_OK);

        DataValidationApiSteps dataValidationApiSteps = new DataValidationApiSteps();
        dataValidationApiSteps.validateData(response, dashboardData);
    }

    @Test
    public void verifyDashboardDataByDasboardId() {
        Response response = dashboardApiSteps.sendGetRequest(RequestSpec
                .buildWithDasboardId(DASHBOARD_URL, dashboardApiSteps.dashboardId.getId()));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_OK);

        GetDashboardByIdResponseDto getDashboardByIdResponseDto = new GetDashboardByIdResponseDto.Builder()
                .description(dashboardApiSteps.createDashboard.getDescription())
                .owner(PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardOwner2"))
                .id(dashboardApiSteps.dashboardId.getId())
                .name(dashboardApiSteps.createDashboard.getName())
                .build();

        DataValidationApiSteps dataValidationApiSteps = new DataValidationApiSteps();
        dataValidationApiSteps.validateCreatedDashboard(response, getDashboardByIdResponseDto);
    }

    @Test
    public void verifyErrorWhenSearchingDataByWrongUrl() {
        Response response = dashboardApiSteps.sendGetRequest(RequestSpec.buildWithUrl(DASHBOARD_WRONG_URL));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void verifyDashboardCreationWithRandomName() {
        dashboardApiSteps.createDashboard.setName(dashboardApiSteps.generateDashboardName());
        CreateDashboardDto createDashboard = new CreateDashboardDto("none",
                dashboardApiSteps.createDashboard.getName());

        Response response = dashboardApiSteps.sendPostRequest(RequestSpec.buildWithBody(DASHBOARD_URL, createDashboard));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_CREATED);
        assertThat(response.getBody().as(DashboardIdDto.class).getId()).isNotNull();
    }

    @Test
    public void verifyErrorWhilePostingExistentDashboard() {
        CreateDashboardDto createDashboard = new CreateDashboardDto("none", dashboardApiSteps.dashboardName);

        Response response = dashboardApiSteps.sendPostRequest(RequestSpec.buildWithBody(DASHBOARD_URL, createDashboard));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_CONFLICT);
    }

    @Test
    public void verifyErrorByPostingDashboardWithEmptyBody() {

        Response response = dashboardApiSteps.sendPostRequest(RequestSpec.buildWithBody(DASHBOARD_URL, ""));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void verifyDashboardDelete() {
        Response response = dashboardApiSteps.sendDeleteRequest(RequestSpec
                .buildWithDasboardId(DASHBOARD_URL, dashboardApiSteps.dashboardId.getId()));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_OK);
    }

    @Test
    public void verifyErrorCodeWhileDeletingDashboardWithNonExistentId() {
        Response response = dashboardApiSteps.sendDeleteRequest(RequestSpec.buildWithDasboardId(DASHBOARD_URL, 0));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void verifyDashboardNameUpdate() {
        String expectedResponseMessage = "Dashboard with ID = '" + dashboardApiSteps.dashboardId.getId()
                + "' successfully updated";

        UpdateDashboardReqDto updateDashboardReq = new UpdateDashboardReqDto(dashboardApiSteps.generateDashboardName());
        Response response = dashboardApiSteps.sendPutRequest(RequestSpec
                .buildWithBodyAndDasboardId(DASHBOARD_URL, dashboardApiSteps.dashboardId.getId(), updateDashboardReq));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_OK);
        responseValidationApiSteps.validateResponseMessage(response, expectedResponseMessage);
    }

    @Test
    public void verifyErrorWhileChangingNonExistentDashboard() {
        UpdateDashboardReqDto updateDashboardReq = new UpdateDashboardReqDto("");
        Response response = dashboardApiSteps.sendPutRequest(RequestSpec
                .buildWithBodyAndDasboardId(DASHBOARD_URL, dashboardApiSteps.dashboardId.getId(), updateDashboardReq));

        ResponseValidationApiSteps responseValidationApiSteps = new ResponseValidationApiSteps();
        responseValidationApiSteps.validateResponseCode(response, HttpStatus.SC_BAD_REQUEST);
    }
}
