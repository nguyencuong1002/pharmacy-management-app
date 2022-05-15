package com.example.giuakyqlnt;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.giuakyqlnt.Thuoc.ActivityThuoc;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Vector;

public class ActivityMap extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    SupportMapFragment mapFragment;
    MarkerOptions mark;
    Vector<MarkerOptions> makerOptions;
    FusedLocationProviderClient client;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        navigationView = findViewById(R.id.bottom_nav);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Initialize fused Location
        client = LocationServices.getFusedLocationProviderClient( this);
        //maker
        makerOptions = new Vector<>();
        makerOptions.add(new MarkerOptions().title("Nhà Thuốc 1")
                .position(new LatLng(10.801285083734955, 106.6549088127092))
                .snippet("Mở từ 7AM-12PM")
        );
        makerOptions.add(new MarkerOptions().title("Nhà Thuốc 2")
                .position(new LatLng(10.858875038055253, 106.80268892745202))
                .snippet("Mở từ 7AM-12PM")
        );
        makerOptions.add(new MarkerOptions().title("Nhà Thuốc 3")
                .position(new LatLng(10.840314620408328, 106.79849537195064))
                .snippet("Mở từ 7AM-12PM")
        );
        makerOptions.add(new MarkerOptions().title("Nhà Thuốc 4")
                .position(new LatLng(10.860265685752669, 106.78193899698404))
                .snippet("Mở từ 7AM-12PM")
        );
        makerOptions.add(new MarkerOptions().title("Nhà Thuốc 5")
                .position(new LatLng(10.84823919283536, 106.76323353864443))
                .snippet("Mở từ 7AM-12PM")
        );

/*        navigationView.setSelectedItemId(R.id.action_map);*/
//        Bottom Navigation
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(ActivityMap.this, MainActivity.class));
                        break;

                    case R.id.action_map:
                        return true;

                    case R.id.action_chart:
                        startActivity(new Intent(ActivityMap.this, ActivityThongTinBanLe.class));
                        break;

                    case R.id.action_setting:
                        startActivity(new Intent(ActivityMap.this, ActivityThuoc.class));
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        // Marker
        for (MarkerOptions mark : makerOptions) {
            mMap.addMarker(mark);
        }
        // tạo vị trí
/*        LatLng latLng1 = new LatLng(10.860265685752669, 106.78193899698404);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 15));*/
        // bật zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = getMyLocation();
        if (location != null)
        {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(10)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        enablemylocation();
    }

    @SuppressLint("MissingPermission")
    private Location getMyLocation() {
        // Get location from GPS if it's available
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        // Location wasn't found, check the next most accurate place for the current location
        if (myLocation == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            // Finds a provider that matches the criteria
            String provider = lm.getBestProvider(criteria, true);
            // Use the provider to get the last known location
            myLocation = lm.getLastKnownLocation(provider);
        }
        return myLocation;
    }

    private void enablemylocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);

            } else {
                String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
                // Permission to access the location is missing. Show rationale and request permission
                ActivityCompat.requestPermissions(this, perms, 208);
            }
        }
    }

}
