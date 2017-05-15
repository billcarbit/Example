package com.example.wangning.threelevellinkage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class ThreeLevelLinkageActivity extends Activity {
    List<Province> mProvinceList = new ArrayList<>();
    PCASelectorDialog mPCADialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_level_linkage);
        initData();
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPCADialog == null) {
                    mPCADialog = new PCASelectorDialog(ThreeLevelLinkageActivity.this, R.style.add_dialog);
                    mPCADialog.setData(mProvinceList);
                }
                mPCADialog.show();
                mPCADialog.setOnAreaClickListener(new PCASelectorDialog.OnAreaClickListener() {
                    @Override
                    public void onAreaClick(String pro, String city, String area) {
                        Log.e("AAA", "onAreaClick: provin=" + pro
                                + ",city=" + city +
                                ",area=" + area);
                    }
                });
            }
        });


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


    public void initData() {
        List<Province> provinceList = new ArrayList<Province>();
        for (int i = 0; i < 100; i++) {
            Province provinceEntity = new Province();
            provinceEntity.setName("省" + i);
            List<Province.City> cityList = new ArrayList<Province.City>();
            for (int j = 0; j < 100; j++) {
                Province.City cityEntity = new Province.City();
                cityEntity.setName(i + "市" + j);
                List<Province.City.Area> areaList = new ArrayList<Province.City.Area>();
                for (int k = 0; k < 100; k++) {
                    Province.City.Area areaEntity = new Province.City.Area();
                    areaEntity.setName(i + "-" + j + "区" + k);
                    areaList.add(areaEntity);
                }
                cityEntity.setArea(areaList);
                cityList.add(cityEntity);
            }
            provinceEntity.setCity(cityList);
            provinceList.add(provinceEntity);
        }


        mProvinceList.addAll(provinceList);
    }
}
