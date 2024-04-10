package epam.ui;

import com.epam.testData.DashboardDataProvider;
import com.epam.ui.steps.DashboardPageSteps;
import com.epam.ui.steps.LoginPageSteps;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Execution(ExecutionMode.CONCURRENT)
public class LoginUITest extends BaseUiTest {
    private LoginPageSteps loginPageSteps;
    private DashboardPageSteps dashboardPageSteps;

    @BeforeEach()
    void setUp() {
        loginPageSteps = new LoginPageSteps();
        dashboardPageSteps = new DashboardPageSteps();
    }

    @BeforeMethod()
    void setUpTestNg() {
        loginPageSteps = new LoginPageSteps();
        dashboardPageSteps = new DashboardPageSteps();
    }

    @ParameterizedTest()
    @Test
    @DisplayName("Login with credentials test")
    @Description("Verify that User can login to the RP")
    @MethodSource("com.epam.testData.DashboardDataProvider#loginData")
    void verifyThatUserCanLoginToTheRP(String userName, String password) {
        loginPageSteps.openLoginPage();
        loginPageSteps.enterUserLogin(userName);
        loginPageSteps.enterUserPassword(password);
        loginPageSteps.clickOnLoginButton();
        dashboardPageSteps.verifyDashboardPageIsOpened();
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = DashboardDataProvider.class)
    public void verifyLoginIsImpossibleWhenCredentialsAreIncorrect(String userName, String password) {
        loginPageSteps.openLoginPage();
        loginPageSteps.enterUserLogin(userName);
        loginPageSteps.enterUserPassword(password);
        loginPageSteps.clickOnLoginButton();
        loginPageSteps.verifyThatRPLoginPageIsOpened();
    }
}
