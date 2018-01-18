package com.example.wangning.zxing;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;

/**
 * Created by Administrator on 2017/12/26.
 */

public class ScanQrCodeActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "ScanQrCodeActivity";
    SurfaceView sv;
    Camera camera;
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        initView();
        initData();
    }

    void initView() {
        sv = (SurfaceView) findViewById(R.id.sv);
        btn = (Button) findViewById(R.id.btn_get_json);
    }

    void initData() {
        btn.setOnClickListener(this);

        SurfaceHolder holder = sv.getHolder();
        holder.setFixedSize(176, 155);
        holder.setKeepScreenOn(true);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(new TakePictureSurfaceCallback());
    }


    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.btn_get_json:
                camera.takePicture(new Camera.ShutterCallback() {
                    @Override
                    public void onShutter() {
                        Log.e(TAG, "onShutter: " );
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        Log.e(TAG, "onPictureTaken: data="+data );
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        Log.e(TAG, "onPictureTaken: data="+data );
                    }
                });

                break;
            default:
                break;

        }
    }

    private final class TakePictureSurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera = Camera.open();
                if (camera == null) {
                    camera = Camera.open(Camera.getNumberOfCameras() - 1);
                }
                Camera.Parameters params = camera.getParameters();
                params.setJpegQuality(100);//照片质量
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
