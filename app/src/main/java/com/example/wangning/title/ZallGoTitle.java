package com.example.wangning.title;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;


/**
 * Created by yishen on 2016/5/16.
 * 通用标题栏
 */
public class ZallGoTitle extends LinearLayout {


    int buttonTextSize=14;



    int buttonTextColor=0xff333333;


    TextView titleText;
    ImageView back;
    LinearLayout leftView,rightView;
    View bg;
    View titleLine;
    public ZallGoTitle(Context context) {
        super(context);
        initView();
    }

    public ZallGoTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ZallGoTitle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
        if(bg!=null)
        {
            bg.setBackgroundResource(resid);
        }
    }


    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_mytitle, this);
        titleText=(TextView) findViewById(R.id.title_text);

        back=(ImageView) findViewById(R.id.back);
        bg= findViewById(R.id.bg);
        leftView=(LinearLayout) findViewById(R.id.left_view);
        rightView=(LinearLayout) findViewById(R.id.right_view);
        titleLine = findViewById(R.id.title_line);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBackLisenter != null) {
                    onBackLisenter.back();
                } else {
                    Activity a = (Activity) getContext();
                    if (a != null)
                        a.onBackPressed();
                }
            }
        });
    }
    public TextView getTitleText() {
        return titleText;
    }

    public void setTitleText(TextView titleText) {
        this.titleText = titleText;
    }
    public void showTitleImg(int left, int top, int right, int bottom)
    {
        titleText.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }
    public int getButtonTextSize() {
        return buttonTextSize;
    }

    public void setButtonTextSize(int buttonTextSize) {
        this.buttonTextSize = buttonTextSize;
    }
    public void setButtonTextColor(int buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
        if(titleText!=null)
        {
            titleText.setTextColor(buttonTextColor);
        }
    }

    public void setBackImg(int id)
    {
        if(back!=null)
        {
            back.setImageResource(id);
        }

    }

    public View getRightView()
    {
        return null == rightView ? null : rightView;
    }

    public  void hidLine(boolean isHid)
    {
        if (null != titleLine && isHid)
        {
            titleLine.setVisibility(GONE);
        }else {
//            titleLine.setVisibility(VISIBLE);
        }
    }

    public void setTitelColor(int color)
    {
        if (null != titleText)
        {
            titleText.setTextColor(color);
        }
    }

    /**
     * 生成 右边按钮
     * @param title 标题文字
     * @param isback 是否显示返回
     */
    public void init(String title, boolean isback)
    {
        init(title,isback,null);
    }

    /**
     *
     * 生成 右边 一个文字按钮
     * @param title 标题文字
     * @param isback 是否显示返回
     * @param buttonText 按钮文字
     * @param l
     */
    public void init(String title, boolean isback, String buttonText, OnClickListener l)
    {
        init(title,isback,new ZallGoTitleButton[]{new ZallGoTitleButton(-1,buttonText,l)});
    }
    /**
     *
     * 生成 右边 一个文字按钮
     * @param title 标题文字
     * @param isback 是否显示返回
     * @param imgId  按钮图片
     * @param l
     */
    public void init(String title, boolean isback, int imgId, OnClickListener l)
    {
        init(title,isback,new ZallGoTitleButton[]{new ZallGoTitleButton(imgId,"",l)});
    }

    /**
     * 生成 右边按钮
     * @param title 标题文字
     * @param isback 是否显示返回
     * @param buttons 右边按钮集合 无则传null
     */
    public void init(String title, boolean isback, ZallGoTitleButton[] buttons) {
        rightView.removeAllViews();
        setTitle(title);
        setIsBack(isback);
        // layout.addView(createFegexian());
        if (null!=buttons)
            for (int i = 0; i < buttons.length; i++) {
                rightView.addView(createButton(buttons[i]));
//            if (i != ids.length - 1) {
//                rightView.addView(createFegexian());
//            }
            }
        rightView.setVisibility(null!=buttons&&buttons.length > 0 ? View.VISIBLE : View.GONE);
    }

    public void setTitle(String titleText)
    {
        this.titleText.setText(titleText+"");
    }
    public void setIsBack(boolean isShow)
    {
        this.back.setVisibility(isShow? View.VISIBLE: View.GONE);
    }

    public void setRightViewVisible(boolean isShow)
    {
        this.rightView.setVisibility(isShow? View.VISIBLE: View.GONE);
    }

    public View createButton(ZallGoTitleButton button)
    {
        if(null==button)
            return  null;
        return TextUtils.isEmpty(button.text+"")?createImageButton(button.imgId,button.onClickListener):createTextView(button.text,button.onClickListener);

    }
    public ImageButton createImageButton(int id, OnClickListener listener) {
        ImageButton leftButton = new ImageButton(getContext());
        leftButton.setImageResource(id);
        leftButton.setId(id);
        leftButton.setBackgroundResource(R.drawable.navbutton_status);
        LayoutParams lParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftButton.setPadding(10, 0, 10, 0);
        leftButton.setLayoutParams(lParams);
        leftButton.setOnClickListener(listener);
        return leftButton;
    }

    public View createTextView(String txt, OnClickListener listener) {

        TextView t = new TextView(getContext());
        t.setBackgroundResource(R.drawable.navbutton_status);
        LayoutParams lParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        t.setLayoutParams(lParams);
        t.setOnClickListener(listener);
        t.setText(txt);
        t.setTextColor(buttonTextColor);
        t.setTextSize(buttonTextSize);
        t.setGravity(Gravity.CENTER);
        t.setPadding(10, 0, 10, 0);
        return t;
    }

    public OnBackLisenter onBackLisenter;

    public void setOnBackLisenter(OnBackLisenter onBackLisenter) {
        this.onBackLisenter = onBackLisenter;
    }

    public interface OnBackLisenter {
        public void back();
    }


    public static  class ZallGoTitleButton
    {
        public int imgId;
        public String text;
        public OnClickListener onClickListener;

        /**
         * 图片和文字只传一个就OK，优先级为文字大于图片
         * @param imgId 按钮图片Id
         * @param text 按钮显示文字
         * @param onClickListener 按钮点击事件
         */
        public ZallGoTitleButton (int imgId, String text, OnClickListener onClickListener)
        {
            this.imgId=imgId;
            this.text=text;
            this.onClickListener=onClickListener;
        }
    }


    /**
     * 设置背景颜色 箭头icon 字体颜色 是否有底线
     */
    public void setTitle(int color,int icon,int buttonTextColor,boolean hasTitleLine){
        setBackgroundResource(color);
        setBackImg(icon);
        setButtonTextColor(buttonTextColor);
        if(titleLine!=null){
            titleLine.setVisibility(hasTitleLine? View.VISIBLE: View.GONE);
        }

    }
}
