package com.example.wangning.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */
public class PermissionActivity extends FragmentActivity {
    PermissionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        btnRuntimePermission();
    }

    public void requestRuntimePermissions(String[] permissions, PermissionListener listener) {
        Log.e("AAA", "requestRuntimePermissions: ");
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                Log.e("AAA", "permission: " + permission);
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
            Log.e("AAA", "requestPermissions: ");
        } else {
            mListener.onGranted();
        }
    }


    public void btnRuntimePermission() {
        requestRuntimePermissions(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onGranted() {
                Log.e("AA", "onGranted: All Permissions Granted!" );
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {
                StringBuilder builder = new StringBuilder(32);
                int deniedCount = deniedPermissions.size();
                for (int i = 0; i < deniedCount; i++) {
                    String[] strArray = deniedPermissions.get(i).split("\\.");
                    builder.append(strArray[strArray.length - 1]);
                    if (i == (deniedCount - 1)) {
                        builder.append(".");
                    } else {
                        builder.append(",");
                    }
                }
                Log.e("AA", "Denied Permissions:"+builder);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermission = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        if (grantResult == PackageManager.PERMISSION_DENIED) {
                            deniedPermission.add(permissions[i]);
                        }
                    }
                    if (deniedPermission.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermission);
                    }
                }
                break;
            default:
                break;
        }
    }

}
