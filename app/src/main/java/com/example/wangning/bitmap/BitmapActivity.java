package com.example.wangning.bitmap;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wangning.R;

import com.example.wangning.permission.PermissionListener;
import com.example.wangning.utils.AppUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */
public class BitmapActivity extends Activity
        implements View.OnClickListener {
    protected PermissionListener mPermissionListener;
    private static final String TAG = "BitmapActivity";
    final static int CODE_OPEN_PHOTO_ALBUM = 0x1;//从手机相册中选择
    final static int CODE_TAKE_PHOTO = 0x2;//拍摄
    Button btnPick, btnTakePhoto;
    ImageView iv;

    /*拍照的照片存储位置*/
    private static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    private File mCurrentPhotoFile;//照相机拍照得到的图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        initView();
        initListener();
    }

    private void initView() {
        btnPick = (Button) findViewById(R.id.btn_pick);
        btnTakePhoto = (Button) findViewById(R.id.btn_take_photo);
        iv = (ImageView) findViewById(R.id.iv);
    }

    private void initListener() {
        btnPick.setOnClickListener(this);
        btnTakePhoto.setOnClickListener(this);
        btnPick.setOnClickListener(this);
        btnTakePhoto.setOnClickListener(this);

        requestRuntimePermissions(new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION
        }, new PermissionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied(List<String> deniedPermission) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        switch (requestCode) {
            case CODE_TAKE_PHOTO:
                Log.e(TAG, "onActivityResult: mCurrentPhotoFile=" + mCurrentPhotoFile);
                //Uri takePhotoUri = AppUtil.file2Uri(this, new File(mCurrentPhotoFile.toString()));
                Uri takePhotoUri = Uri.fromFile(new File(mCurrentPhotoFile.toString()));
                Log.e(TAG, "onActivityResult:,CODE_TAKE_PHOTO， takePhotoUri=" + takePhotoUri);
                String realPath2 = AppUtil.getRealFilePath(this, takePhotoUri);
                Log.e(TAG, "onActivityResult:,CODE_TAKE_PHOTO， realPath2=" + realPath2);

                break;
            case CODE_OPEN_PHOTO_ALBUM:
                Uri uri = data.getData();
                BitmapCompressUtils.compressByResolution(this, AppUtil.getRealFilePath(this,uri),100,100);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pick:
                startActivityForResult(getPhotoPickIntent(), CODE_OPEN_PHOTO_ALBUM);
                break;
            case R.id.btn_take_photo:
                takePhoto();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState");
    }



    /**
     * 拍照获取图片
     */
    void takePhoto() {
        try {
            PHOTO_DIR.mkdirs();// 创建照片的存储目录
            mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
            final Intent intent = getTakePickIntent(mCurrentPhotoFile);
            startActivityForResult(intent, CODE_TAKE_PHOTO);
        } catch (ActivityNotFoundException e) {
        } catch (SecurityException e) {
        }
    }

    /**
     * 用当前时间给取得的图片命名
     */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'OVU'_yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date) + ".jpg";
    }


    public Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, AppUtil.file2Uri(this, f));
        return intent;
    }

    /**
     * 获取调用相册的Intent
     */
    public Intent getPhotoPickIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        return intent;
    }


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
            mPermissionListener.onGranted();
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
