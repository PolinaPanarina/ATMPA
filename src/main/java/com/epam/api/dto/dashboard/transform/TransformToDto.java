package com.epam.api.dto.dashboard.transform;

import com.epam.api.dto.dashboard.DashboardIdDto;
import com.epam.api.dto.dashboard.GetDashboardByIdResponseDto;
import com.epam.api.dto.dashboard.ResponseMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TransformToDto {

    public static DashboardIdDto transformToDashboardIdDto(String customResponse) {
        DashboardIdDto dashboardIdDto;
        ObjectMapper objectMapperFromResponse = new ObjectMapper();
        try {
            dashboardIdDto = objectMapperFromResponse.readValue(customResponse, DashboardIdDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dashboardIdDto;
    }

    public static GetDashboardByIdResponseDto transformToGetDashboardByIdResponseDto(String customResponse) {
        GetDashboardByIdResponseDto getDashboardByIdResponseDto;
        ObjectMapper objectMapperFromResponse = new ObjectMapper();
        try {
            getDashboardByIdResponseDto = objectMapperFromResponse.readValue(customResponse,
                    GetDashboardByIdResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getDashboardByIdResponseDto;
    }

    public static ResponseMessageDto transformToResponseMessageDto(String customResponse) {
        ResponseMessageDto responseMessageDto;
        ObjectMapper objectMapperFromResponse = new ObjectMapper();
        try {
            responseMessageDto = objectMapperFromResponse.readValue(customResponse, ResponseMessageDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseMessageDto;
    }
}
