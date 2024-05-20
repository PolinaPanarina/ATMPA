package com.epam.api.config;

import com.epam.staticdata.enums.HttpMethods;

public class CustomRequest<T> {
    private String url;
    private HttpMethods method;
    private Object body;

    private CustomRequest(CustomRequestBuilder builder) {
        this.url = builder.url;
        this.body = builder.body;
        this.method = builder.method;
    }

    public static class CustomRequestBuilder {
        private String url;
        private HttpMethods method;
        private Object body;

        public CustomRequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public CustomRequestBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public CustomRequestBuilder method(HttpMethods method) {
            this.method = method;
            return this;
        }

        public CustomRequest build() {
            return new CustomRequest(this);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public HttpMethods getMethod() {
        return method;
    }

    public void setMethod(HttpMethods method) {
        this.method = method;
    }
}
