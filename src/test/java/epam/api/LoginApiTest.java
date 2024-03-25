package epam.api;

import com.epam.api.steps.LoginPageApiSteps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
