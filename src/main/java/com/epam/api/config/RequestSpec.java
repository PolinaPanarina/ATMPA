package com.epam.api.config;

import com.epam.services.properties.PropertiesReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.epam.staticdata.enums.PropertiesEnum.*;

public class RequestSpec {
    public static final String API_PROPERTIES_FILE_NAME = "api.properties";
    public static final String RP_API_BASE_URL = PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME, RP_API_BASE_PATH.getValue());
    public static final String RP_PROJECT = PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME, RP_PROJECT_NAME.getValue());
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private RequestSpec() {
        throw new IllegalStateException("Utility class");
    }

    public static RequestSpecification buildSimpleLoginSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME, RP_LOGIN_API_PATH.getValue()))
                .setContentType("application/json")
                .build().log().all();
    }
}
