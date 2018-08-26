package com.example.wangning;

import com.alibaba.fastjson.JSONArray;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2018/8/20.
 */
public class QueueTest {

    @Test
    public void queue() throws Exception {
        SearchHistoryBox searchHistoryBox = new SearchHistoryBox();
        for (int i = 0; i < 20; i++) {
            searchHistoryBox.offer(String.valueOf(i));
        }
        Object[] queue = searchHistoryBox.getQueue().toArray();
        for (int i = 0; i < queue.length; i++) {
            System.out.println(queue[i]);
        }
    }
}
