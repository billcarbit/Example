package com.example.wangning.location;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.List;

public class LocationActivity extends Activity {

    private TextView tv_latitudes, tv_longitude;
    private String provider;
    private Location location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tv_latitudes = findViewById(R.id.tv_latitudes);
        tv_longitude = findViewById(R.id.tv_longitude);
        initLocation();
    }


    public void initLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            return;
        }

        location = locationManager.getLastKnownLocation(provider);

        locationManager.requestLocationUpdates(provider, 1000, 0, locationListener);


    }


    public LocationListener locationListener = new LocationListener() {

        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

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
            tv_latitudes.setText(String.valueOf(location.getLatitude()));
            tv_longitude.setText(String.valueOf(location.getLongitude()));
        }
    };


}
