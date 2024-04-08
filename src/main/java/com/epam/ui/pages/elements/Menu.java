package com.epam.ui.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Menu {
    private final WebElement menu;

    public Menu(WebElement webElement) {
        this.menu = webElement;
    }

    public WebElement getMenu() {
        return menu.findElement(By.xpath("//aside[@class='sidebar__sidebar--mc65e']"));
    }

    public WebElement getDashboardButton() {
        return menu.findElement(By.xpath("//div[@class='projectSelector__project-selector--C4soz']"));
    }

    public WebElement getLaunchButton() {
        return menu.findElement(By.xpath("//div[@class='sidebar__sidebar-btn--DE02C']"));
    }

    public WebElement getProjectButton() {
        return menu.findElement(By.xpath("//div[@class='projectSelector__project-selector--C4soz']"));
    }

    public Projects getProjectsMenu() {
        return new Projects(menu);
    }

}
