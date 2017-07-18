package com.example.wangning.viewpager.datepager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-07-18
 * @since JDK 1.8
 */
public class DatePager extends LinearLayout {
    private static final String TAG = "DatePager";
    ViewPager main_viewpager;
    List<View> views;
    public DatePager(Context context) {
        super(context);

    }

    public DatePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_date_pager, this);
        initView();
        initDate(context);
    }

    void initView() {
        main_viewpager = (ViewPager) findViewById(R.id.main_viewpager);
    }

    void initDate(Context context){
        //准备数据源
        views = new ArrayList<View>();
        View view1 = View.inflate(context, R.layout.viewpager1, null);
        View view2 = View.inflate(context, R.layout.viewpager2, null);
        View view3 = View.inflate(context, R.layout.viewpager3, null);
        views.add(view1);
        //views.add(view2);
      //  views.add(view3);

        DatePagerAdapter pagerAdapter = new DatePagerAdapter(views);
        main_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "onPageScrolled: position="+position+",positionOffset="+positionOffset+",positionOffsetPixels="+positionOffsetPixels );
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: position="+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG, "onPageScrollStateChanged: state="+state);
            }
        });

        main_viewpager.setAdapter(pagerAdapter);
        main_viewpager.setOnTouchListener(new OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "ACTION_DOWN: x="+event.getX()+",y="+event.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "ACTION_UP: x="+event.getX()+",y="+event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "ACTION_MOVE: x="+event.getX()+",y="+event.getY());

                        break;
                    default:
                        break;
                }
                return false;
            }
        });


    }



    private GestureDetector.OnGestureListener listViewGestureListener = new GestureDetector.OnGestureListener(){
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };

    GestureDetector listViewGesture = new GestureDetector(listViewGestureListener);



}
