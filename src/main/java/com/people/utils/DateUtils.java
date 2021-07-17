package com.people.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /*
    * 현재 날짜 및 시간을 원하는 포맷으로 반환
    * */
    public static String getDateFormat(String dFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dFormat);
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    /*
    * 현재 날짜 및 시간을 yyyy-MM-dd HH:mm:ss 포맷으로 반환
    * */
    public static String getNowDateAndTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }
    /*
    * 현재 날짜를 yyyyMMdd 포맷으로 반환
    * */
    public static String getNowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    /*
    * 현재 날짜 + day를 yyyMMdd 포맷으로 반환
    * */
    public static String getNowDate(int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return simpleDateFormat.format(calendar.getTime());
    }
    /*
    * 현재 시간을 HHmmss 포맷으로 반환
    * */
    public static String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    /*
    * 현재 날짜 + 시간을 Date 타입으로 반환
    * */
    public static Date getTime() {
        return  Calendar.getInstance().getTime();
    }
}
