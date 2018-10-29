package com.example.wangning.floatingbtn;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.wangning.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/10/8.
 */
public class FloatingActionButtonActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> personList;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_btn);
        initData();
        initView();
    }

    private void initData() {
        personList = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            personList.add("A" + i);
        }
    }

    private void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.TRANSPARENT);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        AppBarLayout app_bar_layout = (AppBarLayout) findViewById(R.id.app_bar_layout);

        final CollapsingToolbarLayout collapsing_toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsing_toolbar_layout.setTitle("");
        collapsing_toolbar_layout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsing_toolbar_layout.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsing_toolbar_layout.setExpandedTitleColor(Color.TRANSPARENT);


        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("BBB", "appBarLayoutHeight:" + appBarLayout.getHeight() + " getTotalScrollRange:" + appBarLayout.getTotalScrollRange() + " offSet:" + verticalOffset);
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                    collapsing_toolbar_layout.setTitle("keep");
                } else {
                    collapsing_toolbar_layout.setTitle("");
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F0625E")));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        adapter = new MainAdapter(this, personList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }


}
