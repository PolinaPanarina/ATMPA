package com.epam.api.steps;

import com.epam.api.dto.dashboard.DashboardDataDto;
import com.epam.api.dto.dashboard.GetDashboardByIdResponseDto;
import io.qameta.allure.Step;

import static com.epam.api.dto.dashboard.transform.TransformToDto.transformToGetDashboardByIdResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DataValidationApiSteps {

    @Step
    public void validateCreatedDashboard(String responseBody, GetDashboardByIdResponseDto getDashboardByIdResponseDto) {
        GetDashboardByIdResponseDto responseDto = transformToGetDashboardByIdResponseDto(responseBody);
        assertThat(responseDto.getDescription()).contains(getDashboardByIdResponseDto.getDescription());
        assertThat(responseDto.getId()).isEqualTo(getDashboardByIdResponseDto.getId());
        assertThat(responseDto.getOwner()).contains(getDashboardByIdResponseDto.getOwner());
    }
}
