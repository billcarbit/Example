package com.example.wangning.bitmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wangning.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/2/8.
 */
public class BitmapActivity extends Activity
        implements View.OnClickListener {
    final static int CODE_OPEN_PHOTO_ALBUM = 0x1;//从手机相册中选择
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case CODE_OPEN_PHOTO_ALBUM:
                //返回后，裁剪图片并输出
                Uri uri = data.getData();
                Bitmap photoBmp = null;
                try {
                    photoBmp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap bm = Bitmap.createScaledBitmap(photoBmp, 150, 150, true);
                save(bm);
                break;
            default:
                break;
        }
    }

    private void save(Bitmap btImage) {
        FileOutputStream out = null;
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        try {
            out = new FileOutputStream(file);
            btImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
            System.out.println("___________保存的__sd___下_______________________");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(BitmapActivity.this, "保存已经至" + Environment.getExternalStorageDirectory() + "下", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                startActivityForResult(getPhotoPickIntent(), CODE_OPEN_PHOTO_ALBUM);
                break;
            default:
                break;
        }
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
