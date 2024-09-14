package com.example.shesafe;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.shesafe.databinding.ActivityShowMyCurrentLocationBinding;

import java.io.IOException;
import java.util.List;

public class Show_My_Current_Location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityShowMyCurrentLocationBinding binding;

    LocationManager locationManager;
    public static final int REQUEST_LOCATION_PERMISSION=1;
    double latitude,longitude;
    String address;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(Show_My_Current_Location.this);
        editor=sharedPreferences.edit();


        binding = ActivityShowMyCurrentLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        //Check the Permission granted or not
        if(ActivityCompat.checkSelfPermission(Show_My_Current_Location.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Show_My_Current_Location.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSION);
        }

        else if (ActivityCompat.checkSelfPermission(Show_My_Current_Location.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Show_My_Current_Location.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_LOCATION_PERMISSION);
        }


        //When Sim is Enable
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 60000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {

                    latitude=location.getLatitude();
                    longitude=location.getLongitude();

                    //Convert latitude & longtude into address
                    Geocoder geocoder=new Geocoder(Show_My_Current_Location.this);

                    try {
                        List<Address> addressList=geocoder.getFromLocation(latitude,longitude,1);
                        address = addressList.get(0).getAddressLine(0)+" "+addressList.get(0).getLocality()+
                                " "+addressList.get(0).getCountryName();

                        editor.putString("add",address).commit();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        //When GPS Is ON
        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 10,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(@NonNull Location location) {

                            latitude=location.getLatitude();
                            longitude=location.getLongitude();

                            Geocoder geocoder=new Geocoder(Show_My_Current_Location.this);

                            try {
                                List<Address> addressList=geocoder.getFromLocation(latitude,longitude,1);
                                address = addressList.get(0).getAddressLine(0)+" "+addressList.get(0).getLocality()+
                                        " "+addressList.get(0).getCountryName();

                               // Toast.makeText(Show_My_Current_Location.this, address, Toast.LENGTH_SHORT).show();
                                editor.putString("add",address).commit();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });


        }
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onBackPressed(){
        super.onBackPressed();
        Intent i=new Intent(Show_My_Current_Location.this,MainActivity.class);
        startActivity(i);
    }
}