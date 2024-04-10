package com.epam.ui.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GridRow {
    private final WebElement gridRow;

    public GridRow(WebElement webElement) {
        this.gridRow = webElement;
    }

    public List<WebElement> getDashboardNames() {
        return gridRow.findElements(By.xpath("//*[contains(concat(' ', normalize-space(@class), ' '), 'dashboardTable__name')]"));
    }

    public List<WebElement> getDashboardOwners() {
        return gridRow.findElements(By.xpath("//*[contains(concat(' ', normalize-space(@class), ' '), 'dashboardTable__owner')]"));
    }

    public List<WebElement> getDashboardDeleteButton() {
        return gridRow.findElements(By.xpath("//*[contains(concat(' ', normalize-space(@class), ' '), 'icon-delete')]"));
    }

}
