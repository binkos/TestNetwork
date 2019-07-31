package com.example.testnetwork.Fragments.PixabayGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PictureItems {

    @SerializedName("hits")
    @Expose
    private PictureItem[] pictures;

    public PictureItem[] getPictures(){
        return pictures;
    };

}
