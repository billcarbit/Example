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
 * file explain
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
    Province province;
    Adapter1 mAdapter1;
    Adapter2 mAdapter2;
    Adapter3 mAdapter3;
    String mProvince;
    String mCity;
    String mArea;
    List<Province.ProvinceEntity> list1 = new ArrayList<Province.ProvinceEntity>();
    List<Province.ProvinceEntity.CityEntity> list2 = new ArrayList<Province.ProvinceEntity.CityEntity>();
    ;
    List<Province.ProvinceEntity.CityEntity.AreaEntity> list3 = new ArrayList<Province.ProvinceEntity.CityEntity.AreaEntity>();
    LinearLayout mLlClose;
    OnDismissListener mOnDismissListener;

    public PCASelectorDialog(Context context) {
        super(context);
    }

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
        mAdapter1 = new Adapter1<Province.ProvinceEntity>(list1);
        mAdapter2 = new Adapter2<Province.ProvinceEntity.CityEntity>(list2);
        mAdapter3 = new Adapter3<Province.ProvinceEntity.CityEntity.AreaEntity>(list3);
        mLv1.setAdapter(mAdapter1);
        mLv2.setAdapter(mAdapter2);
        mLv3.setAdapter(mAdapter3);
        mLv1.setOnItemClickListener(this);
        mLv2.setOnItemClickListener(this);
        mLv3.setOnItemClickListener(this);
        mLlClose.setOnClickListener(this);
    }

    public void setData(Province pr) {
        province = pr;
        List<Province.ProvinceEntity> proList = province.getProvince();
        Province.ProvinceEntity pro = proList.get(0);
        List<Province.ProvinceEntity.CityEntity> cityList = pro.getCity();
        Province.ProvinceEntity.CityEntity city = cityList.get(0);
        List<Province.ProvinceEntity.CityEntity.AreaEntity> areaList = city.getArea();
        Province.ProvinceEntity.CityEntity.AreaEntity area = areaList.get(0);
        mProvince = pro.getName();
        mCity = city.getName();
        mArea = area.getName();

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
        mOnDismissListener.onDismiss(mProvince,mCity,mArea);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View parent, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.lv1:
                list2.clear();
                Province.ProvinceEntity l1 = list1.get(position);
                List<Province.ProvinceEntity.CityEntity> list = l1.getCity();
                list2.addAll(list1.get(position).getCity());
                mAdapter2.notifyDataSetChanged();

                list3.clear();
                list3.addAll(list2.get(0).getArea());
                mAdapter3.notifyDataSetChanged();

                mProvince = list1.get(position).getName();
                mCity = list2.get(0).getName();
                mArea = list3.get(0).getName();
                break;
            case R.id.lv2:
                list3.clear();
                list3.addAll(list2.get(position).getArea());
                mAdapter3.notifyDataSetChanged();

                mCity = list2.get(position).getName();
                mArea = list3.get(0).getName();

                break;
            case R.id.lv3:
                mArea = list3.get(position).getName();
                dismiss();
                break;
            default:
                break;
        }
    }


    public void setOnDismissListener(OnDismissListener listener) {
        mOnDismissListener = listener;

    }

    public interface OnDismissListener {
        void onDismiss(String pro, String city, String area);
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
                vh.setTvContent((TextView) convertView.findViewById(R.id.tv_content));
                vh.setLlBg((LinearLayout) convertView.findViewById(R.id.ll_bg));
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            T item = getItem(position);
            vh.getTvContent().setText(item.getName());
            if (item.isCity()) {
                vh.getLlBg().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_pca3level_item_click2));
            } else {
                vh.getLlBg().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_pca3level_item_click));
            }
            return convertView;
        }
    }

    class ViewHolder {
        TextView mTvContent;
        LinearLayout mLlBg;

        public TextView getTvContent() {
            return mTvContent;
        }

        public void setTvContent(TextView tvContent) {
            mTvContent = tvContent;
        }

        public LinearLayout getLlBg() {
            return mLlBg;
        }

        public void setLlBg(LinearLayout llBg) {
            this.mLlBg = llBg;
        }
    }
}
