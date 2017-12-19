package com.example.wangning;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-12-19
 * @since JDK 1.8
 */
public class A {


    public A clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new A();
    }

    public static enum AnimationStyle {
        A, b, c
    }
}
