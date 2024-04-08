package com.epam.ui.steps;

import com.epam.ui.pages.LoginPage;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPageSteps extends BaseSteps {
    private final LoginPage loginPage;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPageSteps.class);

    public LoginPageSteps() {
        this.loginPage = new LoginPage();
    }

    @Step
    public void openLoginPage() {
        LOGGER.info("openLoginPage");
        loginPage.navigateTo(loginPage.LOGIN_PAGE_URL);
    }

    @Step
    public void enterUserLogin(String userLogin) {
        LOGGER.info("enterUserLogin: " + userLogin);
        waitUntilElementIsDisplayed(loginPage.getDriver(), loginPage.getLoginPlaceHolder());
        loginPage.getLoginPlaceHolder().sendKeys(userLogin);
    }

    @Step("enterUserPassword: ")
    public void enterUserPassword(String userPassword) {
        LOGGER.info("enterUserPassword: " + userPassword);
        waitUntilElementIsDisplayed(loginPage.getDriver(), loginPage.getPasswordPlaceHolder());
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
        Assertions.assertThat(loginPage.getLoginButton().isDisplayed()).isTrue();
    }
}
