package com.example.wangning.progress;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.wangning.R;


public class ProgressDialog extends Dialog {

    private Context mContext;

    public ProgressDialog(Context context) {
        super(context, R.style.add_dialog);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.softupdate_progress);
        ProgressBar update_progress = findViewById(R.id.update_progress);

    }


}
