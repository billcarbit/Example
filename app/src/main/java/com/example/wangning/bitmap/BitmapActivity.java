package com.example.wangning.bitmap;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wangning.R;
import com.example.wangning.utils.AppUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/8.
 */
public class BitmapActivity extends Activity
        implements View.OnClickListener {

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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        switch (requestCode) {
            case CODE_TAKE_PHOTO:
                Log.e(TAG, "onActivityResult: mCurrentPhotoFile=" + mCurrentPhotoFile);
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


    public static Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
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
}
