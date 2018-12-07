package com.example.wangning.forceupdate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.wangning.R;

/**
 * 强制更新
 */
public class ForceUpdateActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force_update);

        //这里来检测版本是否需要更新
        final UpdateManager mUpdateManager = new UpdateManager(this);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdateManager.showNoticeDialog(2.0, 1.0);
            }
        });


    }
}
