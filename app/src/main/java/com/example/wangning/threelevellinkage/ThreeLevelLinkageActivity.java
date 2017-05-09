package com.example.wangning.threelevellinkage;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class ThreeLevelLinkageActivity extends Activity {
    ThreeLevelLinkageView three_level_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_level_linkage);
        three_level_view = (ThreeLevelLinkageView) findViewById(R.id.three_level_view);
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        for(int i=0;i<10;i++){
            list1.add("A"+i);
        }
        for(int i=0;i<10;i++){
            list2.add("B"+i);
        }
        for(int i=0;i<10;i++){
            list3.add("C"+i);
        }
        three_level_view.setLevel1(list1);
        three_level_view.setLevel2(list2);
        three_level_view.setLevel3(list3);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
