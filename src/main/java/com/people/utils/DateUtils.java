package com.people.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    /*
    * 현재 날짜 및 시간을 원하는 포맷으로 반환
    * */
    public static String getDateFormat(String dFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dFormat);
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    /*
    * Date 원하는 포맷으로 변환
    * */
    public static String getDateFormat(Date date, String dFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dFormat);
        return simpleDateFormat.format(date);
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


    /*
    * 날짜 yyyyMMdd 형식으로 입력시 Date로 변환
    * */
    public static Date convertStringToDate(String date) throws Exception {
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");

        //Date로 변경하기 위해서는 날짜 포맷을 yyyy-mm-dd로 변경해야 함.
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date beforeDate = null;

        try {
            //yyymmdd로 된 날짜 포맷으로 Date 객체를 생성
            beforeDate = beforeFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Date를 yyyy-mm-dd 형식으로 변경하여 String으로 반환
        String afterDate = afterFormat.format(beforeDate);

        return afterFormat.parse(afterDate);
    }

    /*
    * 날짜로 요일 구하기
    * */
    public static String getDayName(String date) throws ParseException {
        String dayName = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");
        Date nDate = simpleDateFormat.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate);
        int dayNum = cal.get(Calendar.DAY_OF_WEEK);

        switch (dayNum) {
            case 1:
                dayName = "일";
                break;
            case 2:
                dayName = "월";
                break;
            case 3:
                dayName = "화";
                break;
            case 4:
                dayName = "수";
                break;
            case 5:
                dayName = "목";
                break;
            case 6:
                dayName = "금";
                break;
            case 7:
                dayName = "토";
                break;    
        }

        return dayName;
    }

    /*
    * local time -> UTC time
    * */
    public static String localTimeToUtcTime(String dateTime) {
        String utcTime = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone timeZone = TimeZone.getDefault();

        try {
            Date parseDate = simpleDateFormat.parse(dateTime);
            long milliseconds = parseDate.getTime();
            int offset = timeZone.getOffset(milliseconds);
            utcTime = simpleDateFormat.format(milliseconds - offset);
            utcTime = utcTime.replace("+0000", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utcTime;
    }

    public static String getNotUtcTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmssXXX", Locale.getDefault());
        return simpleDateFormat.format(new Date().getTime() - (1000*60*60*9));
    }
}
