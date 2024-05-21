package com.epam.ui.steps;

import com.epam.api.dto.dashboard.DashboardDataDto;
import com.epam.ui.pages.DashboardPage;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

public class DashboardPageSteps extends BaseSteps {
    private final DashboardPage dashboardPage;
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardPageSteps.class);

    public DashboardPageSteps() {
        this.dashboardPage = new DashboardPage();
    }

    @Step
    public void verifyDashboardPageIsOpened() {
        LOGGER.info("verifyDashboardPageIsOpened");
        waitUntilElementIsDisplayed(dashboardPage.getDriver(), dashboardPage.getAllDashboardsTitle());
        Assertions.assertThat(dashboardPage.getAllDashboardsTitle().isDisplayed()).isTrue();
    }

    @Step
    public void verifyDashboardIsPresent(DashboardDataDto dashboardData) {
        LOGGER.info("verifyDashboardIsPresent");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThat(dashboardPage.getDashboardTable().getRows().getDashboardNames().stream().map(WebElement::getText).collect(Collectors.toList()))
                .contains(dashboardData.getDashboardName());
        Assertions.assertThat(dashboardPage.getDashboardTable().getRows().getDashboardOwners().stream().map(WebElement::getText).collect(Collectors.toList()))
                .contains(dashboardData.getOwner());
    }

    @Step
    public void verifyFirstSearchedDashboardData(DashboardDataDto dashboardData) {
        LOGGER.info("verifyFirstSearchedDashboardData");
        waitUntilElementIsDisplayed(dashboardPage.getDriver(), dashboardPage.getDashboardTable().getRows().getDashboardNames().get(0));
        Assertions.assertThat(getDashboardNameByIndex(0).getText()).isEqualTo(dashboardData.getDashboardName());
        Assertions.assertThat(getDashboardOwnerByIndex(0).getText()).isEqualTo(dashboardData.getOwner());
    }

    @Step
    public WebElement getDashboardNameByIndex(int index) {
        LOGGER.info("getDashboardNameByIndex");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        waitUntilElementIsDisplayed(dashboardPage.getDriver(), dashboardPage.getDashboardTable().getRows().getDashboardNames().get(0));
        return dashboardPage.getDashboardTable().getRows().getDashboardNames().get(index);
    }

    @Step
    public WebElement getDashboardOwnerByIndex(int index) {
        LOGGER.info("getDashboardOwnerByIndex");
        return dashboardPage.getDashboardTable().getRows().getDashboardOwners().get(index);
    }

    @Step
    public void searchDashboard(String dashboardName) {
        LOGGER.info("searchDashboard: " + dashboardName);
        waitUntilElementIsDisplayed(dashboardPage.getDriver(), dashboardPage.getSearchArea());
        dashboardPage.getSearchArea().sendKeys(dashboardName + Keys.ENTER);
        waitUntilElementIsDisplayed(dashboardPage.getDriver(), dashboardPage.getDashboardTable().getTable());
    }

    @Step
    public void choseProject(String projectName) {
        LOGGER.info("choseProject: " + projectName);
        dashboardPage.getMenu().getProjectButton().click();
        waitUntilElementIsDisplayed(dashboardPage.getDriver(), dashboardPage.getMenu().getProjectsMenu().getProjects().get(0));
        dashboardPage.getMenu().getProjectsMenu().getProject(projectName.toUpperCase()).click();
        waitUntilElementIsDisplayed(dashboardPage.getDriver(), dashboardPage.getAllDashboardsTitle());
    }
}
