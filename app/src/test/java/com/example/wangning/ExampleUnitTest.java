package com.example.wangning;

import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {


    @Test
    public void splitTest() throws Exception {
        String dotString = "2,";
        String [] array = dotString.split(",");
        System.out.println("array.length=" +array.length+",array[0]="+array[0]);
    }

    @Test
    public void readFile() throws Exception {
        File file = new File("D:\\need_replace");

        System.out.println("file.isDirectory()=" + file.isDirectory());
    }

    @Test
    public void createYList() throws Exception {


        List<Float> floats = new ArrayList<>();
        floats.add(14170F);
        floats.add(14230F);
        floats.add(14020F);
        floats.add(13920F);
        floats.add(13950F);
        floats.add(14050F);
        List<Integer> list = createYDataList(floats, 10, 0.8f);

        for (Integer item : list) {
            System.out.println("item=" + item);
        }
    }

    /**
     * 该算法利用等差数列通项公式 an = a1 + (n-1)*d
     *
     * @param dataList 数据源
     * @param scaleNum 想要生成的刻度数量
     * @param percent  最大数据所占Y轴比例 0~1
     * @return
     */
    private List<Integer> createYDataList(List<Float> dataList, int scaleNum, float percent) {
        List<Integer> yList = new ArrayList<>();
        int maxY = 0, minY = 0;
        if (dataList == null || dataList.size() == 0 || percent < 0 || percent > 1) {
            return yList;
        }
        float max = Collections.max(dataList);
        float min = Collections.min(dataList);

        //  float spanMax = max / percent - min;//高出最大数据 1-percent 的值

        float spanMax = (max - min) / percent + min;


        //Y轴刻度最小值应该将min向下取整，最大值应该将spanMax向上取整
        minY = (int) min;
        maxY = (int) (spanMax + 0.9f);
        //已知等差数列首相 a1 为 minY，末项 an 为 maxY，项数为scaleNum,代入公式，求出公差d
        float d = (float) (maxY - minY) / (float) (scaleNum - 1);//由于得出的结果未必是正数，所以类型为float
        //由于Y轴刻度必须是正数，所以公差只能向上取整
        int D = (int) (d + 0.9f);
        //得出最终解
        for (int i = 0; i < scaleNum; i++) {
            yList.add(minY + i * D);
        }
        return yList;
    }


    @Test
    public void repeat() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("-1");
        list.add("2");
        list.add("3");
        boolean checkIsRepeat = checkIsRepeat(list);
        System.out.println("checkIsRepeat=" + checkIsRepeat);
    }

    boolean checkIsRepeat(List<String> list) {
        Set<String> hashSet = new HashSet<String>();
        for (int i = 0; i < list.size(); i++) {
            hashSet.add(list.get(i));
        }
        if (hashSet.size() == list.size()) {
            return false;
        } else {
            return true;
        }
    }

    @Test
    public void compareDate() throws Exception {
        String startTime = "2017-10-01";
        String endTime = "2017-10-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long startDate = 0;
        long endDate = 0;
        try {
            startDate = dateFormat.parse(startTime).getTime();
            endDate = dateFormat.parse(endTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("endDate=" + endDate);
        System.out.println("startDate=" + startDate);
    }

    @Test
    public void json() throws Exception {
        JSONObject jsonA = (JSONObject) JSONObject.toJSON(new A());
        B b = JSONObject.toJavaObject(jsonA, B.class);
        System.out.println("jsonA=" + jsonA.toJSONString());
        System.out.println("b=" + b.getA());


    }

    static class A {
        String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    static class B {
        String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }
}