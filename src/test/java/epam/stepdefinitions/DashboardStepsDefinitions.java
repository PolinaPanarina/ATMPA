package epam.stepdefinitions;

import com.epam.api.dto.dashboard.DashboardDataDto;
import com.epam.services.properties.PropertiesReader;
import com.epam.staticdata.enums.PropertiesEnum;
import com.epam.ui.steps.DashboardPageSteps;
import com.epam.ui.steps.LoginPageSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class DashboardStepsDefinitions {

    private final LoginPageSteps loginPageSteps = new LoginPageSteps();
    private final DashboardPageSteps dashboardPageSteps = new DashboardPageSteps();
    public static final String URL_PROPERTY_FILE_NAME = "urls.properties";
    public static final String DASHBOARD_DATA_PROPERTY_FILE_NAME = "data.properties";


    @Given("I am logged in to RP with valid credentials")
    public void navigateToLoginPage(DataTable dataTable) {
        Map<String, PropertiesEnum> credentials = dataTable.asMap(String.class, PropertiesEnum.class);
        loginPageSteps.openLoginPage();
        loginPageSteps.enterUserLogin(PropertiesReader.readSecret(credentials.get("username").getValue()));
        loginPageSteps.enterUserPassword(PropertiesReader.readSecret(credentials.get("password").getValue()));
        loginPageSteps.clickOnLoginButton();
    }

    @Then("I should see the Dashboard page is opened")
    public void verifyDashboardPageIsOpened() {
        dashboardPageSteps.verifyDashboardPageIsOpened();
    }

    @When("I can chose the project")
    public void choseTheProject(DataTable dataTable) {
        Map<String, PropertiesEnum> credentials = dataTable.asMap(String.class, PropertiesEnum.class);
        dashboardPageSteps.choseProject(PropertiesReader.getProperty(URL_PROPERTY_FILE_NAME, credentials.get("projectName").getValue()));
    }

    @Then("I should see that dashboard data is present {string} and {string}")
    public void verifyThatDashboardDataIsPresent(String dashboardName, String dashboardOwner) {
        DashboardDataDto dashboardData = new DashboardDataDto(PropertiesReader.getProperty(DASHBOARD_DATA_PROPERTY_FILE_NAME,
                dashboardName), PropertiesReader.getProperty(DASHBOARD_DATA_PROPERTY_FILE_NAME, dashboardOwner));
        dashboardPageSteps.verifyDashboardIsPresent(dashboardData);
    }

    @When("I search {string}")
    public void searchDashboardByName(String dashboardName) {
        dashboardPageSteps.searchDashboard(PropertiesReader.getProperty(DASHBOARD_DATA_PROPERTY_FILE_NAME,
                dashboardName));
    }

    @Then("I should see that dashboard data is present in first lines {string} and {string}")
    public void verifyDashboardDataIsDisplayedInFirstLines(String dashboardName, String dashboardOwner) {
        DashboardDataDto dashboardData = new DashboardDataDto(PropertiesReader.getProperty(DASHBOARD_DATA_PROPERTY_FILE_NAME,
                dashboardName), PropertiesReader.getProperty(DASHBOARD_DATA_PROPERTY_FILE_NAME, dashboardOwner));
        dashboardPageSteps.verifyFirstSearchedDashboardData(dashboardData);
    }


}
