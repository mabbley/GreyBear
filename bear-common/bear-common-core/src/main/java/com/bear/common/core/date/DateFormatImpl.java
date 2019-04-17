package com.bear.common.core.date;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mby on 2019/4/17.
 */
public class DateFormatImpl extends SimpleDateFormat{

    public DateFormatImpl() {
        super();
    }

    public DateFormatImpl(String pattern) {
        super(pattern);
    }

    public DateFormatImpl(String pattern, Locale locale) {
        super(pattern, locale);
    }

    public DateFormatImpl(String pattern, DateFormatSymbols formatSymbols) {
        super(pattern, formatSymbols);
    }

    @Override
    public String toString() {
        return super.toPattern();
    }

    public synchronized final String formatCurrent() {
        return super.format(new Date());
    }

    public synchronized int formatIntCurrent() {
        return Integer.parseInt(replace(super.format(new Date())));
    }

    public synchronized Long formatLongCurrent() {
        return Long.parseLong(replace(super.format(new Date())));
    }

    public synchronized long formatLong(Date date) {
        return Long.parseLong(replace(super.format(date)));
    }

    public synchronized String format(Long millis) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(millis);
        return super.format(instance.getTime());
    }

    public synchronized long formatLong(Long millis) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(millis);
        return Long.parseLong(replace(super.format(instance.getTime())));
    }

    public synchronized int formatInt(Long millis) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(millis);
        return Integer.parseInt(replace(super.format(instance.getTime())));
    }

    public synchronized int formatInt(Date date) {
        return Integer.parseInt(replace(super.format(date)));

    }

    public synchronized Date parseCurrent() {
        return parse(formatCurrent());
    }

    @Override
    public synchronized Date parse(String date) {
        Date source = null;
        try {
            source = super.parse(date);
        } catch (ParseException e) {
        }
        return source;
    }

    public synchronized Date parse(Integer date) {
        Date source = null;
        try {
            source = super.parse(date.toString());
        } catch (ParseException e) {
        }
        return source;
    }

    public synchronized Date parse(Long date) {
        Date source = null;
        try {
            source = super.parse(date.toString());
        } catch (ParseException e) {

        }
        return source;
    }

    public synchronized String replace(String date) {
        return date.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
    }
}
