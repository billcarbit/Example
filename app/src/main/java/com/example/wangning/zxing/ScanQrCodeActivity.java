package com.example.wangning.zxing;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.wangning.R;

/**
 * Created by Administrator on 2017/12/26.
 */

public class ScanQrCodeActivity extends Activity {
    SurfaceView sv;
    Camera camera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        initView();
        initData();
    }

    void initView() {
        sv = (SurfaceView) findViewById(R.id.sv);


    }

    void initData() {
        SurfaceHolder holder = sv.getHolder();
        holder.setFixedSize(176, 155);
        holder.setKeepScreenOn(true);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(new TakePictureSurfaceCallback());
    }

    private final class TakePictureSurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera = Camera.open();
                if (camera == null) {
                    int cametacount = Camera.getNumberOfCameras();
                    camera = Camera.open(cametacount - 1);
                }

                Camera.Parameters params = camera.getParameters();
                params.setJpegQuality(80);//照片质量
                params.setPictureSize(1024, 768);//图片分辨率
                params.setPreviewFrameRate(5);//预览帧率

                camera.setDisplayOrientation(90);
                /**
                 * 设置预显示
                 */
                //camera.setPreviewDisplay(surfaceView.getHolder());
                camera.setPreviewDisplay(holder);
                camera.setPreviewCallback(new Camera.PreviewCallback() {
                    @Override
                    public void onPreviewFrame(byte[] data, Camera camera) {

                    }
                });
                /**
                 * 开启预览
                 */
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null) {
                camera.release();
                camera = null;
            }
        }
    }


}
