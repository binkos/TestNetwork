package com.example.testnetwork.Fragments.MapsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapsLegs {

    @SerializedName("distance")
    @Expose
    private MapsTextValue distanceTextValue;

    @SerializedName("duration")
    @Expose
    private MapsTextValue durationTextValue;

    @SerializedName("end_address")
    @Expose
    private String end_address;

    @SerializedName("start_address")
    @Expose
    private String start_address;

    @SerializedName("start_location")
    @Expose
    private MapsLatLng start_location;

    @SerializedName("end_location")
    @Expose
    private MapsLatLng end_location;

    @SerializedName("steps")
    @Expose
    private MapsStep[] mapsSteps ;

    public MapsTextValue getDistanceTextValue() {
        return distanceTextValue;
    }

    public MapsTextValue getDurationTextValue() {
        return durationTextValue;
    }

    public String getEnd_address() {
        return end_address;
    }

    public String getStart_address() {
        return start_address;
    }

    public MapsLatLng getStart_location() {
        return start_location;
    }

    public MapsLatLng getEnd_location() {
        return end_location;
    }

    public MapsStep[] getMapsSteps() {
        return mapsSteps;
    }
}
