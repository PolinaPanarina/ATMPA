package epam.api;

import com.epam.api.steps.LoginPageApiSteps;
import org.junit.jupiter.api.*;

public class LoginApiTest extends BaseApiTest {

    public static LoginPageApiSteps loginPAgeApiSteps;

    @BeforeAll
    public static void setUpAPi() {
        loginPAgeApiSteps = new LoginPageApiSteps();
    }

    @Test
    @DisplayName("Verify status code while Login Page opening")
    public void verifyLoginPageStatusCode() {
        loginPAgeApiSteps.verifyStatusCodeOfTheLoginPageOpening();
    }
}
