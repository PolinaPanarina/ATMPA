package com.epam.api.steps;

import com.epam.api.dto.dashboard.DashboardDataDto;
import com.epam.api.dto.dashboard.GetDashboardByIdResponseDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class DataValidationApiSteps {

    @Step
    public void validateData(Response resp, DashboardDataDto dashboardData) {
        String responseBody = resp.getBody().asString();
        assertThat(responseBody).contains(dashboardData.getDashboardName());
        assertThat(responseBody).contains(dashboardData.getOwner());
    }

    @Step
    public void validateCreatedDashboard(Response resp, GetDashboardByIdResponseDto getDashboardByIdResponseDto) {
        GetDashboardByIdResponseDto responseDto = resp.as(GetDashboardByIdResponseDto.class);
        assertThat(responseDto.getDescription()).contains(getDashboardByIdResponseDto.getDescription());
        assertThat(responseDto.getId()).isEqualTo(getDashboardByIdResponseDto.getId());
        assertThat(responseDto.getOwner()).contains(getDashboardByIdResponseDto.getOwner());
    }
}
