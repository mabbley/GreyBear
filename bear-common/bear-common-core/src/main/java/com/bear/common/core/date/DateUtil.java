package com.bear.common.core.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mby on 2019/4/17.
 */
public class DateUtil implements DateConstant {

    private static final String ZERO = "0";

    private DateUtil() {

    }

    public static Date fromLocalDate(LocalDate date) {

        Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDate fromDate(Date date) {

        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    public static int addWork(int i, int date) {

        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.yyyymmdd.parse(date));
        c.add(Calendar.WEDNESDAY, i);
        return yyyymmdd.formatInt(c.getTime());
    }

    public static int addMonth(int i, int date) {

        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.yyyymmdd.parse(date));
        c.add(Calendar.MONTH, i);
        return yyyymmdd.formatInt(c.getTime());
    }

    public static Date addWork(int i, Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEDNESDAY, i);
        return c.getTime();
    }

    public static Date addMonth(int i, Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, i);
        return c.getTime();
    }

    public static Date addDay(int i, Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, i);
        return c.getTime();
    }

    public static int addDay(int i, int date) {

        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.yyyymmdd.parse(date));
        c.add(Calendar.DAY_OF_YEAR, i);
        return DateUtil.yyyymmdd.formatInt(c.getTime());
    }

    public static int addMinute(int i, int MINUTE) {

        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.hhmm.parse(MINUTE));
        c.add(Calendar.MINUTE, i);
        return DateUtil.hhmm.formatInt(c.getTime());
    }

    public static int addMinute(int i, String MINUTE) {

        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.hhmm.parse(MINUTE));
        c.add(Calendar.MINUTE, i);
        return DateUtil.hhmm.formatInt(c.getTime());
    }

    public static Date addMinute(int i, Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, i);
        return c.getTime();
    }

    public static Date addSecond(int i, Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, i);
        return c.getTime();
    }

    public static Date add(Date date, int field, int i) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, i);
        return c.getTime();
    }

    /**
     * 判断指定的年份是否是闰年
     *
     * @param year 指定的年份
     * @return boolean 如果指定的年份是闰年则返回true，否则返回false
     */
    public synchronized static boolean isLeapYear(int year) {

        boolean leap = false;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            leap = true;
        }
        return leap;
    }

    /**
     * 返回当天的日期
     *
     * @return String 当天的日期
     */
    public synchronized static String getCurrentDate() {

        StringBuffer buffer = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        buffer.append(calendar.get(Calendar.YEAR));
        buffer.append("-");
        // 注意：月份从０算起
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month < 10) {
            buffer.append(ZERO);
        }
        buffer.append(month);
        buffer.append("-");
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            buffer.append(ZERO);
        }
        buffer.append(calendar.get(Calendar.DAY_OF_MONTH));
        return buffer.toString();
    }

    /**
     * 返回当时的时间
     *
     * @return String 当时的时间
     */
    public synchronized static String getCurrentTime() {

        StringBuffer buffer = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        buffer.append(calendar.get(Calendar.HOUR_OF_DAY));
        buffer.append(":");
        int minute = calendar.get(Calendar.MINUTE);
        if (minute < 10) {
            buffer.append(ZERO);
        }
        buffer.append(minute);
        buffer.append(":");
        int second = calendar.get(Calendar.SECOND);
        if (second < 10) {
            buffer.append(ZERO);
        }
        buffer.append(second);
        return buffer.toString();
    }

    /**
     * 返回当时时间的长格式形式
     *
     * @return String 当时时间的长格式形式
     */
    public synchronized static String getLongTime() {

        StringBuffer time = new StringBuffer();
        time.append(getCurrentDate());
        time.append(" ");
        time.append(getCurrentTime());
        return time.toString();
    }

    /**
     * @return int 今天是一年中的第几个季度
     */
    public synchronized static int getQuarter() {

        return quarter(month.formatIntCurrent());
    }

    /**
     * @param month 月份
     * @return int 指定月份是一年的第几个季度
     */
    public synchronized static int getQuarter(int month) {

        return quarter(month);
    }

    /**
     * @param date 日期
     * @return int 指定日期是一年的第几个季度
     */
    public synchronized static int getQuarter(Date date) {

        return quarter(month.formatInt(date));
    }

    /**
     * @param date 日期(yyyy-mm-dd)
     * @return int 指定日期是一年的第几个季度
     */
    public synchronized static int getQuarter(String date) {

        return quarter(month.formatInt(month.parse(date)));
    }

    /**
     * 返回今天是一年中的第几个星期
     *
     * @return int 今天是一年中的第几个星期
     */
    public synchronized static int getWeekOfYear() {

        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public synchronized static int getWeek(Date date) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            return 7;
        }
        return dayOfWeek;
    }

    public synchronized static int differDaysMonday(Date date) {

        int differDays = getWeek(date) - 1;
        return differDays;

    }

    /**
     * 返回 指定日期与本周日相差的天数
     *
     * @param date 日期
     * @return int 天
     */
    public synchronized static int differDaysSunday(Date date) {
        int differDays = 7 - getWeek(date);
        return differDays;
    }

    /**
     * 是否同一个星期
     *
     * @param beginDate yyyy-mm-dd
     * @param endDate   yyyy-mm-dd
     * @return boolean 是否
     */
    public static boolean isSameWeek(int beginDate, int endDate) {
        return isSameWeek(yyyymmdd.parse(beginDate), yyyymmdd.parse(endDate));

    }

    public static boolean isSameWeek(float beginDate, float endDate) {
        return isSameWeek((int) beginDate, (int) endDate);
    }

    /**
     * 是否同一个星期
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return boolean 是否
     */
    public static boolean isSameWeek(Date beginDate, Date endDate) {

        try {
            if (year.formatInt(beginDate) == year.formatInt(beginDate)) {
                Calendar calendar = Calendar.getInstance();// 获取Calendar实例
                calendar.setTime(beginDate);
                int begin = calendar.get(Calendar.WEEK_OF_YEAR);
                calendar.setTime(endDate);
                int end = calendar.get(Calendar.WEEK_OF_YEAR);
                return begin == end;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 是否同一个月
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return boolean 是否
     */
    public static boolean isSameMonth(int beginDate, int endDate) {

        try {
            return isSameMonth(yyyymmdd.parse(beginDate), yyyymmdd.parse(endDate));
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isSameMonth(float beginDate, float endDate) {

        return isSameMonth((int) beginDate, (int) endDate);
    }

    /**
     * 是否同一个月
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return boolean 是否
     */
    public static boolean isSameMonth(Date beginDate, Date endDate) {

        try {
            return yyyy_mm.format(beginDate).equals(yyyy_mm.format(endDate));
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 今年是否是闰年
     *
     * @return boolean 是否
     */
    public static boolean isLeapYear() {

        return isLeapYear(year.formatIntCurrent());
    }

    /**
     * @param date 日期
     * @return string 返回月初
     */
    public static String getFirstMonth(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return yyyy_mm_dd.format(c.getTime());

    }

    /**
     * @param date 日期
     * @return string 返回月初
     */
    public static int getFirstMonth(int date) {

        Calendar c = Calendar.getInstance();
        c.setTime(yyyymmdd.parse(date));
        c.set(Calendar.DAY_OF_MONTH, 1);
        return yyyymmdd.formatInt(c.getTime());

    }

    /**
     * 返回上月第一天
     *
     * @param date 数字格式日期
     * @return int 天
     */
    public static int getLastMonthFirst(int date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(yyyymmdd.parse(date));
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DATE, 1);
        return yyyymmdd.formatInt(cal.getTime());
    }

    /**
     * 返回月末
     *
     * @param date 数字格式日期
     * @return int 天
     */
    public static int getEndMonth(int date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(yyyymmdd.parse(date));
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);
        return yyyymmdd.formatInt(cal.getTime());
    }

    /**
     * 返回月末
     *
     * @param date 数字格式日期
     * @return Date 日期
     */
    public static Date getEndMonth(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static String[] getWeekStartEndDay(int year, int month, int week) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        Date lastDay = getEndMonth(calendar.getTime());
        Date tempDate = null;
        while (calendar.getTime().before(lastDay) || calendar.getTime().equals(lastDay)) {
            if (calendar.get(Calendar.WEEK_OF_MONTH) == week) {
                tempDate = calendar.getTime();
                break;
            }
            calendar.add(Calendar.DATE, 1);
        }
        String start_end_date[] = new String[2];
        if (tempDate != null) {
            start_end_date[0] = getMonday(tempDate);
            start_end_date[1] = getSunday(tempDate);

        }
        System.out.println("周一：" + start_end_date[0]);
        System.out.println("周日：" + start_end_date[1]);
        return start_end_date;
    }

    /**
     * 返回周一
     *
     * @param date 数字格式日期
     * @return int 日期
     */
    public static int getMonday(int date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(yyyymmdd.parse(date));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return DateUtil.yyyymmdd.formatInt(cal.getTime());
    }

    /**
     * 返回周一
     *
     * @param date 日期
     * @return String 日期
     */
    public static String getMonday(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return DateUtil.yyyy_mm_dd.format(cal.getTime());
    }

    /**
     * 星期日
     *
     * @param date 日期
     * @return int 日期
     */
    public static int getSunday(int date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(yyyymmdd.parse(date));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DATE, 6);
        return DateUtil.yyyymmdd.formatInt(cal.getTime());
    }

    /**
     * 星期日
     *
     * @param date 日期
     * @return String 日期
     */
    public static String getSunday(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DATE, 6);
        return DateUtil.yyyy_mm_dd.format(cal.getTime());
    }

    /**
     * 返回上周一
     *
     * @param date 日期
     * @return int 日期
     */
    public static int getLastMonday(int date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(yyyymmdd.parse(date));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DATE, -7);
        return yyyymmdd.formatInt(cal.getTime());
    }

    /**
     * 返回上周一
     *
     * @param date 日期
     * @return String 日期
     */
    public static String getLastMonday(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DATE, -7);
        return DateUtil.yyyy_mm_dd.format(cal.getTime());
    }

    /**
     * 取得这个季度的第一天
     *
     * @param date 日期
     * @return Date 日期
     */
    public static Date getFirstDayOfQuarter(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) / 3 * 3);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 取得这个季度的第一天
     *
     * @return Date 日期
     */
    public static Date getFirstDayOfQuarter() {

        Calendar c = Calendar.getInstance();
        c.setTime(getFirstDayOfQuarter(new Date()));
        c.set(Calendar.MONTH, -1);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) / 3 * 3);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    private static int quarter(int month) {
        switch (month) {
            case 1:
            case 2:
            case 3:
                return 1;
            case 4:
            case 5:
            case 6:
                return 2;
            case 7:
            case 8:
            case 9:
                return 3;
            case 10:
            case 11:
            case 12:
                return 4;
        }
        return 0;
    }
}
