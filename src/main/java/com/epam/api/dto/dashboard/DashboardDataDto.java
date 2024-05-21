package com.epam.api.dto.dashboard;

public class DashboardDataDto {
    private final String dashboardName;
    private final String owner;

    public String getDashboardName() {
        return dashboardName;
    }

    public String getOwner() {
        return owner;
    }

    public DashboardDataDto(String dashboardName, String owner) {
        this.dashboardName = dashboardName;
        this.owner = owner;
    }
}
