package com.example.wangning.download;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2018/7/16.
 */
public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPause, btnDownload;
    private RetrofitDownloader mRetrofitDownloader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
        initListener();
        mRetrofitDownloader = new RetrofitDownloader(this);
    }

    private void initView() {
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnDownload = (Button) findViewById(R.id.btn_download);
    }


    private void initListener() {
        btnPause.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pause:
                mRetrofitDownloader.pauseDownload();
                break;
            case R.id.btn_download:
                mRetrofitDownloader.startDownload();
                break;
            default:
                break;
        }
    }

    public static void method3(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content + "\r\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
