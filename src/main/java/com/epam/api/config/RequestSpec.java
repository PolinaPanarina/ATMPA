package com.epam.api.config;

import com.epam.services.properties.PropertiesReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static com.epam.staticdata.enums.PropertiesEnum.*;

public class RequestSpec {
    public static final String API_PROPERTIES_FILE_NAME = "api.properties";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final Map<String, String> AUTH_MAP = Map.of(AUTHORIZATION_HEADER, PropertiesReader
            .readSecret(TOKEN_PROPERTY.getValue()));


    private RequestSpec() {
        throw new IllegalStateException("Utility class");
    }

    public static RequestSpecification buildSimpleLoginSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(String.format(PropertiesReader.getProperty(API_PROPERTIES_FILE_NAME,
                        RP_LOGIN_API_PATH.getValue()), "/dashboard"))
                .setContentType("application/json")
                .build().log().all();
    }

    public static RequestSpecification buildWithUrl(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeaders(AUTH_MAP)
                .setContentType("application/json")
                .build().log().all();
    }

    public static RequestSpecification buildWithBody(String url, Object obj) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeaders(AUTH_MAP)
                .setContentType("application/json")
                .setBody(obj)
                .build().log().all();
    }

    public static RequestSpecification buildWithDasboardId(String url, int id) {
        return new RequestSpecBuilder()
                .setBaseUri(url + "/" + id)
                .addHeaders(AUTH_MAP)
                .setContentType("application/json")
                .build().log().all();
    }

    public static RequestSpecification buildWithBodyAndDasboardId(String url, int id, Object obj) {
        return new RequestSpecBuilder()
                .setBaseUri(url + "/" + id)
                .addHeaders(AUTH_MAP)
                .setBody(obj)
                .setContentType("application/json")
                .build().log().all();
    }
}
