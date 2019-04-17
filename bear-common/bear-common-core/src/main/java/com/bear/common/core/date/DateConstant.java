package com.bear.common.core.date;

/**
 * Created by mby on 2019/4/17.
 */
public interface DateConstant {

    /**
     * 年周数
     */
    DateFormatImpl yyyyw = new DateFormatImpl("yyyyw");
    /**
     * 秒
     */
    DateFormatImpl second = new DateFormatImpl("ss");
    /**
     * 分钟
     */
    DateFormatImpl minute = new DateFormatImpl("mm");
    /**
     * 24小时
     */
    DateFormatImpl hour24 = new DateFormatImpl("HH");
    /**
     * 24小时
     */
    DateFormatImpl hhmm = new DateFormatImpl("HHmm");
    /**
     * 24小时
     */
    DateFormatImpl hhmmss = new DateFormatImpl("HHmmss");
    /**
     * 24小时
     */
    DateFormatImpl hh_mm_ss = new DateFormatImpl("HH:mm:ss");
    /**
     * 12小时
     */
    DateFormatImpl hour12 = new DateFormatImpl("hh");
    /**
     * 天
     */
    DateFormatImpl day = new DateFormatImpl("dd");
    /**
     * 月
     */
    DateFormatImpl month = new DateFormatImpl("MM");
    /**
     * 年
     */
    DateFormatImpl year = new DateFormatImpl("yyyy");
    /**
     * 年-月
     */
    DateFormatImpl yyyy_mm = new DateFormatImpl("yyyy-MM");
    /**
     * MM-dd
     */
    DateFormatImpl mm_dd = new DateFormatImpl("MM-dd");
    /**
     * 年-月-日
     */
    DateFormatImpl yyyy_mm_dd = new DateFormatImpl("yyyy-MM-dd");
    /**
     * 年月
     */
    DateFormatImpl yyyymm = new DateFormatImpl("yyyyMM");
    /**
     * 月中-周
     */
    DateFormatImpl MMWW = new DateFormatImpl("MMWW");
    /**
     * 年中第几周
     */
    DateFormatImpl yyyyww = new DateFormatImpl("yyyyww");
    /**
     * 年中第几周
     */
    DateFormatImpl WW = new DateFormatImpl("WW");
    /**
     * 年中第几周
     */
    DateFormatImpl ww = new DateFormatImpl("ww");
    /**
     * 年月日
     */
    DateFormatImpl yyyymmdd = new DateFormatImpl("yyyyMMdd");
    /**
     * 年月日时
     */
    DateFormatImpl yyyymmddhh = new DateFormatImpl("yyyyMMddHH");
    /**
     * 年月日时分
     */
    DateFormatImpl yyyymmddhhmm = new DateFormatImpl("yyyyMMddHHmm");
    /**
     * 年月日时分
     */
    DateFormatImpl yyyymmddhhmmss = new DateFormatImpl("yyyyMMddHHmmss");
    /**
     * 年-月-日 时:分:秒 毫秒
     */
    DateFormatImpl yyyymmddhhmmsssss = new DateFormatImpl("yyyyMMddHHmmssSSS");
    /**
     * 年月日 时分秒
     */
    DateFormatImpl yyyymmdd_hhmmss = new DateFormatImpl("yyyyMMdd HHmmss");
    /**
     * 年-月-日 时:分:秒
     */
    DateFormatImpl yyyy_mm_dd_hh_mm = new DateFormatImpl("yyyy-MM-dd HH:mm");
    /**
     * 年-月-日 时:分:秒
     */
    DateFormatImpl yyyy_mm_dd_hh_mm_ss = new DateFormatImpl("yyyy-MM-dd HH:mm:ss");
    /**
     * 年-月-日 时:分:秒 毫秒
     */
    DateFormatImpl yyyy_mm_dd_hh_mm_ss_sss = new DateFormatImpl("yyyy-MM-dd HH:mm:ss SSS");
}
