package com.example.wangning.file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/6/14.
 */
public class FileActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
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


}
