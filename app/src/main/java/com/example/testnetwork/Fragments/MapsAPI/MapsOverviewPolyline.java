package com.example.testnetwork.Fragments.MapsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapsOverviewPolyline {
    @SerializedName("points")
    @Expose
    private String points;

    public String getPoints() {
        return points;
    }
}
