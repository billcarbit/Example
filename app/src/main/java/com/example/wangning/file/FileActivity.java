package com.example.wangning.file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/6/14.
 */
public class FileActivity extends Activity implements View.OnClickListener {

    private Button btnNewFile;
    private Button btn_new_dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        initView();
        initData();
    }

    private void initView() {
        btnNewFile = (Button) findViewById(R.id.btn_new_file);
        btn_new_dir = (Button) findViewById(R.id.btn_new_dir);

    }

    private void initData() {
        btnNewFile.setOnClickListener(this);
        btn_new_dir.setOnClickListener(this);
    }

    /**
     * 打开文件管理器
     */
    private void openFileSelection() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_file:
                try {
                    File file = new File(Environment.getExternalStorageDirectory() + "/my_download_file");
                    if (!file.exists()) {
                        file.mkdir();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_new_dir:
                try {
                    File file = new File(Environment.getExternalStorageDirectory() + "/my_test.text");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
