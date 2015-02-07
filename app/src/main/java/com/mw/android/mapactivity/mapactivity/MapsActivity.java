package com.mw.android.mapactivity.mapactivity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    float zoomlevel;
    Double[] points = {28.54435, 77.27272, 28.54704, 77.27359, 28.54754, 77.27390, 28.54804, 77.27332,
            28.54747, 77.27290, 28.54754, 77.27353, 28.54695, 77.27327, 28.54619, 77.27330,
            28.54395, 77.27248, 28.54420, 77.27071};

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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
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
            HashMap<LatLng, String> hashMap = new HashMap<LatLng, String>();
            hashMap.put(new LatLng(points[0], points[1]), "Academic Building");
            hashMap.put(new LatLng(points[2], points[3]), "Girls Hostel");
            hashMap.put(new LatLng(points[4], points[5]), "Boys Hostel");
            hashMap.put(new LatLng(points[6], points[7]), "Tennis Court");
            hashMap.put(new LatLng(points[8], points[9]), "Football Field");
            hashMap.put(new LatLng(points[10], points[11]), "Badminton Court 1");
            hashMap.put(new LatLng(points[12], points[13]), "Badminton Court 2");
            hashMap.put(new LatLng(points[14], points[15]), "Student Centre");
            hashMap.put(new LatLng(points[16], points[17]), "Library Building");
            hashMap.put(new LatLng(points[18], points[19]), "Faculty Building");
            /*hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
            hashMap.put(new LatLng(),"");
*/
            for (Map.Entry<LatLng, String> entry : hashMap.entrySet()) {
                LatLng x = (LatLng) entry.getKey();
                //String y =(LatLng)entry.getValue();

                mMap.addMarker(new MarkerOptions()
                                .position(x)
                                .title(entry.getValue())
                                .draggable(true)
                );
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                                      @Override
                                                      public void onInfoWindowClick(Marker marker) {

                                                          if (marker.getTitle().equals("Student Centre")) {
                                                              Toast.makeText(MapsActivity.this, marker.getTitle(), 1000).show();
                                                              Intent student_ctr = new Intent(MapsActivity.this,Student_Centre.class);
                                                              startActivity(student_ctr);

                                                          }

                                                      }
                                                  }

                );

            }
        }
    }
}