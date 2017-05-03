package com.joan.ra1;

import android.location.Location;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationServices;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.OkHttpClient;


public class Mapa extends AppCompatActivity {

    MapView mapaView;
    private MapboxMap mapa;
    private FloatingActionButton btUbicacion;
    private LocationServices servicioUbicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapboxAccountManager.start(this,"pk.eyJ1IjoibDNsdWUiLCJhIjoiY2l3dGJrYWtnMDAwcjJvcXQ1Mm9zbTgyNiJ9.lkySmD8uT-rKwQCTKP9gMg");
        final String titulo1 = getResources().getString(R.string.lb_titulo1);
        final String titulo2 = getResources().getString(R.string.lb_titulo2);
        final String desc1 = getResources().getString(R.string.lb_desc1);
        final String desc2 = getResources().getString(R.string.lb_desc2);
        final String desc3 = getResources().getString(R.string.lb_desc3);
        setContentView(R.layout.activity_mapa);
        mapaView = (MapView) findViewById(R.id.mapaView);
        mapaView.onCreate(savedInstanceState);

        mapaView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapa = mapboxMap;
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(41.648626, -0.886741))
                        .title(titulo1)
                        .snippet(desc1));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(41.635866, -0.896847))
                        .title(titulo2)
                        .snippet(desc2));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(41.653220, -0.866762))
                        .title(titulo1)
                        .snippet(desc3));
            }
        });

        ubicarUsuario();


    }
    private void ubicarUsuario() {

        servicioUbicacion = LocationServices.getLocationServices(this);

        btUbicacion = (FloatingActionButton) findViewById(R.id.btUbicacion);
        btUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapa != null) {
                    Location lastLocation = servicioUbicacion.getLastLocation();
                    if (lastLocation != null)
                        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation), 16));

                    // Resalta la posición del usuario en el mapa
                    mapa.setMyLocationEnabled(true);
                }
            }
        });
    }


    private class TareaDescargarDatos extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {


            return null;
        }
    }
}
