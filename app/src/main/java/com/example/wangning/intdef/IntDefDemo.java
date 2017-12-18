package com.example.wangning.intdef;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-12-15
 * @since JDK 1.8
 */
public class IntDefDemo {

    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;

    public void dasd(int jj) {
       @WeekDays int a = jj;
        switch (a){

        }
    }


    @IntDef({SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WeekDays {
    }

}
