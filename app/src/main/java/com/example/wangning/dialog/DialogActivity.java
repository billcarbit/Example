package com.example.wangning.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-17
 * @since JDK 1.8
 */
public class DialogActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_del);
        Button btn = (Button) findViewById(R.id.btn);

        Log.e("AAA", "onCreate: " + 4 % 3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DialogActivity.this,SignInSuccessActivity.class));
            // startActivity(new Intent(DialogActivity.this,DialogStyleActivity.class));
            }
        });


    }
}
