package com.epam.api.config;

import com.epam.staticdata.enums.HttpMethods;

public class UnifiedRequests {

    public static CustomResponse sendGet(RequestConverter requestConverter, String url) {
        CustomRequest req = new CustomRequest.CustomRequestBuilder()
                .url(url)
                .method(HttpMethods.GET)
                .build();

        return new CustomRequestExecutor(requestConverter).execute(req);
    }

    public static CustomResponse sendPost(RequestConverter requestConverter, String url, Object body) {
        CustomRequest req = new CustomRequest.CustomRequestBuilder()
                .url(url)
                .method(HttpMethods.POST)
                .body(body)
                .build();

        return new CustomRequestExecutor(requestConverter).execute(req);
    }

    public static CustomResponse sendPut(RequestConverter requestConverter, String url, Object body) {
        CustomRequest req = new CustomRequest.CustomRequestBuilder()
                .url(url)
                .method(HttpMethods.PUT)
                .body(body)
                .build();

        return new CustomRequestExecutor(requestConverter).execute(req);
    }

    public static CustomResponse sendDelete(RequestConverter requestConverter, String url) {
        CustomRequest req = new CustomRequest.CustomRequestBuilder()
                .url(url)
                .method(HttpMethods.DELETE)
                .build();

        return new CustomRequestExecutor(requestConverter).execute(req);
    }
}
