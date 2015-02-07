package com.mw.android.mapactivity.mapactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.HashMap;
import java.util.Map;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Double[] polygon = {28.54752, 77.27201, 28.54865, 77.27401, 28.54829, 77.27422, 28.54825, 77.27447, 28.54363, 77.27268, 28.54350, 77.27257,
            28.54346, 77.27234, 28.54416, 77.26998, 28.54505, 77.27001, 28.54498, 77.27244, 28.54531, 77.27292, 28.54680, 77.27291, 28.54346, 77.27235};

    Double[] points = {28.54435, 77.27272, 28.54704, 77.27359, 28.54754, 77.27390, 28.54804, 77.27332,
            28.54747, 77.27290, 28.54754, 77.27353, 28.54695, 77.27327, 28.54619, 77.27330,
            28.54395, 77.27248, 28.54420, 77.27071};

    private CameraPosition collegeCenter = new CameraPosition.Builder().target(new LatLng(28.54650, 77.27110)).zoom(16).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //setUpMapIfNeeded();
        createMapView();
        addMarker();
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
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
                                                              Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_LONG).show();
                                                              Intent student_ctr = new Intent(MapsActivity.this, Student_Centre.class);
                                                              startActivity(student_ctr);

                                                          }

                                                      }
                                                  }

                );
                mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(polygon[0], polygon[1]), new LatLng(polygon[2], polygon[3]), new LatLng(polygon[4], polygon[5]), new LatLng(polygon[6], polygon[7]),
                                new LatLng(polygon[8], polygon[9]), new LatLng(polygon[10], polygon[11]), new LatLng(polygon[12], polygon[13]), new LatLng(polygon[14], polygon[15]),
                                new LatLng(polygon[16], polygon[17]), new LatLng(polygon[18], polygon[19]), new LatLng(polygon[16], polygon[17]), new LatLng(polygon[18], polygon[19]),
                                new LatLng(polygon[20], polygon[21]), new LatLng(polygon[22], polygon[23]))
                        .fillColor(Color.TRANSPARENT));


            }
        }
    }
}