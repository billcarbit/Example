package com.example.wangning.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.wangning.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 开源的Banner类，经过简单的改造更适合Zallgo,使用时仅需调用setPages进行控件初始化,
 * 初始化完后调用startTurning方法开始自动播放,停止播放时调用stopTurning
 */
public class ConvenientBanner<T> extends LinearLayout {
    private static final String TAG = "ConvenientBanner";
    private CBViewHolderCreator holderCreator;
    private List<T> mDatas;
    private int[] page_indicatorId;
    private ArrayList<ImageView> mPointViews = new ArrayList<ImageView>();
    private CBPageChangeListener pageChangeListener;
    private CBPageAdapter pageAdapter;
    private CBLoopViewPager viewPager;
    private ViewGroup loPageTurningPoint;
    private long autoTurningTime;
    private boolean turning;
    private boolean canTurn = false;
    private boolean manualPageable = true;

    private PageChangeCallBack pageChangeCallBack;

    public void setPageChangeCallBack(PageChangeCallBack pageChangeCallBack){
        this.pageChangeCallBack = pageChangeCallBack;
    }

    public enum Transformer {
        DefaultTransformer("DefaultTransformer"), AccordionTransformer(
                "AccordionTransformer"), BackgroundToForegroundTransformer(
                "BackgroundToForegroundTransformer"), CubeInTransformer(
                "CubeInTransformer"), CubeOutTransformer("CubeOutTransformer"), DepthPageTransformer(
                "DepthPageTransformer"), FlipHorizontalTransformer(
                "FlipHorizontalTransformer"), FlipVerticalTransformer(
                "FlipVerticalTransformer"), ForegroundToBackgroundTransformer(
                "ForegroundToBackgroundTransformer"), RotateDownTransformer(
                "RotateDownTransformer"), RotateUpTransformer(
                "RotateUpTransformer"), StackTransformer("StackTransformer"), TabletTransformer(
                "TabletTransformer"), ZoomInTransformer("ZoomInTransformer"), ZoomOutSlideTransformer(
                "ZoomOutSlideTransformer"), ZoomOutTranformer(
                "ZoomOutTranformer");

        private final String className;

        // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Transformer(String className) {
            this.className = className;
        }

        public String getClassName() {
            return className;
        }
    }

    private Handler timeHandler = new Handler();
    private Runnable adSwitchTask = new Runnable() {
        @Override
        public void run() {
            if (viewPager != null && turning) {
                if (listener!=null){
                    listener.setCurrentPositionListener(viewPager.getCurrentItem());
                }
                int page = viewPager.getCurrentItem() + 1;
                viewPager.setCurrentItem(page);
                timeHandler.postDelayed(adSwitchTask, autoTurningTime);
            }
        }
    };

    public ConvenientBanner(Context context) {
        this(context, null);
    }
    public ConvenientBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View hView = LayoutInflater.from(context).inflate(
                R.layout.common_perfect_banner, this, true);
        viewPager = (CBLoopViewPager) hView.findViewById(R.id.cbLoopViewPager);
        loPageTurningPoint = (ViewGroup) hView
                .findViewById(R.id.loPageTurningPoint);
        initViewPagerScroll();
    }

    /**
     * 初始化Banner控件
     * @param datas Banner数据，请实现IBanner接口
     * @param listener Banner点击事件，可以传空
     * @param defaultDrawable 默认图片，无则传0
     * @return
     */
    public ConvenientBanner setPages(List<T> datas
            , final BannerImageHolderView.OnBannerClickListener listener
            , final int defaultDrawable){
        this.mDatas = datas;
        this.holderCreator = new CBViewHolderCreator<BannerImageHolderView>() {
            @Override
            public BannerImageHolderView createHolder() {
                return new BannerImageHolderView().setOnBannerClickListener(listener).setDefaultLoadImage(defaultDrawable);
            }
        };
        setPageIndicator(new int[]{R.drawable.banner_point_off, R.drawable.banner_point_on});
        setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer);
        pageAdapter = new CBPageAdapter(holderCreator,mDatas);
        viewPager.setAdapter(pageAdapter);
        viewPager.setBoundaryCaching(true);
        if (page_indicatorId != null) {
            setPageIndicator(page_indicatorId);
        }
        return this;
    }

    /**
     * 初始化Banner控件,可以显示自定义的 ImageView,满足ImageView不同姿势的显示 By liaohuagang
     * @param datas Banner数据，请实现IBanner接口
     * @param listener Banner点击事件，可以传空
     * @param defaultDrawable 默认图片，无则传0
     * @return
     */
    public ConvenientBanner setCustomImageViewPages(List<T> datas
            , final BannerImageHolderView.OnBannerClickListener listener
            ,final BannerImageHolderView.CreateBannerImageView createBannerImageView, final int defaultDrawable){
        this.mDatas = datas;
        this.holderCreator = new CBViewHolderCreator<BannerImageHolderView>() {
            @Override
            public BannerImageHolderView createHolder() {
                return new BannerImageHolderView().setCreateBannerImageView(createBannerImageView).setOnBannerClickListener(listener).setDefaultLoadImage(defaultDrawable);
            }
        };
        setPageIndicator(new int[]{R.drawable.banner_point_off, R.drawable.banner_point_on});
        setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer);
        pageAdapter = new CBPageAdapter(holderCreator,mDatas);
        viewPager.setAdapter(pageAdapter);
        viewPager.setBoundaryCaching(true);
        if (page_indicatorId != null) {
            setPageIndicator(page_indicatorId);
        }
        return this;
    }

    /**
     * 开始自动播放
     * @param intervals 滚动间隔
     */
    public void startScroll(int intervals){

        if (mDatas != null && mDatas.size()>1 && !turning) {
            manualPageable=true;
            startTurning(intervals);
        }else
        {
            manualPageable=false;
        }
    }

    public void setCurrentItem(int index)
    {
        this.viewPager.setCurrentItem(index, false);

    }


    /**
     * 开始自动播放,使用默认时间间隔
     */
    public void startScroll(){

        startScroll(5000);
    }

    /**
     * 停止播放
     */
    public void stopScroll() {

        if (turning) {

            stopTurning();
        }
    }

    /**
     * 设置底部指示器是否可见
     *
     * @param visible
     */
    public ConvenientBanner setPointViewVisible(boolean visible) {
        loPageTurningPoint.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 底部指示器资源图片
     *
     * @param page_indicatorId
     */
    public ConvenientBanner setPageIndicator(int[] page_indicatorId) {
        loPageTurningPoint.removeAllViews();
        mPointViews.clear();
        this.page_indicatorId = page_indicatorId;
        if(mDatas==null||mDatas.size()<=1)return this;
        for (int count = 0; count < mDatas.size(); count++) {
            // 翻页指示的点
            ImageView pointView = new ImageView(getContext());
            pointView.setPadding(8, 0, 8, 0);
            if (mPointViews.isEmpty())
                pointView.setImageResource(page_indicatorId[1]);
            else
                pointView.setImageResource(page_indicatorId[0]);
            mPointViews.add(pointView);
            loPageTurningPoint.addView(pointView);
        }
        //添加了一个获取 viewpage 当前 index 的回调接口 by liaohuagang
        pageChangeListener = new CBPageChangeListener(mPointViews,
                page_indicatorId,pageChangeCallBack);
        viewPager.setOnPageChangeListener(pageChangeListener);

        return this;
    }

    /***
     * 开始翻页
     * @param autoTurningTime 自动翻页时间
     * @return
     */
    public ConvenientBanner startTurning(long autoTurningTime) {
        //如果是正在翻页的话先停掉
        if(turning){
            stopTurning();
        }
        //设置可以翻页并开启翻页
        canTurn = true;
        this.autoTurningTime = autoTurningTime;
        turning = true;
        timeHandler.postDelayed(adSwitchTask, autoTurningTime);
        return this;
    }

    public void stopTurning() {
        turning = false;
        timeHandler.removeCallbacks(adSwitchTask);
    }

    /**
     * 自定义翻页动画效果
     *
     * @param transformer
     * @return
     */
    public ConvenientBanner setPageTransformer(PageTransformer transformer) {
        viewPager.setPageTransformer(true, transformer);
        return this;
    }

    /**
     * 自定义翻页动画效果，使用已存在的效果
     *
     * @param transformer
     * @return
     */
    public ConvenientBanner setPageTransformer(Transformer transformer) {
        try {
            viewPager
                    .setPageTransformer(
                            true,
                            (PageTransformer) Class.forName(
                                    "com.zallgo.component.banner.transforms."
                                            + transformer.getClassName())
                                    .newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置ViewPager的滑动速度
     * */
    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(
                    viewPager.getContext());
//			scroller.setScrollDuration(1500);
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean isManualPageable() {
        return manualPageable;
    }

    public void setManualPageable(boolean manualPageable) {
        this.manualPageable = manualPageable;
    }

    //触碰控件的时候，翻页应该停止，离开的时候如果之前是开启了翻页的话则重新启动翻页
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float tempX = ev.getRawX();
        float tempY = ev.getRawY();
        float tempY1 = ev.getY();
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (mDatas != null && mDatas.size() > 1) {//大于一张图片才翻页
                // 开始翻页
                if (canTurn) startTurning(autoTurningTime);
            }
            if(!manualPageable) {
                super.dispatchTouchEvent(ev);
                return true;
            }
        } else if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 停止翻页
            if (canTurn)stopTurning();
            if(!manualPageable) {
                super.dispatchTouchEvent(ev);
                return true;
            }
        }
        if(ev.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(tempY1<100)
            {
                return false;
            }
        }

        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            if (mDatas != null && mDatas.size() <= 1) {
                return false;
            } else {
                return super.dispatchTouchEvent(ev);
            }
        }
        if(manualPageable) {
            boolean b=super.dispatchTouchEvent(ev);
            return b;
        } else {
             return true;
        }

    }


    public interface PageChangeCallBack{
         void changeIndex(int index);
    }


    private OnCurrentListener listener;

    public void setOnCurrentPositionListener(OnCurrentListener listener){
        this.listener = listener;
    }

    public interface OnCurrentListener{
        void setCurrentPositionListener(int position);
    }
}
