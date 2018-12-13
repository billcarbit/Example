package com.example.wangning.installer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.example.wangning.R;
import com.example.wangning.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InstallerActivity extends Activity {

    private Activity mActivity;
    private int REQUEST_CODE_PERMISSION_APPLY = 101;//权限申请
    private int REQUEST_CODE_APP_DETAILS = 102;//APP详情页面
    private int REQUEST_CODE_UNKNOWN_APP = 103;//允许未知APP
    private int REQUEST_CODE_INSTALL = 104;//启动安装

    private static final String savePath = "/sdcard/ovu_temp/"; //apk保存到SD卡的路径
    private static final String saveFileName = savePath + "ovupark_v3.0.2show.apk"; //完整路径名
    private PermissionListener permissionListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installer);
        mActivity = this;
        permissionListener = new PermissionListenerImpl();
        checkPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 101:
                List<String> deniedPermission = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    int grantResult = grantResults[i];
                    if (grantResult == PackageManager.PERMISSION_DENIED) {
                        deniedPermission.add(permissions[i]);
                    }
                }
                if (deniedPermission.isEmpty()) {
                    permissionListener.onGranted();
                } else {
                    //permissionListener.onDenied(deniedPermission);
                    //finish();
                    ToastUtils.show(mActivity, "请打开权限");
                    startActivityForResult(getAppDetailSettingIntent(), REQUEST_CODE_APP_DETAILS);
                    finish();
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("AAAA", "onActivityResult,requestCode=" + requestCode + ",resultCode=" + resultCode);
        if (REQUEST_CODE_UNKNOWN_APP == requestCode) {
            ToastUtils.show(mActivity, "请允许来源");
            checkPermissions();
        }

        if(REQUEST_CODE_PERMISSION_APPLY == requestCode){
            ToastUtils.show(mActivity, "安装取消");
            finish();
        }

    }

    /**
     * 获取应用详情页面intent（如果找不到要跳转的界面，也可以先把用户引导到系统设置页面）
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }


    protected void requestRuntimePermissions(String[] permissions, PermissionListener listener) {
        List<String> deniedPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissionList.add(permission);
            }
        }
        if (deniedPermissionList.isEmpty()) {
            listener.onGranted();
        } else {
            listener.onDenied(deniedPermissionList);

        }
    }

    public interface PermissionListener {
        void onGranted();

        void onDenied(List<String> deniedPermission);
    }

    private class PermissionListenerImpl implements PermissionListener {

        @Override
        public void onGranted() {
            executeInstallApk();//如果权限都已被授予，则执行安装
        }

        @Override
        public void onDenied(List<String> deniedPermission) {
            ActivityCompat.requestPermissions(mActivity, deniedPermission.toArray(new String[deniedPermission.size()]), REQUEST_CODE_PERMISSION_APPLY);

            //startActivityForResult(getAppDetailSettingIntent(), REQUEST_CODE_APP_DETAILS);
        }
    }

    /**
     * 检查权限
     */
    public void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            Log.e("AAA", "haveInstallPermission: " + haveInstallPermission);
            if (!haveInstallPermission) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                startActivityForResult(intent, REQUEST_CODE_UNKNOWN_APP);
                return;
            }
        }
        requestRuntimePermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionListener);

    }

    private void executeInstallApk() {
        File apkFile = new File(saveFileName);
        if (!apkFile.exists()) {
            Log.e("AAAA", "APK文件丢失");
            return;
        }


        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mActivity, mActivity.getApplicationContext().getPackageName() + ".provider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (mActivity.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            startActivity(intent);
            finish();
        }
    }

}
