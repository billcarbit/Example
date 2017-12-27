package com.example.wangning.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.wangning.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class CameraActivity extends Activity
        implements View.OnClickListener, SurfaceHolder.Callback {
    private static final String TAG = "CameraActivity";
    SurfaceView sv;
    Button btn;
    SurfaceHolder mHolder;
    Camera camera;
    ToneGenerator tone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();
        initData();

    }

    void initView() {
        sv = (SurfaceView) findViewById(R.id.sv);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    void initData() {
        mHolder = sv.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.btn:
                //拍摄照片
                camera.takePicture(shutterCallback, null, jpegCallback);
                break;
            default:
                break;

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated");

        //打开摄像头，获得Camera对象
        camera = Camera.open();
        camera.setDisplayOrientation(90);
        try {
            //设置显示
            camera.setPreviewDisplay(holder);
        } catch (IOException exception) {
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged: width=" + width + ",height=" + height);
        //已经获得Surface的width和height，设置Camera的参数
        Camera.Parameters parameters = camera.getParameters();
        // 设置相片格式
        parameters.setPictureFormat(ImageFormat.JPEG);
        // 设置预览大小
        parameters.setPreviewSize(960, 540);
        // 设置对焦方式，这里设置自动对焦
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        camera.setParameters(parameters);
        //开始预览
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        //释放Camera
        camera.release();
        camera = null;
    }

    //快门按下的时候onShutter()被回调
    private Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {

        public void onShutter() {
            if (tone == null) {
                //发出提示用户的声音
                tone = new ToneGenerator(AudioManager.STREAM_MUSIC,
                        ToneGenerator.MAX_VOLUME);
            }
            tone.startTone(ToneGenerator.TONE_PROP_BEEP2);
        }
    };

    //返回照片的JPEG格式的数据
    private Camera.PictureCallback jpegCallback = new Camera.PictureCallback() {

        public void onPictureTaken(byte[] data, Camera camera) {
            Camera.Parameters ps = camera.getParameters();
            if (ps.getPictureFormat() == PixelFormat.JPEG) {
                //存储拍照获得的图片
                String path = save(data);
                //将图片交给Image程序处理
                if (path == null) {
                    return;
                }
                Uri uri = Uri.fromFile(new File(path));
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setDataAndType(uri, "image/jpeg");
                startActivity(intent);
            }
        }
    };

    private String save(byte[] data) {
        String path = "/sdcard/" + System.currentTimeMillis() + ".jpg";
        try {
            //判断是否装有SD卡
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //判断SD卡上是否有足够的空间
                String storage = Environment.getExternalStorageDirectory().toString();
                StatFs fs = new StatFs(storage);
                long available = fs.getAvailableBlocks() * fs.getBlockSize();
                if (available < data.length) {
                    //空间不足直接返回空
                    return null;
                }
                File file = new File(path);
                if (!file.exists())
                    //创建文件
                    file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return path;
    }
}
