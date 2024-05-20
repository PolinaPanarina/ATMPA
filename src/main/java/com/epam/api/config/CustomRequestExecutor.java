package com.epam.api.config;

public class CustomRequestExecutor {
    private final RequestConverter converter;

    public CustomRequestExecutor(RequestConverter converter) {
        this.converter = converter;
    }

    public CustomResponse execute(CustomRequest request) {
        return converter.convert(request);
    }
}
