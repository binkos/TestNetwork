package com.example.testnetwork.Fragments.MapsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapsLatLng {
    @SerializedName("lat")
    @Expose
    private double lat;

    @SerializedName("lng")
    @Expose
    private double lng;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
