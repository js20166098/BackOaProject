package com.js.common.util;

import com.js.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: 姜爽
 * @date: 2019/12/12 14:37
 * @description: 日期封装类
 * @version: V1.0
 */
@Slf4j
public class DateUtil {

    private static final String PATTERNDATETOSTRING = "yyyy/MM/dd";

    private static final String PATTERNDATETOSTRINGMIN = "yyyy/MM/dd HH:mm:ss";

    private static final String PATTERNHOURMON = "HH:mm";

    private static final String PATTERNDATETOSTRCANINTEGER = "yyyyMMdd";

    private DateUtil() {
        throw new IllegalStateException("DateUtil工具异常");
    }

    public static synchronized Date getDate(Date dateTemp) {
        if (dateTemp == null) {
            dateTemp = new Date();
        }
        return dateTemp;
    }

    /**
     * 将日期转化为自定义格式的字符串
     */
    public static String dateToStringByPattern(Date dateTemp, String pattern) {
        Date date = getDate(dateTemp);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * 将一个字符串转化为Date,自己指定输入格式
     */
    public static Date strToDateLongByPattern(String strDate, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 将日期转化为字符串(年/月/日) 8 位yyyy/MM/dd
     */
    public static String dateToString(Date dateTemp) {
        Date date = getDate(dateTemp);
        return dateToStringByPattern(date, PATTERNDATETOSTRING);
    }

    /**
     * 将日期转化为字符串(年,月,日,时,分,秒)yyyy/MM/dd HH:mm:ss
     */
    public static String dateToStringMin(Date dateTemp) {
        Date date = getDate(dateTemp);
        return dateToStringByPattern(date, PATTERNDATETOSTRINGMIN);
    }

    /**
     * 将日期转为8位字符串(年,月,日)結是8位字符串 例如（20151211）
     */
    public static String dateToStrCanInteger(Date dateTemp) {
        Date date = getDate(dateTemp);
        return dateToStringByPattern(date, PATTERNDATETOSTRCANINTEGER);
    }

    /**
     * 获取自定义格式的日期字符串
     */
    public static String getDateByPattern(String pattern) {
        Date date = getDate(null);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * 根据时间分钟字符串转化为Date
     */
    public static Date getHourMon(String dateStr) {
        return strToDateLongByPattern(dateStr, PATTERNHOURMON);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days;
        try {
            days = (int)((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        } catch (Exception e) {
            log.info("日期强转失败{}", e);
            throw new SystemException("数据强转失败");
        }
        return days;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
}
