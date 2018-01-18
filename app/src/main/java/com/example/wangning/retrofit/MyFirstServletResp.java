package com.example.wangning.retrofit;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2017/12/31.
 */
public class MyFirstServletResp {
    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toJson() {
        return JSON.toJSON(this).toString();
    }
}
