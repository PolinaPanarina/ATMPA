package com.epam.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApiSteps {

    @Step
    public Response sendGetResponse(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .when().get()
                .then().log().status().extract().response();
    }
}
