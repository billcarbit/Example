package com.example.wangning.fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-03-29
 * @since JDK 1.8
 */
public class MainFragmentActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initView() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment1 fragment1 = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putString("a", "1");
        fragment1.setArguments(bundle);
        ft.replace(R.id.frame_layout, fragment1);
        ft.commit();
    }


}
