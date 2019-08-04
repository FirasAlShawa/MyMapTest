package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Random;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btn;
    private EditText locationEdit;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btn = findViewById(R.id.newmarker);
        locationEdit = findViewById(R.id.locationEdit);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
//                Random random = new Random();
//                LatLng sydney = new LatLng(24.663991, 46.755583);
//                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney" + random.nextInt()));

                getCurrentLocation();

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getCurrentLocation() {
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    Activity#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for Activity#requestPermissions for more details.
//            return;
//        }
//        Task loction = mFusedLocationClient.getLastLocation();
//        loction.addOnCompleteListener(new OnCompleteListener() {
//            @Override
//            public void onComplete(@NonNull Task task) {
//                if(task.isSuccessful())
//                {
//                    Location currentlocation = (Location) task.getResult();
//                    System.out.println(currentlocation);
//                    mMap.addCircle( new CircleOptions()
//                            .center(new LatLng(currentlocation.getAltitude(),currentlocation.getLongitude()))
//                    .radius(10000.0).strokeColor(Color.GREEN).strokeWidth(3f).fillColor(Color.argb(70,150,50,50)));
//                }else{
//                    System.out.println("Current locatoin is failed!");
//                }
//            }
//        });
        String[] text = locationEdit.getText().toString().split(",");
        LatLng work =new LatLng(Double.parseDouble(text[0]), Double.parseDouble(text[1]));
        mMap.addMarker(new MarkerOptions().position(work).title("Marker in work place"));
        mMap.addCircle( new CircleOptions()
                            .center(work)
                    .radius(500.0).strokeColor(Color.RED).strokeWidth(3f).fillColor(Color.argb(70,150,50,50)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(work,10));

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
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

    }
}
