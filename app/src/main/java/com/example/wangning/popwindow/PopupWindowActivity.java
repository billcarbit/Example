package com.example.wangning.popwindow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-02-27
 * @since JDK 1.8
 */
public class PopupWindowActivity extends Activity {
    private PopupWindow mPopupWindow;
    private Button mButton;

    /**
     * 已知总数量和列数求行数
     *
     * @param size    总数量
     * @param columns 列数
     * @return 计算得到的行数
     */
    public int rowsOf(int size, int columns) {
        if (size < 1 || columns < 1)
            return 0;
        // 整除
        boolean isDivisible = (size % columns) == 0;
        if (isDivisible) {
            return size / columns;
        } else {
            return size / columns + 1;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);

        GridView gridView = (GridView) findViewById(R.id.gv);
        LinearLayout ll_bottom_item = (LinearLayout) findViewById(R.id.ll_bottom_item);

        List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            datas.add("A" + i);
        }

        int rowsNum = rowsOf(datas.size(), 4);
        if (datas.size() % 4 != 0) {//最后一行不足4个
            List<String> ll = new ArrayList<String>();
            for (int i = 0; i < (rowsNum - 1) * 4; i++) {
                ll.add(datas.get(i));
            }
            GridViewAdapter adapter = new GridViewAdapter(this, ll);
            gridView.setAdapter(adapter);
            for (int i = 0; i < datas.size() % 4; i++) {//最后一行余下的条目
                ItemView itemView= new ItemView(this);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new MyPopupWindow(PopupWindowActivity.this).show(v);
                    }
                });
                itemView.setText(datas.get(i+ll.size()));
                ll_bottom_item.addView(itemView);
            }

        }else{
            GridViewAdapter adapter = new GridViewAdapter(this, datas);
            gridView.setAdapter(adapter);
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new MyPopupWindow(PopupWindowActivity.this).show(view);
            }
        });


        mButton = (Button) findViewById(R.id.btn_popupwin);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new MyPopupWindow(PopupWindowActivity.this).show(v);
            }
        });
    }
}
