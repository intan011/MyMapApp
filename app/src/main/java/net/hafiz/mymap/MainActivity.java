package net.hafiz.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    Button button, buttonLogout,buttonAboutUs, buttonlocation;
    SharedPreferences sharedPreferences;
    LocationManager locationManager;
    Location location;
    double latitude,longitude;
    String latitudeString, longitudeString;
    TextView textView;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLogout= findViewById(R.id.logout);
        buttonlocation = findViewById(R.id.location);
        textView = findViewById(R.id.textView);
        buttonAboutUs = findViewById(R.id.aboutUs);

        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AboutUs.class);
                startActivity(intent);
            }
        });

        sharedPreferences = getSharedPreferences("mymap", MODE_PRIVATE);
        if(sharedPreferences.getString("logged", "false").equals("false")){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        String fullname = sharedPreferences.getString("full_name", "");
        textView.setText("Hello " + fullname);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String savedUsername = sharedPreferences.getString("username", "");
                //String savedApiKey = sharedPreferences.getString("apiKey", "");

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.144.1/LoginRegister/logout.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.equals("success")){
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("logged", "");
                                    editor.putString("full_name", "");
                                    editor.putString("username", "");
                                    editor.putString("apiKey", "");
                                    editor.apply();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("username", sharedPreferences.getString("username", ""));
                        paramV.put("apiKey", sharedPreferences.getString("apiKey", ""));
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });


        button = (Button) findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MapsActivity.class);
                startActivity(intent);


            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        buttonlocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(),MapsActivity.class);
//                if (location != null) {
//                    latitude = location.getLatitude();
//                    longitude = location.getLongitude();
//                    Toast.makeText(getApplicationContext(), "Location Sent " + latitude, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        buttonlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if location permissions are granted
                if (ContextCompat.checkSelfPermission(
                        MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

                    // Request location updates
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            1000,  // 1 seconds
                            10,    // 10 meters
                            new LocationListener() {
                                @Override
                                public void onLocationChanged(@NonNull Location loc) {
                                    // Update the 'location' variable with the latest location
                                    location = loc;
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();

                                    //This is for line inspection
                                    //Toast.makeText(getApplicationContext(), "Location Sent " + latitude, Toast.LENGTH_SHORT).show();

                                    // Convert double values to String
//                                    latitudeString = Double.toString(latitude);
//                                    longitudeString = Double.toString(longitude);
                                    latitudeString = String.format("%.6f", latitude);
                                    longitudeString = String.format("%.6f", longitude);


                                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                    String url ="http://192.168.144.1/LoginRegister/sendlocation.php";

                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                            new Response.Listener<String>() {

                                                @Override
                                                public void onResponse(String response) {
                                                    if (response.equals("success")){
                                                        Toast.makeText(getApplicationContext(), "Location Sent", Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(getApplicationContext(), "Location Failed to Send. Response Unsuccess", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            error.printStackTrace();
                                            Toast.makeText(getApplicationContext(), "Error Response"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }){
                                        protected Map<String, String> getParams(){
                                            Map<String, String> paramV = new HashMap<>();
                                            paramV.put("user_name", fullname);
                                            paramV.put("lat", latitudeString);
                                            paramV.put("lng", longitudeString);
                                            return paramV;
                                        }
                                    };
                                    queue.add(stringRequest);

                                    // Stop location updates after obtaining the location
                                    locationManager.removeUpdates(this);
                                }

                                @Override
                                public void onStatusChanged(String provider, int status, Bundle extras) {
                                }

                                @Override
                                public void onProviderEnabled(String provider) {
                                }

                                @Override
                                public void onProviderDisabled(String provider) {
                                }
                            }
                    );

                } else {
                    // Request location permissions if not granted
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            LOCATION_PERMISSION_REQUEST_CODE
                    );
                    Toast.makeText(getApplicationContext(), "Error Response Location Permission", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}