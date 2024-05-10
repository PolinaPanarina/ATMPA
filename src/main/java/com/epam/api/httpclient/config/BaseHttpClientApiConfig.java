package com.epam.api.httpclient.config;

import com.epam.services.properties.PropertiesReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.epam.api.config.RequestSpec.AUTHORIZATION_HEADER;
import static com.epam.staticdata.enums.PropertiesEnum.TOKEN_PROPERTY;

public class BaseHttpClientApiConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseHttpClientApiConfig.class);

    public HttpResponse sendGetRequest(String url) {

        HttpResponse response;
        HttpUriRequest httpUriRequest = RequestBuilder.get(url)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReader.readSecret(TOKEN_PROPERTY.getValue()))
                .addHeader("Content-Type", "application/json")
                .build();
        LOGGER.info("send GET:" + httpUriRequest);

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            response = httpClient.execute(httpUriRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public HttpResponse sendGetById(String url, int dashboardId) {
        HttpResponse response;
        HttpUriRequest httpUriRequest = RequestBuilder.get(url + '/' + dashboardId)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReader.readSecret(TOKEN_PROPERTY.getValue()))
                .addHeader("Content-Type", "application/json")
                .build();
        LOGGER.info("send GET by ID :" + httpUriRequest);
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            response = httpClient.execute(httpUriRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public HttpResponse sendPostRequest(String url, String body) {
        HttpResponse response;
        StringEntity entity;
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(AUTHORIZATION_HEADER, PropertiesReader.readSecret(TOKEN_PROPERTY.getValue()));
        httpPost.addHeader("Content-Type", "application/json");

        LOGGER.info("send POST:" + httpPost);
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            entity = new StringEntity(body);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public HttpResponse senDeleteRequest(String url, int dashboardId) {
        HttpResponse response;
        HttpDelete httpDelete = new HttpDelete(url + '/' + dashboardId);
        httpDelete.addHeader(AUTHORIZATION_HEADER, PropertiesReader.readSecret(TOKEN_PROPERTY.getValue()));
        httpDelete.addHeader("Content-Type", "application/json");
        LOGGER.info("send DELETE:" + httpDelete);

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            response = httpClient.execute(httpDelete);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public HttpResponse sendPutRequest(String url, String body, int dashboardId) {
        HttpResponse response;
        StringEntity entity;
        HttpPut httpPut = new HttpPut(url + '/' + dashboardId);
        httpPut.addHeader(AUTHORIZATION_HEADER, PropertiesReader.readSecret(TOKEN_PROPERTY.getValue()));
        httpPut.addHeader("Content-Type", "application/json");
        LOGGER.info("send PUT:" + httpPut);

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            entity = new StringEntity(body);
            httpPut.setEntity(entity);
            response = httpClient.execute(httpPut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
