package com.epam.testData;

import com.epam.api.dto.dashboard.DashboardDataDto;
import com.epam.services.properties.PropertiesReader;

import static com.epam.staticdata.enums.PropertiesEnum.PASSWORD_PROPERTY;
import static com.epam.staticdata.enums.PropertiesEnum.USER_NAME_PROPERTY;

public class DashboardDataProvider {
    public static final String PROPERTY_FILE_NAME = "data.properties";

    public static Object[][] loginData() {
        return new Object[][]{
                {PropertiesReader.readSecret(USER_NAME_PROPERTY.getValue()), PropertiesReader.readSecret(PASSWORD_PROPERTY.getValue())},
                {PropertiesReader.readSecret(USER_NAME_PROPERTY.getValue()), PropertiesReader.readSecret(PASSWORD_PROPERTY.getValue())},
        };
    }

    @org.testng.annotations.DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {
        return new Object[][]{
                {"A", "B"},
                {"B", "B"},
                {"C", "B"},
        };
    }

    @org.testng.annotations.DataProvider(name = "dashboardData")
    public static Object[][] dashboardData() {
        return new Object[][]{
                {new DashboardDataDto(PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardName1"),
                        PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardOwner1"))},
                {new DashboardDataDto(PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardName2"),
                        PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardOwner2"))},
                {new DashboardDataDto(PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardName3"),
                        PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardOwner2"))}
        };
    }

    @org.testng.annotations.DataProvider(name = "incorrectDashboardData")
    public static Object[][] incorrectDashboardData() {
        return new Object[][]{
                {new DashboardDataDto("1", "polina")},
                {new DashboardDataDto("5", "polina_test1238955444")},
                {new DashboardDataDto("3", "po")},
                {new DashboardDataDto("5", "23")},
                {new DashboardDataDto("8", "test")},
        };
    }

    public static Object[][] demoData() {
        return new Object[][]{
                {new DashboardDataDto(PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardName1"),
                        PropertiesReader.getProperty(PROPERTY_FILE_NAME, "dashboardOwner1"))}
        };
    }
}
