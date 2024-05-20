package com.epam.api.restassured;

import com.epam.api.dto.dashboard.DashboardIdDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseRequests {

    public Response sendGetRequest(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .when().get()
                .then().log().status().extract().response();
    }

    public Response sendPostRequest(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .when().post()
                .then().log().status().extract().response();
    }

    public Response sendDeleteRequest(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .when().delete()
                .then().log().status().extract().response();
    }

    public Response sendPutRequest(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .when().put()
                .then().log().status().extract().response();
    }
}
