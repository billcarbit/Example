package com.example.wangning.zxing;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.wangning.R;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

/**
 * 识别图片二维码
 */
public class RecognizeQRCodeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recog_qr_code);
        final ImageView iv_qr_code = findViewById(R.id.iv_qr_code);
        iv_qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(RecognizeQRCodeActivity.this)
                        .setPositiveButton("识别二维码", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bitmap obmp = ((BitmapDrawable) (iv_qr_code.getDrawable())).getBitmap();
                                int width = obmp.getWidth();
                                int height = obmp.getHeight();
                                int[] data = new int[width * height];
                                obmp.getPixels(data, 0, width, 0, 0, width, height);
                                RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
                                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                                new QrCodeAsyncTask().execute(bitmap);
                            }
                        }).show();


            }
        });
    }

    class QrCodeAsyncTask extends AsyncTask<BinaryBitmap, Void, Result> {

        @Override
        protected Result doInBackground(BinaryBitmap... params) {
            QRCodeReader reader = new QRCodeReader();
            Result result = null;
            try {
                result = reader.decode(params[0]);
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (ChecksumException e) {
                e.printStackTrace();
            } catch (FormatException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            String text = result.getText();
            Log.e("AAAA", "onPostExecute: text=" + text);
        }
    }


}
