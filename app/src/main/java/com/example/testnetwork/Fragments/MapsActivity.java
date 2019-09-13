package com.example.testnetwork.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.testnetwork.Fragments.MapsAPI.MapsAPIService;
import com.example.testnetwork.Fragments.MapsAPI.MapsArray;
import com.example.testnetwork.Fragments.MapsAPI.MapsLatLng;
import com.example.testnetwork.Fragments.MapsAPI.MapsObject;
import com.example.testnetwork.Fragments.MapsAPI.MapsStep;
import com.example.testnetwork.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    private Double Lang;
    private Double Long;
    private LatLng[] latLngs;
    private static final String KEY = "AIzaSyCMeTWpU-D6PqUHyzjuH3S_xYoI_cFH4qI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        Lang = extras.getDouble("Longitude");
        Long = extras.getDouble("Latitude");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location = new LatLng(Long, Lang);
        mMap.addMarker(new MarkerOptions().position(location).title("Current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
        mMap.getUiSettings().isZoomControlsEnabled();

        Call<MapsArray> mapsArrayCall = MapsAPIService.getInstance().getRequest().getMapsObject(KEY,"Скрыганова 6а","Minsk");

        mapsArrayCall.enqueue(new Callback<MapsArray>() {
            @Override
            public void onResponse(Call<MapsArray> call, Response<MapsArray> response) {


              if (!response.body().getStatus().equals("OK")){
                List<LatLng> mPoints = PolyUtil.decode(response.body().getMapsObjects()[0].getMapsOverviewPolyline().getPoints());
                PolylineOptions mLine = new PolylineOptions();
                LatLngBounds.Builder LatLngBuilder = new LatLngBounds.Builder();
                mLine.width(4f).color(R.color.colorPrimary);

                for (LatLng latLng:mPoints){
                    mLine.add(latLng);
                    LatLngBuilder.include(latLng);
                }

                mMap.addPolyline(mLine);
              }
            }
            @Override
            public void onFailure(Call<MapsArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

        //mMap.addPolyline(new PolylineOptions().add(latLngs));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding1
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}
