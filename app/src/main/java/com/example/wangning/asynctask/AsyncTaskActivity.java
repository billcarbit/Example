package com.example.wangning.asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;

import java.util.Date;
import java.util.List;

public class AsyncTaskActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ClassifierAsyncTask().execute("w1w1", "dddd", "ffff");
            }
        });
    }


    private class ClassifierAsyncTask extends AsyncTask<String, List, List> {


        @Override
        protected List doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
        }
    }
}
