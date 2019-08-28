package com.example.testnetwork.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.testnetwork.Fragments.MapsAPI.MapsAPIService;
import com.example.testnetwork.Fragments.MapsAPI.MapsArray;
import com.example.testnetwork.Fragments.MapsAPI.MapsStep;
import com.example.testnetwork.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Double Lang;
    private Double Long;
    private LatLng[] latLngs;

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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(Long, Lang);
        mMap.addMarker(new MarkerOptions().position(location).title("Current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
        mMap.addPolyline(new PolylineOptions().add(
                location,
                new LatLng(53.910375, 27.426416),
                new LatLng(53.911913, 27.426692),
                new LatLng(53.911904, 27.427415),
                new LatLng(53.911743, 27.427430),
                new LatLng(53.911730, 27.427978),
                new LatLng(53.911446, 27.427963)
        ));

        Call<MapsArray> mapsArrayCall = MapsAPIService.getInstance().getRequest().getMapsObject("Нёманская 6","Янки Мавра 47");

        mapsArrayCall.enqueue(new Callback<MapsArray>() {
            @Override
            public void onResponse(Call<MapsArray> call, Response<MapsArray> response) {

                int i = response.body().getMapsObjects().length;

                Toast.makeText(getApplicationContext(),i+"",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),response.body().getMapsObjects().length,Toast.LENGTH_SHORT).show();

//                mMap.addMarker(new MarkerOptions().position(new LatLng(
//                        response.body().getMapsObjects()[0].getMapsLegs()[0].getMapsSteps()[0].getStart_location().getLat(),
//                        response.body().getMapsObjects()[0].getMapsLegs()[0].getMapsSteps()[0].getStart_location().getLng())
//                ).title("home"));
//                for (int i = 0;i<mapsSteps.length;i++){
//                    latLngs[i] = new LatLng(mapsSteps[i].getStart_location().getLat(),mapsSteps[i].getStart_location().getLng());
//                }
            }
            @Override
            public void onFailure(Call<MapsArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"FAIL",Toast.LENGTH_SHORT).show();
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
