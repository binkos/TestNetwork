package com.example.testnetwork.Fragments.MapsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapsArray {
    @SerializedName("routes")
    @Expose
    private MapsObject[] mapsObjects;

    @SerializedName("status")
    @Expose
    private String status;

    public MapsObject[] getMapsObjects(){
        return mapsObjects;
    }

    public String getStatus(){
        return status;
    }

}
