package com.epam.ui.steps;

import com.epam.ui.pages.LoginPage;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.epam.ui.pages.LoginPage.LOGIN_PAGE_URL;

public class LoginPageSteps extends BaseSteps {
    private final LoginPage loginPage;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPageSteps.class);

    public LoginPageSteps() {
        this.loginPage = new LoginPage();
    }

    @Step
    public void openLoginPage() {
        LOGGER.info("openLoginPage");
        loginPage.navigateTo(LOGIN_PAGE_URL);
    }

    @Step
    public void enterUserLogin(String userLogin) {
        LOGGER.info("enterUserLogin: " + userLogin);
        loginPage.getLoginPlaceHolder().sendKeys(userLogin);
    }

    @Step("enterUserPassword: ")
    public void enterUserPassword(String userPassword) {
        LOGGER.info("enterUserPassword: " + userPassword);
        loginPage.getPasswordPlaceHolder().sendKeys(userPassword);
    }

    @Step("clickOnLoginButton")
    public void clickOnLoginButton() {
        LOGGER.info("clickOnLoginButton");
        loginPage.getLoginButton().click();
    }

    @Step("verifyThatRPLoginPageIsOpened")
    public void verifyThatRPLoginPageIsOpened() {
        LOGGER.info("verifyThatRPLoginPageIsOpened");
        Assertions.assertThat(loginPage.getElementsWithWelcomeText()).allSatisfy(WebElement::isDisplayed);
    }
}
