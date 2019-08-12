package com.example.testnetwork.Fragments.MapsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MapsObject {

    @SerializedName("copyrights")
    @Expose
    private String copyrights;

    @SerializedName("legs")
    @Expose
    private MapsLegs[] mapsLegs;


    public String getCopyrights(){
        return copyrights;
    }

    public MapsLegs[] getMapsLegs(){
        return mapsLegs;
    }
}
