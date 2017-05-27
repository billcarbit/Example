package com.example.wangning.threelevellinkage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 省市区选择对话框
 *
 * @author wangning
 * @version 1.0 2017-05-12
 * @since JDK 1.8
 */
public class PCASelectorDialog extends Dialog implements AdapterView.OnItemClickListener,
        View.OnClickListener {

    ListView mLv1;
    ListView mLv2;
    ListView mLv3;
    Context mContext;
    Adapter1 mAdapter1;
    Adapter2 mAdapter2;
    Adapter3 mAdapter3;
    String mProvince;
    String mCity;
    String mArea;
    List<ProvinceResp.Province> list1 = new ArrayList<ProvinceResp.Province>();
    List<ProvinceResp.Province.City> list2 = new ArrayList<ProvinceResp.Province.City>();
    List<ProvinceResp.Province.City.Area> list3 = new ArrayList<ProvinceResp.Province.City.Area>();
    LinearLayout mLlClose;
    OnAreaClickListener mOnAreaClickListener;

    public PCASelectorDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setWindowAnimations(R.style.mystyle);
        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.view_pca_3level);
        initView();
        mAdapter1 = new Adapter1<ProvinceResp.Province>(list1);
        mAdapter2 = new Adapter2<ProvinceResp.Province.City>(list2);
        mAdapter3 = new Adapter3<ProvinceResp.Province.City.Area>(list3);
        mLv1.setAdapter(mAdapter1);
        mLv2.setAdapter(mAdapter2);
        mLv3.setAdapter(mAdapter3);
        mLv1.setOnItemClickListener(this);
        mLv2.setOnItemClickListener(this);
        mLv3.setOnItemClickListener(this);
        mLlClose.setOnClickListener(this);
    }

    public void setData(ProvinceResp provinceResp) {
        List<ProvinceResp.Province> proList = provinceResp.getProvince();
        ProvinceResp.Province province = proList.get(0);
        List<ProvinceResp.Province.City> cityList = province.getCity();
        ProvinceResp.Province.City city = cityList.get(0);
        List<ProvinceResp.Province.City.Area> areaList = city.getRegionList();
        ProvinceResp.Province.City.Area area = areaList.get(0);

        mProvince = province.getName();
        mCity = city.getName();
        mArea = area.getName();

        province.setChecked(true);
        city.setChecked(true);

        list1.addAll(proList);
        list2.addAll(cityList);
        list3.addAll(areaList);


    }

    void initView() {
        mLv1 = (ListView) findViewById(R.id.lv1);
        mLv2 = (ListView) findViewById(R.id.lv2);
        mLv3 = (ListView) findViewById(R.id.lv3);
        mLlClose = (LinearLayout) findViewById(R.id.ll_close);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_close:
                dismiss();
                break;
            default:
                break;
        }
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        switch (parent.getId()) {
            case R.id.lv1:
                list2.clear();
                ProvinceResp.Province province = list1.get(position);
                List<ProvinceResp.Province.City> cityList = province.getCity();
                ProvinceResp.Province.City city = cityList.get(0);
                List<ProvinceResp.Province.City.Area> areaList = cityList.get(0).getRegionList();
                ProvinceResp.Province.City.Area area = areaList.get(0);
                list2.addAll(cityList);
                mAdapter2.notifyDataSetChanged();


                list3.clear();
                list3.addAll(areaList);
                mAdapter3.notifyDataSetChanged();

                clearList1CheckStatus();
                clearList2CheckStatus();
                clearList3CheckStatus();
                province.setChecked(true);
                city.setChecked(true);
                mAdapter1.notifyDataSetChanged();

                mProvince = province.getName();
                mCity = city.getName();
                mArea = area.getName();
                break;
            case R.id.lv2:
                ProvinceResp.Province.City city2 = list2.get(position);
                List<ProvinceResp.Province.City.Area> areaList2 = city2.getRegionList();
                ProvinceResp.Province.City.Area area2 = areaList2.get(0);
                list3.clear();
                list3.addAll(city2.getRegionList());
                mAdapter3.notifyDataSetChanged();

                clearList2CheckStatus();
                clearList3CheckStatus();
                city2.setChecked(true);
                mAdapter2.notifyDataSetChanged();

                mCity = city2.getName();
                mArea = area2.getName();

                break;
            case R.id.lv3:
                ProvinceResp.Province.City.Area area3 = list3.get(position);
                mArea = area3.getName();
                clearList3CheckStatus();
                area3.setChecked(true);
                mAdapter3.notifyDataSetChanged();
                dismiss();
                mOnAreaClickListener.onAreaClick(mProvince, mCity, mArea);
                break;
            default:
                break;
        }
    }

    private void clearList1CheckStatus() {
        for (int i = 0; i < list1.size(); i++) {
            list1.get(i).setChecked(false);
        }
    }

    private void clearList2CheckStatus() {
        for (int i = 0; i < list2.size(); i++) {
            list2.get(i).setChecked(false);
        }
    }

    private void clearList3CheckStatus() {
        for (int i = 0; i < list3.size(); i++) {
            list3.get(i).setChecked(false);
        }
    }

    public void setOnAreaClickListener(OnAreaClickListener listener) {
        mOnAreaClickListener = listener;

    }

    public interface OnAreaClickListener {
        void onAreaClick(String pro, String city, String area);
    }


    class Adapter3<T extends BasePCA> extends Adapter1 {
        public Adapter3(List list) {
            super(list);
        }
    }

    class Adapter2<T extends BasePCA> extends Adapter1 {
        public Adapter2(List list) {
            super(list);
        }
    }

    class Adapter1<T extends BasePCA> extends BaseAdapter {
        private List<T> mDataList;

        public Adapter1(List<T> list) {
            super();
            mDataList = list;
        }

        @Override
        public int getCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @Override
        public T getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_view_pca_3level, null);
                vh.mTvContent = ((TextView) convertView.findViewById(R.id.tv_content));
                vh.mLlBg = ((LinearLayout) convertView.findViewById(R.id.ll_bg));
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            T item = getItem(position);
            vh.mTvContent.setText(item.getName());
            if (item.isChecked()) {
                vh.mTvContent.setTextColor(mContext.getResources().getColor(R.color.text_ff6600));
            } else {
                vh.mTvContent.setTextColor(mContext.getResources().getColor(R.color.text_222222));
            }
            if (item.isCity()) {
                vh.mLlBg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_pca3level_item_click2));
            } else {
                vh.mLlBg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_pca3level_item_click));
            }
            return convertView;
        }

        class ViewHolder {
            TextView mTvContent;
            LinearLayout mLlBg;
        }
    }
}
