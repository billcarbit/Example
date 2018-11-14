package com.example.wangning.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-29
 * @since JDK 1.8
 */
public class MainFragmentActivity extends FragmentActivity
        implements View.OnClickListener {

    Button btn1;
    Button btn2;
    Button btn3;
    private List<Fragment2> fragmentList = new ArrayList<Fragment2>();
    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
        initView();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initView() {
        FrameLayout frame_layout = (FrameLayout) findViewById(R.id.frame_layout);
        frame_layout.setOnClickListener(this);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }


    private void initData() {
        Bundle bundle = new Bundle();
        for (int i = 0; i < 10; i++) {
            Fragment2 fragment = new Fragment2();
            bundle.putString("a", String.valueOf(i));
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                index = 1;
                sasa(1);
                break;
            case R.id.btn2:
                index = 2;
                sasa(2);
                break;
            case R.id.btn3:
                index = 3;
                sasa(3);
                break;
            default:
                break;

        }
    }

    private void sasa(int value) {
        Fragment2 fragment2 = fragmentList.get(value);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(!fragment2.isAdded()){
            ft.hide(fragment2);
            ft.add(R.id.frame_layout, fragment2);
        }


        for(Fragment2 fragment : fragmentList){
            ft.hide(fragment);
        }
        ft.show(fragment2);
        ft.commitAllowingStateLoss();
    }
}
