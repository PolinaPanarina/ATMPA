package com.epam.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    public static final String DASHBOARD_PAGE_URL = "https://reportportal.epam.com/ui/#{1}/dashboard";

    @FindBy(xpath = "//span[text()='All Dashboards']")
    private WebElement allDashboardsTitle;


    public WebElement getAllDashboardsTitle() {
        return allDashboardsTitle;
    }

    public DashboardPage() {
        super();
    }
}
