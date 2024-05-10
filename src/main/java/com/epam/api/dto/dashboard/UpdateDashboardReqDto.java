package com.epam.api.dto.dashboard;

public class UpdateDashboardReqDto {
    private String description;
    private String name;
    private WidgetObjectDto updateWidgets;

    public UpdateDashboardReqDto(String description, String name, WidgetObjectDto updateWidgets) {
        setDescription(description);
        setName(name);
        setUpdateWidgets(updateWidgets);
    }

    public UpdateDashboardReqDto(String name) {
        setName(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WidgetObjectDto getUpdateWidgets() {
        return updateWidgets;
    }

    public void setUpdateWidgets(WidgetObjectDto updateWidgets) {
        this.updateWidgets = updateWidgets;
    }
}
