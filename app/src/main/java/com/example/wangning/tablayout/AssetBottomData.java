package com.example.wangning.tablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangning.R;


/**
 * 资产安全管理底部导航元素
 * Created by Administrator on 2017/12/28.
 */
public class AssetBottomData {

    public static final int []mTabRes = new int[]{R.drawable.asset_management_normal,R.drawable.asset_authorization_normal};
    public static final int []mTabResPressed = new int[]{R.drawable.asset_management_selected,R.drawable.asset_authorization_selected};
    public static final int []mTabTitle = new int[]{R.string.asset_management,R.string.asset_authorization};

    public static Fragment[] getFragments(){
        Fragment fragments[] = new Fragment[2];
        fragments[0] = new FragmentTabPager1();
        fragments[1] = new FragmentTabPager2();
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(AssetBottomData.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(context.getString(mTabTitle[position]));
        tabText.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
        return view;
    }
}