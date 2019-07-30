package com.example.testnetwork.Fragments.PixabayGallery;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryAPIService {

    private static GalleryAPIService ourInstance;
    private static final String BaseURL ="https://pixabay.com/api/";
    private static Retrofit mRetrofit;

    public static GalleryAPIService getInstance() {
        if (ourInstance==null){
            ourInstance = new GalleryAPIService();
        }
        return ourInstance;
    }

    private GalleryAPIService(){
        mRetrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public RequestPixabayGalleryInterface getRequest(){
        return mRetrofit.create(RequestPixabayGalleryInterface.class);
    }



}
