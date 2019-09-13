package com.example.testnetwork.Fragments.MapsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 public class MapsStep {
    @SerializedName("distance")
    @Expose
    private MapsTextValue distanceTextValue;

    @SerializedName("duration")
    @Expose
    private MapsTextValue durationTextValue;

    @SerializedName("start_location")
    @Expose
    private MapsLatLng start_location;

    @SerializedName("end_location")
    @Expose
    private MapsLatLng end_location;

    public MapsTextValue getDistanceTextValue() {
        return distanceTextValue;
    }

    public MapsTextValue getDurationTextValue() {
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
