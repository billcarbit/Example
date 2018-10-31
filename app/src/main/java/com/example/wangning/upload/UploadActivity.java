package com.example.wangning.upload;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wangning.R;
import com.example.wangning.bitmap.BitmapCompressUtils;
import com.example.wangning.utils.AppUtil;
import com.example.wangning.utils.ImageUtils;

import java.io.File;

public class UploadActivity extends Activity implements View.OnClickListener {
    Button btn_upload;
    ImageView iv_photo;
    final static int CODE_OPEN_PHOTO_ALBUM = 0x1;//从手机相册中选择
    Uri picUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        iv_photo = findViewById(R.id.iv_photo);
        btn_upload = findViewById(R.id.btn_upload);
        iv_photo.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
    }

    /**
     * 获取调用相册的Intent
     */
    public Intent getPhotoPickIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        return intent;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_photo:
                startActivityForResult(getPhotoPickIntent(), CODE_OPEN_PHOTO_ALBUM);
                break;
            case R.id.btn_upload:
                new MyUploader().upload(this, AppUtil.getRealFilePath(this, picUri), new MyUploader.Callback() {
                    @Override
                    public void onUploadSuccess(String url) {

                    }

                    @Override
                    public void onUploadFail(String msg, int errorCode) {

                    }
                });
                break;
            default:
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case CODE_OPEN_PHOTO_ALBUM:
                picUri = data.getData();
                Bitmap photo = ImageUtils.getScaledBitmap(this, picUri);
                iv_photo.setImageBitmap(photo);
                break;
            default:
                break;
        }
    }


}
