package com.example.wangning.title;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.HashMap;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-28
 * @since JDK 1.8
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        ZallGoTitle zallGoTitle = (ZallGoTitle) findViewById(R.id.zallgo_title);

        ZallGoTitle.ZallGoTitleButton btnCopy = new ZallGoTitle.ZallGoTitleButton(getCopyBtnId(), "", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ZallGoTitle.ZallGoTitleButton btnEdit = new ZallGoTitle.ZallGoTitleButton(R.drawable.num1, "123", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ZallGoTitle.ZallGoTitleButton[] btnArray = new ZallGoTitle.ZallGoTitleButton[]{btnCopy,btnEdit};
/*        btnArray[0] = btnCopy;
        btnArray[1] = btnEdit;*/
        zallGoTitle.init("DSNIDNWS", true, btnArray);
        View rightView = zallGoTitle.getRightView();
        View copyBtn = rightView.findViewById(getCopyBtnId());
        //copyBtn.setVisibility(View.GONE);


    }

    private int getCopyBtnId() {
        return R.drawable.num0;
    }
}
