package com.example.wangning.threelevellinkage;

import android.app.Activity;
import android.content.DialogInterface;
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
    Province province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_level_linkage);
        initData();
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PCASelectorDialog dialog = new PCASelectorDialog(ThreeLevelLinkageActivity.this, R.style.add_dialog);
                dialog.show();
                dialog.setData(province);
                dialog.setOnDismissListener(new PCASelectorDialog.OnDismissListener() {
                    @Override
                    public void onDismiss(String pro, String city, String area) {
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
        List<Province.ProvinceEntity> provinceList = new ArrayList<Province.ProvinceEntity>();
        for (int i = 0; i < 100; i++) {
            Province.ProvinceEntity provinceEntity = new Province.ProvinceEntity();
            provinceEntity.setName("省" + i);
            List<Province.ProvinceEntity.CityEntity> cityList = new ArrayList<Province.ProvinceEntity.CityEntity>();
            for (int j = 0; j < 100; j++) {
                Province.ProvinceEntity.CityEntity cityEntity = new Province.ProvinceEntity.CityEntity();
                cityEntity.setName(i + "市" + j);
                List<Province.ProvinceEntity.CityEntity.AreaEntity> areaList = new ArrayList<Province.ProvinceEntity.CityEntity.AreaEntity>();
                for (int k = 0; k < 100; k++) {
                    Province.ProvinceEntity.CityEntity.AreaEntity areaEntity = new Province.ProvinceEntity.CityEntity.AreaEntity();
                    areaEntity.setName(i + "-" + j + "区" + k);
                    areaList.add(areaEntity);
                }
                cityEntity.setArea(areaList);
                cityList.add(cityEntity);
            }
            provinceEntity.setCity(cityList);
            provinceList.add(provinceEntity);
        }
        province = new Province();
        province.setAreaVersion(1);
        province.setProvince(provinceList);
    }
}
