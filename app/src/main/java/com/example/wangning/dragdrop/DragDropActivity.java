package com.example.wangning.dragdrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangning.R;


/**
 * Created by Administrator on 2016/12/28.
 */
public class DragDropActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragdrop);
        ImageView imageView = (ImageView) findViewById(R.id.img);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //创建移动数据
                ClipData.Item item = new ClipData.Item((String) v.getTag());
                ClipData data = new ClipData("AAAAA", new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                //调用startDrag方法，第二个参数为创建拖放阴影
                v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
                return true;

            }
        });
        final TextView title = (TextView) findViewById(R.id.title);
        final LinearLayout container = (LinearLayout) findViewById(R.id.container);
        container.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        //拖拽开始事件
                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            return true;
                        }
                        return false;
                    case DragEvent.ACTION_DRAG_ENTERED: //被拖放View进入目标View
                        container.setBackgroundColor(Color.YELLOW);
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        //被拖放View离开目标View
                        container.setBackgroundColor(Color.BLUE);
                        title.setText("");
                        return true;
                    case DragEvent.ACTION_DROP:
                        // 释放拖放阴影，并获取移动数据
                        ClipData.Item item = event.getClipData().getItemAt(0);
                        String dragData = item.getText().toString();
                        title.setText(dragData + event.getY() + ":" + event.getX());
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED: //拖放事件完成
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
