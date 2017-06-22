package com.example.wangning.threelevellinkage;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-05-12
 * @since JDK 1.8
 */
public interface BasePCA {
    String getName();
    boolean isCity();
    boolean isProvince();
    boolean isArea();
    boolean isChecked();
    void setChecked(boolean checked);
}
