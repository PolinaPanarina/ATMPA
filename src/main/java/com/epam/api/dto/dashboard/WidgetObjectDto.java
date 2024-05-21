package com.epam.api.dto.dashboard;

import java.util.ArrayList;
import java.util.List;

public class WidgetObjectDto {
    private int widgetId = 0;
    private String widgetName = "";
    private List<String> widgetOptions = new ArrayList<>();
    private Position widgetPosition = new Position();
    private Size widgetSize = new Size();
    private String widgetType = "";

    public List<String> getWidgetOptions() {
        return widgetOptions;
    }

    public void setWidgetOptions(List<String> widgetOptions) {
        this.widgetOptions = widgetOptions;
    }

    public int getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public Position getWidgetPosition() {
        return widgetPosition;
    }

    public void setWidgetPosition(Position widgetPosition) {
        this.widgetPosition = widgetPosition;
    }

    public Size getWidgetSize() {
        return widgetSize;
    }

    public void setWidgetSize(Size widgetSize) {
        this.widgetSize = widgetSize;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public class Position {
        private int positionX = 0;
        private int positionY = 0;

        public int getPositionX() {
            return positionX;
        }

        public void setPositionX(int positionX) {
            this.positionX = positionX;
        }

        public int getPositionY() {
            return positionY;
        }

        public void setPositionY(int positionY) {
            this.positionY = positionY;
        }
    }

    public class Size {
        private int height = 0;
        private int width = 0;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
