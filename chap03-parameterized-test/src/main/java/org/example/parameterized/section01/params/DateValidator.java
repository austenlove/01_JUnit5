package org.example.parameterized.section01.params;

import java.time.Month;

public class DateValidator {

    public static boolean isCollect(Month month) {

        // month의 value(숫자)를 가져오기
        int monthValue = month.getValue();

        return monthValue >= 1 && monthValue <= 12;
    }

    public static int getLastDayOf(Month month) {

        return month.maxLength();
    }
}
