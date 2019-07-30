package com.example.testnetwork.Fragments.PixabayGallery;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestPixabayGalleryInterface {

    @GET("?key=13108154-2d56f0433792d0d7acfffbe8e&q=yellow+flowers&image_type=photo")
    Call<PictureItems> getPictures();
}
