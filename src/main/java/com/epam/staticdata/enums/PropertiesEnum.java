package com.epam.staticdata.enums;

public enum PropertiesEnum {
    USER_NAME_PROPERTY("testUsername"),
    PASSWORD_PROPERTY("testPassword"),
    BROWSER("browser"),
    RP_API_DASHBOARD_PATH("reportPortalDashboardApiPath"),
    RP_API_DASHBOARD_PATH_N("reportPortalDashboardApiPathN"),
    RP_LOGIN_API_PATH("reportPortalLoginPage"),
    RP_PROJECT_NAME("projectName"),
    TOKEN_PROPERTY("token");

    final String value;

    PropertiesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
