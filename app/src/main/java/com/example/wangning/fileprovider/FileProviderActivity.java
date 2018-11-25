package com.example.wangning.fileprovider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;

import java.io.File;

/**
 * Created by admin on 2018/11/5.
 */

public class FileProviderActivity extends Activity {

    private static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_provider);

        Button btn_camera = findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(FileProviderActivity.this,"dwdw",new File(PHOTO_DIR,"wang"),0x12);
            }
        });
    }


    /**
     * @param activity    当前activity
     * @param authority   FileProvider对应的authority
     * @param file        拍照后照片存储的文件
     * @param requestCode 调用系统相机请求码
     */
    public void takePicture(Activity activity, String authority, File file, int requestCode) {
        Intent intent = new Intent();
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(activity, authority, file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            imageUri = Uri.fromFile(file);
        }
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, requestCode);
    }


}
