package com.example.wangning.coordinator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import com.example.wangning.R;

/**
 * Created by Administrator on 2018/7/31.
 */
public class CoordinatorLayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        ctl.setTitle("EPEPEEPEP");
        ctl.setCollapsedTitleTextColor(Color.WHITE);
        ctl.setExpandedTitleColor(Color.WHITE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.nav_bell_white1);





    }
}
