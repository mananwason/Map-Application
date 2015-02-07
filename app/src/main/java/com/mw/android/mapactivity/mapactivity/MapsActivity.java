package com.mw.android.mapactivity.mapactivity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    float zoomlevel;
    LatLng centre = new LatLng(28.54435, 77.27272);
    LatLng girls_hostel = new LatLng(28.54704, 77.27359);
    LatLng boys_hostel = new LatLng(28.54754, 77.27390);

    private CameraPosition collegeCenter = new CameraPosition.Builder().target(centre).zoom(16).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //setUpMapIfNeeded();
        createMapView();
        addMarker();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
        createMapView();
        addMarker();
    }

    private void setUpMap() {

        //mMap.addMarker(new MarkerOptions().position(centre).title("Marker"));
    }

    private void createMapView() {

        try {
            if (null == mMap) {
                mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(collegeCenter));
                mMap.getUiSettings().setZoomControlsEnabled(false);
                if (null == mMap) {
                    Toast.makeText(getApplicationContext(), "Error Creating Map", Toast.LENGTH_SHORT).show();

                }
            }
        } catch (NullPointerException e) {
            Log.e("mapApp", e.toString());
        }
        zoomlevel = mMap.getCameraPosition().zoom;
        Log.e("mapZoom", String.valueOf(zoomlevel));

    }

    private void addMarker() {
        if (null != mMap) {
            mMap.addMarker(new MarkerOptions()
                            .position(centre)
                            .title("Academic Building")
                            .draggable(true)
            );
            mMap.addMarker(new MarkerOptions()
                            .position(girls_hostel)
                            .title("Girls Hostel")
                            .draggable(true)
            );
            mMap.addMarker(new MarkerOptions()
                            .position(boys_hostel)
                            .title("Boys Hostel")
                            .draggable(true)
            );

        }
    }
}
