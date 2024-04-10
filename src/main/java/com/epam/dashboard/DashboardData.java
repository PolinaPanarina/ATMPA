package com.epam.dashboard;

public class DashboardData {
    private final String dashboardName;
    private final String owner;

    public String getDashboardName() {
        return dashboardName;
    }

    public String getOwner() {
        return owner;
    }

    public DashboardData(String dashboardName, String owner) {
        this.dashboardName = dashboardName;
        this.owner = owner;
    }
}
