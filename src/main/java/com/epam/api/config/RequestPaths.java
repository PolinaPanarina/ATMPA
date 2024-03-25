package com.epam.api.config;

public enum RequestPaths {
    ALL_DASHBOARDS("dashboard");
    private final String requestPath;

    RequestPaths(String path) {
        this.requestPath = path;
    }

    public String getPath() {
        return requestPath;
    }
}
