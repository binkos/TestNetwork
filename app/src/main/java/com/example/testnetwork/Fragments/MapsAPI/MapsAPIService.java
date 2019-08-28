package com.example.testnetwork.Fragments.MapsAPI;

import com.example.testnetwork.Fragments.PixabayGallery.GalleryAPIService;
import com.example.testnetwork.Fragments.PixabayGallery.RequestPixabayGalleryInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsAPIService  {

    private static MapsAPIService ourInstance;
    private static final String BaseURL = "https://maps.googleapis.com/maps/api/";
    private static Retrofit mMapsRetrofit;

    Gson gson = new GsonBuilder().setLenient().create();

    public static MapsAPIService getInstance() {
        if (ourInstance == null) {
            ourInstance = new MapsAPIService();
        }
        return ourInstance;
    }

    private MapsAPIService() {
        mMapsRetrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public RequestMapsInterface getRequest() {

    return mMapsRetrofit.create(RequestMapsInterface.class);
    }
}