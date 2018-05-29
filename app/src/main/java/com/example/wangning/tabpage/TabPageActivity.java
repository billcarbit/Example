package com.example.wangning.tabpage;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangning.R;
import com.example.wangning.fragment.Fragment2;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-09-20
 * @since JDK 1.8
 */
public class TabPageActivity extends Activity implements ViewPager.OnPageChangeListener {

    private TextView tv_title1;
    private TextView tv_title2;
    private TextView tv_title3;

    private ViewPager viewPager;
    private List<View> views; //用于装页卡
    private View view1, view2, view3; //分别对应3个Tab的页卡视图
    private ImageView iv_cursor;
    private int bmpW; //指示器图片的宽
    private int offset; // 动画图片偏移量
    private int currIndex = 0; // 当前页卡的编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_page);

        //分开封装，便于维护和阅读
        initImageView();
        initTitleView();
        initViewPager();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);//获取控件对象
        views = new ArrayList<View>(); //初始化容器
        LayoutInflater inflater = LayoutInflater.from(this); //用LayoutInflater获取xml布局文件
        view1 = inflater.inflate(R.layout.fragment1_main, null);//加载tab对应的xml布局
        views.add(view1); //添加进容器
        view2 = inflater.inflate(R.layout.fragment2_main, null);
        views.add(view2);
        view3 = inflater.inflate(R.layout.fragment1_main, null);
        views.add(view3);

        viewPager.setAdapter(new MyPagerAdapter()); //绑定适配器
        viewPager.setCurrentItem(0); //默认显示第1张页卡

        viewPager.setOnPageChangeListener(this); //设置页卡改变监听器

    }

    private void initTitleView() {
        tv_title1 = (TextView) findViewById(R.id.tv_title1);
        tv_title2 = (TextView) findViewById(R.id.tv_title2);
        tv_title3 = (TextView) findViewById(R.id.tv_title3);

        tv_title1.setOnClickListener(new MyListener(0)); //为TextView设置监听器
        tv_title2.setOnClickListener(new MyListener(1));
        tv_title3.setOnClickListener(new MyListener(2));
    }

    private void initImageView() {
        iv_cursor = (ImageView) findViewById(R.id.iv_cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.radio_check)
                .getWidth(); //获得图片宽度
        DisplayMetrics outMetrics = new DisplayMetrics(); // 用来保存屏幕相关数据的对象
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics); // 填充当前屏幕数据到该对象
        int screenW = outMetrics.widthPixels; // 获得屏幕宽度

        // 动画操作
        Matrix matrix = new Matrix(); // 用来做矩阵变换的对象
        offset = (screenW / 3 - bmpW) / 2;
        matrix.postTranslate(offset, 0); // 初始位置对于 页卡编号0
        iv_cursor.setImageMatrix(matrix); // 设置动画效果
    }

    //自定义监听器类
    private class MyListener implements View.OnClickListener {
        private int index = -1;

        public MyListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index); //设置显示的页卡
        }
    }

    //自定义页卡适配器
    private class MyPagerAdapter extends PagerAdapter {

        @Override //父容器中页卡总数
        public int getCount() {
            return views.size();
        }

        @Override //建立object和view的映射
        public boolean isViewFromObject(View view, Object object) {
            return view == object; //由object来生成view  (key-value)
        }

        @Override //将页卡view从父容器中移除
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override //将页卡view添加到父容器,最后将view作为object返回
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override //页卡改变事件
    public void onPageSelected(int arg0) {
        //处理指示器动画效果
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        Animation animation = new TranslateAnimation(one * currIndex, one * arg0, 0, 0);
        currIndex = arg0;
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(300);// 持续时间
        iv_cursor.startAnimation(animation); //开始动画
    }
}
