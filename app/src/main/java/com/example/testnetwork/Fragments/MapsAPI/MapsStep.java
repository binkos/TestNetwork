package com.example.testnetwork.Fragments.MapsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class MapsStep {
    @SerializedName("distance")
    @Expose
    private String distanceTextValue;

    @SerializedName("duration")
    @Expose
    private String durationTextValue;

    @SerializedName("start_location")
    @Expose
    private MapsLatLng start_location;

    @SerializedName("end_location")
    @Expose
    private MapsLatLng end_location;

    public String getDistanceTextValue() {
        return distanceTextValue;
    }

    public String getDurationTextValue() {
        return durationTextValue;
    }

    public MapsLatLng getStart_location() {
        return start_location;
    }

    public MapsLatLng getEnd_location() {
        return end_location;
    }

    public String getTravel_mode() {
        return travel_mode;
    }

    @SerializedName("travel_mode")
    @Expose
    private String travel_mode;



}
