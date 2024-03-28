package epam.ui;

import com.epam.services.properties.PropertiesReader;
import com.epam.ui.steps.DashboardPageSteps;
import com.epam.ui.steps.LoginPageSteps;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.epam.staticdata.enums.PropertiesEnum.PASSWORD_PROPERTY;
import static com.epam.staticdata.enums.PropertiesEnum.USER_NAME_PROPERTY;

public class LoginUITest extends BaseUiTest {
    public static LoginPageSteps loginPageSteps;
    public static DashboardPageSteps dashboardPageSteps;

    @BeforeAll()
    static void setUp() {
        loginPageSteps = new LoginPageSteps();
        dashboardPageSteps = new DashboardPageSteps();
    }

    @Test
    @DisplayName("Open Login Page test")
    @Description("Verify that RP Login Page is opened")
    void verifyThatRPLoginPageIsOpened() {
        loginPageSteps.openLoginPage();
        loginPageSteps.verifyThatRPLoginPageIsOpened();
    }

    @Test
    @DisplayName("Login with credentials test")
    @Description("Verify that User can login to the RP")
    void verifyThatUserCanLoginToTheRP() {
        loginPageSteps.openLoginPage();
        loginPageSteps.enterUserLogin(PropertiesReader.readSecret(USER_NAME_PROPERTY.getValue()));
        loginPageSteps.enterUserPassword(PropertiesReader.readSecret(PASSWORD_PROPERTY.getValue()));
        loginPageSteps.clickOnLoginButton();
        dashboardPageSteps.verifyDashboardPageIsOpened();
    }
}
