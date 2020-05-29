package com.jonathan.jonathan;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btn;
    EditText txt;
    Double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txt = (EditText) findViewById(R.id.txt);
        btn = (Button) findViewById(R.id.btn);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng punto = new LatLng(19.4319716, -99.1356141);
        //mMap.addMarker(new MarkerOptions().position(punto).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(punto, 13));
    }

    public void Pintar(View v) {
        String coords = txt.getText().toString();
        if (coords == "") {
            Toast.makeText(getApplicationContext(), "No hay coordenadas", Toast.LENGTH_LONG).show();
        } else {
            String[] latlon = coords.split(",");
            int tam = latlon.length / 2;
            int cont = 0, indice = 0;
            //Toast.makeText(getApplicationContext(), ""+tam, Toast.LENGTH_LONG).show();
            while (indice < tam) {
                lat = Double.parseDouble(latlon[cont]);
                lon = Double.parseDouble(latlon[cont + 1]);
                LatLng punto = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(punto).title("Punto"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(punto, 13));
                txt.setText("");
                cont = cont + 2;
                indice++;
            }
        }
    }
}