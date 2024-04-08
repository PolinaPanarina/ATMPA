package com.epam.ui.pages;

import com.epam.services.properties.PropertiesReader;
import com.epam.ui.pages.elements.Menu;
import com.epam.ui.pages.elements.Table;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    public String DASHBOARD_PAGE_URL;

    @FindBy(xpath = "//span[text()='All Dashboards']")
    private WebElement allDashboardsTitle;

    @FindBy(xpath = "//div[@class='addDashboardButton__add-dashboard-btn--acseh']")
    private WebElement addNewDashboardButton;

    @FindBy(xpath = "//*[contains(concat(' ', normalize-space(@class), ' '), 'dashboard-table')]")
    private WebElement dashboardTable;

    @FindBy(xpath = "//input[@placeholder='Search by name']")
    private WebElement searchArea;

    @FindBy(xpath = "//div[@class='gridRow__grid-row-wrapper--xj8DG']")
    private WebElement table;

    @FindBy(xpath = "//aside[@class='sidebar__sidebar--mc65e']")
    private WebElement menu;

    public Table getDashboardTable() {
        return new Table(dashboardTable);
    }

    public Menu getMenu() {
        return new Menu(menu);
    }

    public WebElement getAllDashboardsTitle() {
        return allDashboardsTitle;
    }

    public WebElement getAddNewDashboardButton() {
        return addNewDashboardButton;
    }

    public WebElement getSearchArea() {
        return searchArea;
    }

    public DashboardPage() {
        this.DASHBOARD_PAGE_URL = PropertiesReader.getProperty(URL_PROPERTY_FILE_NAME, "dashboardPageUrl");
    }
}
