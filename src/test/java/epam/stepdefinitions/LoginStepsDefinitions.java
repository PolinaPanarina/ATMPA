package epam.stepdefinitions;

import com.epam.services.properties.PropertiesReader;
import com.epam.staticdata.enums.PropertiesEnum;
import com.epam.ui.steps.AllDashboardsPageSteps;
import com.epam.ui.steps.LoginPageSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class LoginStepsDefinitions {
    private final LoginPageSteps loginPageSteps = new LoginPageSteps();
    private final AllDashboardsPageSteps dashboardPageSteps = new AllDashboardsPageSteps();

    @DataTableType
    public PropertiesEnum propertiesDataType(String propertyTypeName) {
        return PropertiesEnum.valueOf(propertyTypeName);
    }

    @Given("I am on the login page")
    public void navigateToLoginPage() {
        loginPageSteps.openLoginPage();
    }

    @And("I click the login button")
    public void clickLoginButton() {
        loginPageSteps.clickOnLoginButton();
    }

    @Then("I should be logged in successfully")
    public void verifyLoginSuccess() {
        dashboardPageSteps.verifyDashboardPageIsOpened();
    }

    @When("I enter valid credentials:$")
    public void enterValidCredentials(DataTable dataTable) {
        Map<String, PropertiesEnum> credentials = dataTable.asMap(String.class, PropertiesEnum.class);
        loginPageSteps.enterUserLogin(PropertiesReader.readSecret(credentials.get("username").getValue()));
        loginPageSteps.enterUserPassword(PropertiesReader.readSecret(credentials.get("password").getValue()));
    }

    @When("I enter invalid credentials {string} and {string}")
    public void enterInvalidCredentialsUsernameAndPassword(String username, String password) {
        loginPageSteps.enterUserLogin(username);
        loginPageSteps.enterUserPassword(password);
    }

    @Then("I shouldn't be logged to the system")
    public void verifyUserIsNotLoggedIn() {
        loginPageSteps.verifyThatRPLoginPageIsOpened();
    }

}
