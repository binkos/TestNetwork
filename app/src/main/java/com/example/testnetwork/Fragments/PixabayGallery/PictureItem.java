package com.example.testnetwork.Fragments.PixabayGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PictureItem {
    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("largeImageURL")
    @Expose
    private String largeImageURL;

    @SerializedName("likes")
    @Expose
    private int likes;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
