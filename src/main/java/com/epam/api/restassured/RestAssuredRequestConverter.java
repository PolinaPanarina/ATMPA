package com.epam.api.restassured;

import com.epam.api.config.CustomRequest;
import com.epam.api.config.CustomResponse;
import com.epam.api.config.RequestConverter;
import com.epam.staticdata.enums.HttpMethods;
import io.restassured.response.Response;

public class RestAssuredRequestConverter implements RequestConverter {

    @Override
    public CustomResponse convert(CustomRequest request) {
        BaseRequests baseRequests = new BaseRequests();
        HttpMethods method = request.getMethod();
        String url = request.getUrl();
        Object body = request.getBody();
        CustomResponse customResponse = new CustomResponse();
        Response response = switch (method) {
            case GET -> baseRequests.sendGetRequest(RequestSpec.buildWithUrl(url));
            case POST -> baseRequests.sendPostRequest(RequestSpec.buildWithBody(url, body));
            case PUT -> baseRequests.sendPutRequest(RequestSpec.buildWithBodyAndDasboardId(url, body));
            case DELETE -> baseRequests.sendDeleteRequest(RequestSpec.buildWithDasboardId(url));
            default -> throw new RuntimeException("Request method not specified or wrong: " + method);
        };
        customResponse.setStatusCode(response.getStatusCode());
        customResponse.setResponseBody(response.getBody().asPrettyString());
        return customResponse;
    }
}
