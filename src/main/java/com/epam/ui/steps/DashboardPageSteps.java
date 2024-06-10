package com.epam.ui.steps;

import com.epam.ui.pages.DashboardPage;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

public class DashboardPageSteps extends BaseSteps {

    private final DashboardPage dashboardPage;
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardPageSteps.class);

    public DashboardPageSteps() {
        this.dashboardPage = new DashboardPage();
    }

    @Step
    public void verifyDashboardPageOpened(String dashboardName) {
        waitUntilElementHasText(dashboardPage.getDriver(), dashboardPage.getDashboardTitle(), dashboardName);
    }

    @Step
    public void verifyMovingWidgetToTheOtherWidgetPlace(String sourceWidgetName, String targetWidgetName) {
        Point initialSourceLocation = getWidgetByTitle(sourceWidgetName).getLocation();
        Point initialTargetLocation = getWidgetByTitle(targetWidgetName).getLocation();

        Actions actions = new Actions(dashboardPage.getDriver());
        actions.dragAndDrop(getWidgetByTitle(sourceWidgetName), getWidgetByTitle(targetWidgetName)).perform();

        waitUntilElementIsDisplayed(dashboardPage.getDriver(), getWidgetByTitle(sourceWidgetName));
        Point newSourceLocation = getWidgetByTitle(sourceWidgetName).getLocation();

        Assertions.assertThat(initialSourceLocation).isNotEqualTo(newSourceLocation);
        Assertions.assertThat(newSourceLocation).isEqualTo(initialTargetLocation);
    }

    @Step
    public void verifyResizingWidget(String sourceWidgetName) {
        Dimension initialSize = getWidgetByTitle(sourceWidgetName).getSize();

        Actions actions = new Actions(dashboardPage.getDriver());
        actions.clickAndHold(getWidgetByTitle(sourceWidgetName))
                .moveByOffset(100, 200)
                .release()
                .perform();

        Dimension changedSize = getWidgetByTitle(sourceWidgetName).getSize();

        Assertions.assertThat(initialSize).isNotEqualTo(changedSize);
    }

    public WebElement getWidgetByTitle(String title) {
        waitUntilElementIsDisplayed(dashboardPage.getDriver(), dashboardPage.getWidgetGrid().get(0));
        return dashboardPage.getWidgetGrid().stream().filter(widget -> widget.getText().equals(title)).findFirst()
                .orElseThrow(() -> new NoSuchElementException(title + " not found"));
    }
}
