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
    ProvinceResp mProvinceResp = new ProvinceResp();
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
                    mPCADialog.setData(mProvinceResp);
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
        List<ProvinceResp.Province> provinceList = new ArrayList<ProvinceResp.Province>();
        for (int i = 0; i < 100; i++) {
            ProvinceResp.Province provinceEntity = new ProvinceResp.Province();
            provinceEntity.setName("省" + i);
            List<ProvinceResp.Province.City> cityList = new ArrayList<ProvinceResp.Province.City>();
            for (int j = 0; j < 100; j++) {
                ProvinceResp.Province.City cityEntity = new ProvinceResp.Province.City();
                cityEntity.setName(i + "市" + j);
                List<ProvinceResp.Province.City.Area> areaList = new ArrayList<ProvinceResp.Province.City.Area>();
                for (int k = 0; k < 100; k++) {
                    ProvinceResp.Province.City.Area areaEntity = new ProvinceResp.Province.City.Area();
                    areaEntity.setName(i + "-" + j + "区" + k);
                    areaList.add(areaEntity);
                }
                cityEntity.setRegionList(areaList);
                cityList.add(cityEntity);
            }
            provinceEntity.setCity(cityList);
            provinceList.add(provinceEntity);
        }

        mProvinceResp.setProvince(provinceList);
    }
}
