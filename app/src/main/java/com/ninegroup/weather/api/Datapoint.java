package com.ninegroup.weather.api;

import com.google.gson.annotations.SerializedName;

public class Datapoint {
    @SerializedName("x")
    public Long x;
    @SerializedName("y")
    public Float y;
    //private ArrayList<DatapointInfo> datapointList = new ArrayList<>();

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

//
//    //getter and setters
//    public ArrayList<DatapointInfo> getDatapointList() {
//        return datapointList;
//    }
}

