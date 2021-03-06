package com.example.wangning;

import android.util.Xml;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.UTF8Decoder;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void aaa() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -3);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 31);

        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
    }

    @Test
    public void stringCompare() {
        String serverVersion = "V3.0.15";
        String appVersion = "V3.0.2";
        System.out.println(serverVersion.compareTo(appVersion) > 0);
    }


    @Test
    public void simpleDateFormat() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH);
        System.out.println("sdf=" + sdf.format(new Date()));
    }


    @Test
    public void math() throws Exception {
        int result = Integer.parseInt("11");
        int result2 = result / 10;
        System.out.println("result2=" + result2);
    }

    @Test
    public void object2MapTest() throws Exception {
        Hello hello = new Hello();
        Map<String, Object> map = object2Map(hello);
        System.out.println("map=" + (map.get("aaa") == null));
    }


    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    @Test
    public void jsonTest() throws Exception {
        String jsonArray = "[{\"sortingId\":\"0794f424fa9c4788bb0e5c9bd19a7ed8\",\"sortingName\":\"金融服务\"}]";
        JSONArray jsonArray1 = new JSONArray(jsonArray);
        System.out.println("result=" + jsonArray1.length());
    }

    @Test
    public void chinese() throws Exception {
        String gbk = "iteye问答频道编码转换问题";

        String iso = new String(gbk.getBytes("UTF-8"), "ISO-8859-1");

        System.out.println(iso);

        String utf8 = new String(iso.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(utf8);

        System.out.println(getUTF8StringFromGBKString(gbk));
    }

    public static String getUTF8StringFromGBKString(String gbkStr) {
        try {
            return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalError();
        }
    }

    public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
        int n = gbkStr.length();
        byte[] utfBytes = new byte[3 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int m = gbkStr.charAt(i);
            if (m < 128 && m >= 0) {
                utfBytes[k++] = (byte) m;
                continue;
            }
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
        }
        if (k < utfBytes.length) {
            byte[] tmp = new byte[k];
            System.arraycopy(utfBytes, 0, tmp, 0, k);
            return tmp;
        }
        return utfBytes;
    }


    @Test
    public void regexTest() throws Exception {
        String regex = "^(?=.*\\d)(?=.*[a-zA-Z]).{8,}$";
        boolean result = "12345678".matches(regex);
        System.out.println("result=" + result);
    }

    @Test
    public void splitTest() throws Exception {
        String dotString = "2,";
        String[] array = dotString.split(",");
        System.out.println("array.length=" + array.length + ",array[0]=" + array[0]);
    }

    @Test
    public void dateRange() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2018-4-12");
        Date endDate = sdf.parse("2018-4-12");
        System.out.println("getGapCount=" + getGapCount(startDate, endDate));
    }


    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
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