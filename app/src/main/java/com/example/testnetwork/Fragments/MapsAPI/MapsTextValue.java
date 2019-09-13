package com.example.testnetwork.Fragments.MapsAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapsTextValue {

    @SerializedName("text")
    @Expose
    String text;

    @SerializedName("value")
    @Expose
    int value;

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }
}
