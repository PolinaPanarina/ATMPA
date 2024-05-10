package com.epam.api.dto.dashboard;

import java.util.List;

public class GetDashboardByIdResponseDto {
    private String description;
    private String owner;
    private int id;
    private String name;
    private Object widgets;

    public GetDashboardByIdResponseDto() {
    }

    private GetDashboardByIdResponseDto(Builder builder) {
        this.description = builder.description;
        this.owner = builder.owner;
        this.id = builder.id;
        this.name = builder.name;
        this.widgets = builder.widgets;
    }

    public static class Builder {
        private String description;
        private String owner;
        private int id;
        private String name;
        private Object widgets;

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder owner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder widgets(List<String> widgets) {
            this.widgets = widgets;
            return this;
        }

        public GetDashboardByIdResponseDto build() {
            return new GetDashboardByIdResponseDto(this);
        }
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Object getWidgets() {
        return widgets;
    }
}

