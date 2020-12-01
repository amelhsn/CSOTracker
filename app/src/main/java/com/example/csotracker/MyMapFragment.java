package com.example.csotracker;

import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

    public class MyMapFragment extends SupportMapFragment implements OnMapReadyCallback {

        private GoogleMap googleMap;

        public MyMapFragment()  {
            getMapAsync(this);
        }

        @Override
        public void onMapReady(final GoogleMap gmap) {
            this.googleMap = gmap;

            // Set default position
           // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            LatLng paris = new LatLng(48.856614, 2.3522219); // lat et long
            this.googleMap.addMarker(new MarkerOptions().position(paris).title("Marker in PARIS"));
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(paris));

            this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(latLng.latitude + " : "+ latLng.longitude);
                    // Clear previously click position.
                    googleMap.clear();
                    // Zoom the Marker
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    // Add Marker on Map
                    googleMap.addMarker(markerOptions);
                }
            });
        }
    }
