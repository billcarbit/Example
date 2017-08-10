package com.example.wangning.decimalformat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-20
 * @since JDK 1.8
 */
public class DecimalActivity extends Activity implements View.OnClickListener {
    Button btn_format;
    EditText et_num;
    TextView tv_format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal);
        initView();

    }

    void initView() {
        btn_format = (Button) findViewById(R.id.btn_format);
        et_num = (EditText) findViewById(R.id.et_num);
        tv_format = (TextView) findViewById(R.id.tv_format);
        btn_format.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_format:
                String numString = et_num.getText().toString();
                String result = DecimalFormatter.rahToStr_Wan(Double.valueOf(numString));
                tv_format.setText(result);
                break;
            default:
                break;
        }
    }
}
