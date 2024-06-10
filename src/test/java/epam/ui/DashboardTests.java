package epam.ui;

import com.epam.api.dto.dashboard.DashboardDataDto;
import com.epam.services.properties.PropertiesReader;
import com.epam.staticdata.enums.DashboardWidgetNames;
import com.epam.ui.steps.AllDashboardsPageSteps;
import com.epam.ui.steps.DashboardPageSteps;
import com.epam.ui.steps.LoginPageSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.epam.staticdata.enums.PropertiesEnum.*;

public class DashboardTests extends BaseUiTest {
    private AllDashboardsPageSteps allDashboardsPageSteps;
    private DashboardPageSteps dashboardPageSteps;
    private LoginPageSteps loginPageSteps;
    public static final String URL_PROPERTY_FILE_NAME = "urls.properties";

    @BeforeEach()
    void setUpJUnit() {
        loginPageSteps = new LoginPageSteps();
        allDashboardsPageSteps = new AllDashboardsPageSteps();
        dashboardPageSteps = new DashboardPageSteps();
    }

    @ParameterizedTest
    @MethodSource("com.epam.testData.DashboardDataProvider#demoData")
    public void verifyMovingWidget(DashboardDataDto dashboardData) {
        String staticArea = DashboardWidgetNames.LAUNCH_STATISTICS_AREA.name();
        String staticBar = DashboardWidgetNames.LAUNCH_STATISTICS_BAR.name();

        loginPageSteps.openLoginPage();
        loginPageSteps.enterUserLogin(PropertiesReader.readSecret(USER_NAME_PROPERTY.getValue()));
        loginPageSteps.enterUserPassword(PropertiesReader.readSecret(PASSWORD_PROPERTY.getValue()));
        loginPageSteps.clickOnLoginButton();
        allDashboardsPageSteps.verifyDashboardPageIsOpened();
        allDashboardsPageSteps.choseProject(PropertiesReader.getProperty(URL_PROPERTY_FILE_NAME, RP_PROJECT_NAME.getValue()));
        allDashboardsPageSteps.verifyDashboardPageIsOpened();
        allDashboardsPageSteps.verifyDashboardIsPresent(dashboardData);

        allDashboardsPageSteps.openDashboard(dashboardData.getDashboardName());
        dashboardPageSteps.verifyDashboardPageOpened(dashboardData.getDashboardName());
        dashboardPageSteps.verifyMovingWidgetToTheOtherWidgetPlace(staticArea, staticBar);
    }

}
