package com.example.wangning.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends Activity {

    private TextView tv_latitudes, tv_longitude;
    private String provider;
    private Location mLocation;
    private PermissionListener mPermissionListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tv_latitudes = findViewById(R.id.tv_latitudes);
        tv_longitude = findViewById(R.id.tv_longitude);
        initLocation();
    }


    public void initLocation() {
        requestRuntimePermissions(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, new PermissionListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onGranted() {
                Log.e("DSDS", "onGranted: " );


                final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // 获取所有可用的位置提供器
                List<String> providerList = locationManager.getProviders(true);
                if (providerList.contains(LocationManager.GPS_PROVIDER)) {
                    provider = LocationManager.GPS_PROVIDER;
                } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
                    provider = LocationManager.NETWORK_PROVIDER;
                } else {
                    return;
                }
                mLocation = locationManager.getLastKnownLocation(provider);
                if(mLocation!=null){
                    tv_latitudes.setText(String.valueOf(mLocation.getLatitude()));
                    tv_longitude.setText(String.valueOf(mLocation.getLongitude()));
                }
                locationManager.requestLocationUpdates(provider, 1, 0, locationListener);
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                Log.e("DSDS", "onDenied: " );
            }
        });




    }


    public LocationListener locationListener = new LocationListener() {

        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e("AAA", "onStatusChanged: ");
        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
            Log.e("AAA", "onProviderEnabled: " + provider);
        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
            Log.e("AAA", "onProviderDisabled: " + provider);

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            Log.e("AAA", "onLocationChanged: " + location);
            mLocation.set(location);
            tv_latitudes.setText(String.valueOf(mLocation.getLatitude()));
            tv_longitude.setText(String.valueOf(mLocation.getLongitude()));
        }
    };


    protected void requestRuntimePermissions(String[] permissions, PermissionListener listener) {
        mPermissionListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 0x1);
        } else {
            listener.onGranted();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0x1:
                if (grantResults.length > 0) {
                    List<String> deniedPermission = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        if (grantResult == PackageManager.PERMISSION_DENIED) {
                            deniedPermission.add(permissions[i]);
                        }
                    }
                    if (deniedPermission.isEmpty()) {
                        mPermissionListener.onGranted();
                    } else {
                        mPermissionListener.onDenied(deniedPermission);
                    }
                }
                break;
            default:
                break;
        }
    }

}
