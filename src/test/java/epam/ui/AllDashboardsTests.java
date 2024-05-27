package epam.ui;

import com.epam.api.dto.dashboard.DashboardDataDto;
import com.epam.services.properties.PropertiesReader;
import com.epam.testData.DashboardDataProvider;
import com.epam.ui.steps.AllDashboardsPageSteps;
import com.epam.ui.steps.LoginPageSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.staticdata.enums.PropertiesEnum.*;
@Execution(ExecutionMode.CONCURRENT)
public class AllDashboardsTests extends BaseUiTest {
    private AllDashboardsPageSteps dashboardPageSteps;
    private LoginPageSteps loginPageSteps;
    public static final String URL_PROPERTY_FILE_NAME = "urls.properties";

    @BeforeMethod()
    void setUpTestNg() {
        loginPageSteps = new LoginPageSteps();
        dashboardPageSteps = new AllDashboardsPageSteps();
    }

    @BeforeEach()
    void setUpJUnit() {
        loginPageSteps = new LoginPageSteps();
        dashboardPageSteps = new AllDashboardsPageSteps();
    }

    @Test(dataProvider = "dashboardData", dataProviderClass = DashboardDataProvider.class)
    @ParameterizedTest
    @MethodSource("com.epam.testData.DashboardDataProvider#dashboardData")
    public void verifyDashboardData(DashboardDataDto dashboardData) {
        loginPageSteps.openLoginPage();
        loginPageSteps.enterUserLogin(PropertiesReader.readSecret(USER_NAME_PROPERTY.getValue()));
        loginPageSteps.enterUserPassword( PropertiesReader.readSecret(PASSWORD_PROPERTY.getValue()));
        loginPageSteps.clickOnLoginButton();
        dashboardPageSteps.verifyDashboardPageIsOpened();
        dashboardPageSteps.choseProject(PropertiesReader.getProperty(URL_PROPERTY_FILE_NAME, RP_PROJECT_NAME.getValue()));
        dashboardPageSteps.verifyDashboardPageIsOpened();
        dashboardPageSteps.verifyDashboardIsPresent(dashboardData);
    }

    @ParameterizedTest
    @MethodSource("com.epam.testData.DashboardDataProvider#dashboardData")
    public void verifySpecifiedDashboardData(DashboardDataDto dashboardData) {
        loginPageSteps.openLoginPage();
        loginPageSteps.enterUserLogin(PropertiesReader.readSecret(USER_NAME_PROPERTY.getValue()));
        loginPageSteps.enterUserPassword( PropertiesReader.readSecret(PASSWORD_PROPERTY.getValue()));
        loginPageSteps.clickOnLoginButton();
        dashboardPageSteps.verifyDashboardPageIsOpened();
        dashboardPageSteps.choseProject(PropertiesReader.getProperty(URL_PROPERTY_FILE_NAME, RP_PROJECT_NAME.getValue()));
        dashboardPageSteps.verifyDashboardPageIsOpened();
        dashboardPageSteps.searchDashboard(dashboardData.getDashboardName());
        dashboardPageSteps.verifyFirstSearchedDashboardData(dashboardData);
    }
}
