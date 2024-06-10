package com.epam.ui.pages;

import com.epam.services.properties.PropertiesReader;
import com.epam.ui.pages.elements.Menu;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {
    public String DASHBOARD_PAGE_URL;

    @FindBy(xpath = "//li/span")
    private WebElement dashboardTitle;

    @FindBy(xpath = "//div[@class='widgetHeader__widget-name-block--AOAHS']")
    private List<WebElement> widgetGridsByTitle;

    @FindBy(xpath = "//aside[@class='sidebar__sidebar--mc65e']")
    private WebElement menu;

    public List<WebElement> getWidgetGrid() {
        return widgetGridsByTitle;
    }

    public Menu getMenu() {
        return new Menu(menu);
    }

    public WebElement getDashboardTitle() {
        return dashboardTitle;
    }

    public DashboardPage() {
        this.DASHBOARD_PAGE_URL = PropertiesReader.getProperty(URL_PROPERTY_FILE_NAME, "dashboardsPageUrl");
    }
}
