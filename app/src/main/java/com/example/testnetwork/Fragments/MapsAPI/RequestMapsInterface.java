package com.example.testnetwork.Fragments.MapsAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestMapsInterface {

    @GET("directions/json?key=AIzaSyCMeTWpU-D6PqUHyzjuH3S_xYoI_cFH4qI")
    Call<MapsArray> getMapsObject(@Query("origin") String originName,
                                   @Query("destination") String destinationName);
}
