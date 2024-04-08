package epam.ui;

import com.epam.dashboard.DashboardData;
import com.epam.services.properties.PropertiesReader;
import com.epam.testData.DashboardDataProvider;
import com.epam.ui.steps.DashboardPageSteps;
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
public class DashboardTest extends BaseUiTest {
    private DashboardPageSteps dashboardPageSteps;
    private LoginPageSteps loginPageSteps;
    public static final String URL_PROPERTY_FILE_NAME = "urls.properties";

    @BeforeMethod()
    void setUpTestNg() {
        loginPageSteps = new LoginPageSteps();
        dashboardPageSteps = new DashboardPageSteps();
    }

    @BeforeEach()
    void setUpJUnit() {
        loginPageSteps = new LoginPageSteps();
        dashboardPageSteps = new DashboardPageSteps();
    }

    @Test(dataProvider = "dashboardData", dataProviderClass = DashboardDataProvider.class)
    @ParameterizedTest
    @MethodSource("com.epam.testData.DashboardDataProvider#dashboardData")
    public void verifyDashboardData(DashboardData dashboardData) {
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
    public void verifySpecifiedDashboardData(DashboardData dashboardData) {
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
