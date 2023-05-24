package com.example.mediamaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapGoogle extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        //Adding latitude and longitude
        LatLng location = new LatLng(26,100);

        //Adding red marker to point location
        mMap.addMarker(new MarkerOptions().position(location).title("Unknown"));

        //Moving camera to desired location
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        //Adding zoom effect
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f));
    }
}