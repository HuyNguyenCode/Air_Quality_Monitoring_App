package com.ninegroup.weather.api;

public class DatapointRequest {
    private String type;
    private String fromTimestamp;
    private String toTimestamp;
    private Integer amountOfPoints;

    public DatapointRequest(String type, String fromTimestamp,
                            String toTimestamp, Integer amountOfPoints) {
        this.type = type;
        this.fromTimestamp = fromTimestamp;
        this.toTimestamp = toTimestamp;
        this.amountOfPoints = amountOfPoints;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromTimestamp() {
        return fromTimestamp;
    }

    public void setFromTimestamp(String fromTimestamp) {
        this.fromTimestamp = fromTimestamp;
    }

    public String getToTimestamp() {
        return toTimestamp;
    }

    public void setToTimestamp(String toTimestamp) {
        this.toTimestamp = toTimestamp;
    }

    public Integer getAmountOfPoints() {
        return amountOfPoints;
    }

    public void setAmountOfPoints(Integer amountOfPoints) {
        this.amountOfPoints = amountOfPoints;
    }
}
