package com.example.testnetwork.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testnetwork.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;


public class FragmentGeolocation extends Fragment {
    private boolean requestingLocationUpdates;
    //private boolean requestLastKnownLocation=false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private Location mCurrentLocation=null;
    private LocationCallback mLocationCallback;
    private SettingsClient mSettingClient;

    private final int MY_PERMISSION_RESULT_CODE = 1;
    Button getLastKnownLocationButton;
    Button startLocationUpdatesButton;
    Button stopLocationUpdatesButton;
    TextView currentLocationView;
    Button showLocationOnTheMap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        init();
        createRequest();
        return inflater.inflate(R.layout.geolocation_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        currentLocationView = getView().findViewById(R.id.current_location_view);
        getLastKnownLocationButton = getView().findViewById(R.id.get_last_known_location_button);
        startLocationUpdatesButton= getView().findViewById(R.id.start_location_updates_button);
        stopLocationUpdatesButton = getView().findViewById(R.id.stop_location_updates_button);
        showLocationOnTheMap = getView().findViewById(R.id.open_map);
        initButtons();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_RESULT_CODE:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if (getLastKnownLocation())
                        Toast.makeText(getContext(), "Latitude is " + mCurrentLocation.getLatitude() + " Longitude is" + mCurrentLocation.getLongitude(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(),"FAIL",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @SuppressLint("MissingPermission")
    private boolean getLastKnownLocation(){
        mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(getActivity(),task -> {
            Toast.makeText(getContext(), "CurrentLocation is: "+task.getResult(), Toast.LENGTH_SHORT).show();
            mCurrentLocation = task.getResult();

        });
        return true;
    }

     private void createRequest(){
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(500);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
     }

     private void init(){
         mFusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(getContext());
         mSettingClient = LocationServices.getSettingsClient(getContext());

         mLocationCallback = new LocationCallback(){
             @Override
             public void onLocationResult(LocationResult locationResult) {
                 super.onLocationResult(locationResult);
                 mCurrentLocation = locationResult.getLastLocation();
                 updateLocationUI();
             }
         };
         requestingLocationUpdates = false;

         LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
         mLocationSettingsRequest = builder.build();
     }

     private void initButtons(){
         getLastKnownLocationButton.setOnClickListener((v)->{
             if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                 ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSION_RESULT_CODE);
             }else {
                 if (getLastKnownLocation())
                     Toast.makeText(getContext(),"Latitude is "+mCurrentLocation.getLatitude()+" Longitude is"+ mCurrentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
             }
         });

         startLocationUpdatesButton.setOnClickListener((v -> {
             if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                 ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSION_RESULT_CODE);
             }else {
                 mSettingClient.checkLocationSettings(mLocationSettingsRequest).addOnSuccessListener(getActivity(),task->{
                     Toast.makeText(getContext(), "Started location updates!", Toast.LENGTH_SHORT).show();

                     mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest,mLocationCallback, Looper.myLooper());
                     requestingLocationUpdates = true;
                     updateLocationUI();
                 });
             }

         }));

         stopLocationUpdatesButton.setOnClickListener(v -> {

             mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback).addOnSuccessListener(getActivity(),task->{
                 Toast.makeText(getContext(), "Location updates stopped!", Toast.LENGTH_SHORT).show();
                 requestingLocationUpdates = false;
                 updateLocationUI();
             });

         });

         showLocationOnTheMap.setOnClickListener(v->{
             Intent intent = new Intent(getContext(),MapsActivity.class);
             if (mCurrentLocation!=null){
                 intent.putExtra("Latitude",mCurrentLocation.getLatitude());
                 intent.putExtra("Longitude",mCurrentLocation.getLongitude());
             }
             startActivity(intent);

         });

     }

    @SuppressLint("SetTextI18n")
    private void updateLocationUI() {
        if (mCurrentLocation!=null){
        currentLocationView.setText("Current Location: "+mCurrentLocation.getLatitude()+", "+mCurrentLocation.getLongitude());}
        toggleButtons();
    }

    private void toggleButtons() {
        if (requestingLocationUpdates) {
            startLocationUpdatesButton.setEnabled(false);
            stopLocationUpdatesButton.setEnabled(true);
        } else {
            startLocationUpdatesButton.setEnabled(true);
            stopLocationUpdatesButton.setEnabled(false);
        }
    }


}
