package google.example.location.locationexm;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker markerPrueba, markerDraw, infWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        LatLng tadeo = new LatLng(4.6071633, -74.0682254);
        mMap.addMarker(new MarkerOptions().position(tadeo).title("Utadeo").snippet("Esta es la " +
                "universidad tadeo bla bla bla...").icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_playa_64)));

        //El draggable es para arrastrar la ubicacion de la EAN
        LatLng ean = new LatLng(4.6556277, -74.0571568);
        mMap.addMarker(new MarkerOptions().position(ean).draggable(true).title("EAN").snippet("Esta es la " +
                "universidad EAN bla bla bla...").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));


        LatLng nacional = new LatLng(4.6387425, -74.0852378);
        mMap.addMarker(new MarkerOptions().position(nacional).title("Nacional").snippet("Esta es la " +
                "universidad nacional bla bla bla...").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        //Marker distrital, ventana de info mas detallada
        LatLng distrital = new LatLng(4.6135611, -74.0651003);
        infWindow = googleMap.addMarker(new MarkerOptions().position(distrital).title("Distrital")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        //Marker prueba, action click
        LatLng prueba = new LatLng(4.6235611, -74.0751003);
        markerPrueba = googleMap.addMarker(new MarkerOptions().position(prueba).title("Prueba"));

        //Marker escIngenieros, action draggable
        LatLng escIngenieros = new LatLng(4.60971, -74.08175);
        markerDraw = googleMap.addMarker(new MarkerOptions().position(escIngenieros).title("Escuela de ingenieros")
                .draggable(true));

        //Camara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(prueba,10));

        //Habilita eventos cuando se clickea un marcador
        googleMap.setOnMarkerClickListener(this);

        //Habilita eventos cuando se arrastra un marcador
        googleMap.setOnMarkerDragListener(this);

        //Dialog
        googleMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(markerPrueba)){
            String lat, lng;
            lat = Double.toString(marker.getPosition().latitude);
            lng = Double.toString(marker.getPosition().longitude);
            Toast.makeText(this,"Latitud "+lat+", Longitud "+lng,Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //Draw Listener
    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(markerDraw)){
            Toast.makeText(this,"Has comenzado a arrastrar la marca",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(markerDraw)){
            String newTitle = String.format(Locale.getDefault(),
                    getString(R.string.maker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);
            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerDraw)){
            Toast.makeText(this,"Has terminado",Toast.LENGTH_SHORT).show();
        }
        setTitle(R.string.sitios);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(infWindow)){
            DistritalFragment.newInstance(marker.getTitle(),
                    getString(R.string.distritalInfo))
                    .show(getSupportFragmentManager(), null);
        }
    }
}
