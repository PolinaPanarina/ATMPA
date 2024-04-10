package com.epam.ui.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class Projects {
    private final WebElement projects;

    public Projects(WebElement webElement) {
        this.projects = webElement;
    }

    public List<WebElement> getProjects() {
        return projects.findElements(By
                .xpath("//div[@class='projectSelector__projects-list--EKkEN' and @data-placement='right-end']//a[@href]"));
    }

    public WebElement getProject(String projectName) {
        return getProjects().stream()
                .filter(pr -> pr.getText().equals(projectName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No element found with the provided project name."));
    }

    public void clickOnProjectButton() {
        projects.click();
    }
}
