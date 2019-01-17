package com.example.wangning.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wangning.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleNestedActivity extends Activity {
    private RecyclerView recycler_view;
    private FirstAdapter mFirstAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        List<FirstNode> firstList = new ArrayList<FirstNode>();
        FirstNode firstNode;
        for (int i = 0; i < 10; i++) {
            firstNode = new FirstNode();
            firstNode.setName("A" + i);
            List<FirstNode.SecondNode> secondList = new ArrayList<FirstNode.SecondNode>();
            FirstNode.SecondNode secondNode;
            for (int j = 0; j < 5; j++) {
                secondNode = new FirstNode.SecondNode();
                secondNode.setName("B" + j);
                secondList.add(secondNode);

            }
            firstNode.setSecondList(secondList);
            firstList.add(firstNode);
        }
        mFirstAdapter = new FirstAdapter(this, firstList);
        recycler_view.setAdapter(mFirstAdapter);
    }


}
