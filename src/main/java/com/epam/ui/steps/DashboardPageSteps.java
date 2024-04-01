package com.epam.ui.steps;

import com.epam.ui.pages.DashboardPage;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPageSteps extends BaseSteps {
    private final DashboardPage dashboardPage;
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardPageSteps.class);

    public DashboardPageSteps() {
        this.dashboardPage = new DashboardPage();
    }

    public void verifyDashboardPageIsOpened() {
        LOGGER.info("verifyDashboardPageIsOpened");
        waitUntilElementIsDisplayed(dashboardPage.getDriver(), dashboardPage.getAllDashboardsTitle());
        Assertions.assertThat(dashboardPage.getAllDashboardsTitle().isDisplayed()).isTrue();
    }
}
