package com.epam.api.httpclient;

import com.epam.api.config.CustomRequest;
import com.epam.api.config.CustomResponse;
import com.epam.api.config.RequestConverter;
import com.epam.staticdata.enums.HttpMethods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientRequestConverter implements RequestConverter {

    @Override
    public CustomResponse convert(CustomRequest request) {
        BaseHttpClientRequestConfig baseHttpClientRequestConfig = new BaseHttpClientRequestConfig();
        HttpMethods method = request.getMethod();
        String url = request.getUrl();
        Object body = request.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpResponse response;
        CustomResponse customResponse = new CustomResponse();
        try {
            response = switch (method) {
                case GET -> baseHttpClientRequestConfig.sendGetRequest(url);
                case POST -> baseHttpClientRequestConfig.sendPostRequest(url, objectMapper.writeValueAsString(body));
                case PUT -> baseHttpClientRequestConfig.sendPutRequest(url, objectMapper.writeValueAsString(body));
                case DELETE -> baseHttpClientRequestConfig.senDeleteRequest(url);
            };
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        customResponse.setStatusCode(response.getStatusLine().getStatusCode());
        try {
            customResponse.setResponseBody(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return customResponse;
    }
}
