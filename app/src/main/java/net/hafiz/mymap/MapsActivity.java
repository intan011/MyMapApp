package net.hafiz.mymap;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.hafiz.mymap.databinding.ActivityMapsBinding;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    MarkerOptions marker;
    Vector<MarkerOptions> markerOptions;

    LatLng perlis;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        markerOptions = new Vector<>();
        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4412,100.1913))
                .title("Hospital Tuanku Fauziah")
                .snippet("Status: Buka")
        );

        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4335,100.1864))
                .title("Hospital Pakar KPJ")
                .snippet("Status: Buka")
        );

        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4161,100.1825))
                .title("MEDIKLINIK RAKYAT DR NAIM AHMAD")
                .snippet("Status: Buka")
        );
        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4467,100.2799))
                .title("Unit Kesihatan Klinik UiTM")
                .snippet("Status: Buka")
        );

        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4656,100.2737))
                .title("Klinik Kesihatan Kampung Gial")
                .snippet("Status: Buka")
        );

        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4326,100.2707))
                .title("Klinik Kesihatan Arau")
                .snippet("Status: Buka")
        );

        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4772,100.2586))
                .title("U.n.i KLINIK Mata Ayer Perlis")
                .snippet("Status: Buka")
        );

        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4456,100.2381))
                .title("Klinik Kesihatan Jejawi")
                .snippet("Status: Buka")
        );

        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4452,100.2359))
                .title("Poliklinik DrAzhar Dan Rakan-rakan Cawangan Jejawi")
                .snippet("Status: Buka")
        );

        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4448,100.2353))
                .title("Klinik Megah")
                .snippet("Status: Buka")
        );

        markerOptions.add(new MarkerOptions()
                .position(new LatLng(6.4528,100.2060))
                .title("Poliklinik Perubatan Dr. Suraya")
                .snippet("Status: Buka")
        );

        perlis = new LatLng(6.5170,100.2152);
        marker = new MarkerOptions().position(perlis).title("Perlis").snippet("Cawangan di buka 7am-9pm");

        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the main page
                startActivity(new Intent(MapsActivity.this, MainActivity.class));
                finish(); // Optional: finish the current activity
            }
        });
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
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        mMap = googleMap;


        mMap.addMarker(marker);

        for (MarkerOptions mark : markerOptions){
            mMap.addMarker(mark);
        }

        enableMyLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(perlis,8));


    }

    private void enableMyLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
            ActivityCompat.requestPermissions(this,perms ,200);

        }
    }
}