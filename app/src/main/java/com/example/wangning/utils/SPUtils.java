package com.example.wangning.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/1/22.
 */
public class SPUtils {

    private static Context mContext;
    public final static String SP_FILE_NAME = "hand_zone";
    public final static String LOGIN_NAME = "login_name";
    public final static String PARK_NAME = "park_name";
    public final static String PARK_ID = "park_id";
    public final static String COMPANY_ID = "company_id";
    public final static String CUSTOMER_USER_ID = "customer_user_id";
    public final static String CUSTOMER_NAME = "customer_name";
    public final static String USER_ICON = "user_icon";
    public final static String CUSTOMER_ID = "customer_id";
    public final static String USER_TYPE = "user_type";
    public final static String TOKEN = "token";
    public final static String PREF_COOKIES = "pref_cookies";
    public final static String MY_SERVICE_SET = "my_service_set";//我的服务ID列表
    public final static String PROJECT_ID = "project_id";//项目id
    public final static String PROJECT_NAME = "project_name";//项目名称
    public final static String FIRST_INSTALL = "first_install";//第一次安装

    public static void init(Context context) {
        mContext = context;
    }

    public static String getHrSearchJobHistoryKey() {
        return SPUtils.get(CUSTOMER_USER_ID) + "search_job_history";
    }

    public static String getHrSearchCompyHistoryKey() {
        return SPUtils.get(CUSTOMER_USER_ID) + "search_compy_history";
    }


    public static void save(String key, String value) {
        SharedPreferences share = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static void saveBoolean(String key, boolean bool) {
        SharedPreferences share = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putBoolean(key, bool);
        edit.commit();
    }

    public static String get(String key) {
        SharedPreferences share = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return share.getString(key, "");
    }

    public static boolean getBoolean(String key) {
        SharedPreferences share = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return share.getBoolean(key, true);
    }

    public static void putStringSet(String key, Set<String> set) {
        SharedPreferences share = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putStringSet(key, set);
        edit.commit();
    }

    public static Set<String> getStringSet(String key) {
        SharedPreferences share = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return share.getStringSet(key, new HashSet<String>());
    }

}
