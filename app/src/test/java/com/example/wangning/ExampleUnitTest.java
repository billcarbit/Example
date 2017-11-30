package com.example.wangning;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONCreator;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void json() throws Exception {
        JSONObject jsonA = (JSONObject) JSONObject.toJSON(new A());
        B b = JSONObject.toJavaObject(jsonA, B.class);
        System.out.println("jsonA=" + jsonA.toJSONString());
        System.out.println("b=" + b.a);

        int cx = 0;
        float fd = 0.000000000000f;
        System.out.println("fd=" + (fd == 0));
    }

    static class A {
        int a = 1;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }

    static class B {
        boolean a;

        public boolean isA() {
            return a;
        }

        public void setA(boolean a) {
            this.a = a;
        }
    }
}