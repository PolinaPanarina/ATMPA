package com.epam.ui.pages;

import com.epam.services.properties.PropertiesReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage {
    public String LOGIN_PAGE_URL;
    @FindBy(xpath = "//span[contains(., 'Welcome,login to your account')]")
    private List<WebElement> elementsWithWelcomeText;
    @FindBy(xpath = ".//button[text()='Login']")
    private WebElement loginButton;
    @FindBy(xpath = ".//input[@placeholder='Login']")
    private WebElement loginPlaceHolder;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement passwordPlaceHolder;

    public WebElement getLoginButton() {
        return loginButton;
    }


    public WebElement getLoginPlaceHolder() {
        return loginPlaceHolder;
    }

    public WebElement getPasswordPlaceHolder() {
        return passwordPlaceHolder;
    }

    public List<WebElement> getElementsWithWelcomeText() {
        return elementsWithWelcomeText;
    }

    public LoginPage() {
        this.LOGIN_PAGE_URL = PropertiesReader.getProperty(URL_PROPERTY_FILE_NAME, "loginPageUrl");
    }

}
