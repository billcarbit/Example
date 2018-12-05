package com.example.wangning.map;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.wangning.R;

public class LocationMapActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_location);

        //获取地图控件引用
        MapView mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        final AMap aMap = mMapView.getMap();


        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        myLocationStyle.strokeColor(R.color.colorAccent);//设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(R.color.aqua);//设置定位蓝点精圆圈的填充颜色的方法。
        myLocationStyle.strokeWidth(5.0f);//设置定位蓝点精度圈的边框宽度的方法。

        UiSettings mUiSettings;//定义一个UiSettings对象
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomControlsEnabled(true);

        mUiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_BUTTOM);

        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮


        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Log.e("AAA", "onMyLocationChange: ");
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                        new CameraPosition(new LatLng(location.getLatitude(), location.getLongitude()), 19, 0, 0)));

                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(new LatLng(location.getLatitude(), location.getLongitude()));
                //markerOption.position(new LatLng(39.924896,116.418067 ));
                //markerOption.title("西安市").snippet("西安市：34.341568, 108.940174");

                markerOption.draggable(true);//设置Marker可拖动
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.drawable.check_orange_easy_box)));
                aMap.addMarker(markerOption);

            }
        });



    }
}
