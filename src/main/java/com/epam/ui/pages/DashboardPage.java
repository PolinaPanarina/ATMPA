package com.epam.ui.pages;

import com.epam.services.properties.PropertiesReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    public String DASHBOARD_PAGE_URL;

    @FindBy(xpath = "//span[text()='All Dashboards']")
    private WebElement allDashboardsTitle;


    public WebElement getAllDashboardsTitle() {
        return allDashboardsTitle;
    }

    public DashboardPage() {
        this.DASHBOARD_PAGE_URL = PropertiesReader.getProperty(URL_PROPERTY_FILE_NAME, "dashboardPageUrl.regexp");
    }
}
