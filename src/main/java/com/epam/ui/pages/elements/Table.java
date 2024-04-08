package com.epam.ui.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Table {
    private final WebElement gridElement;

    public Table(WebElement webElement) {
        this.gridElement = webElement;
    }

    public WebElement getTable() {
        return gridElement.findElement(By.xpath("//*[contains(concat(' ', normalize-space(@class), ' '), 'dashboard-table')]"));
    }

    public GridRow getRows() {
        return new GridRow(gridElement);
    }

}
