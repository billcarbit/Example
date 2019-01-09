package com.example.wangning.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapSave {

    public static void saveBitmap(ImageView view, String filePath) {
        view.setDrawingCacheEnabled(true);//开启绘制缓存
        Bitmap imageBitmap = view.getDrawingCache();
        FileOutputStream outStream = null;
        File file = new File(filePath);
        if (file.isDirectory()) {//如果是目录不允许保存
            return;
        }
        try {
            outStream = new FileOutputStream(file);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            imageBitmap.recycle();
            view.setDrawingCacheEnabled(false);//关闭绘制缓存
        }

    }

}
