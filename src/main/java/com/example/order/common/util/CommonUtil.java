package com.example.order.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    /**
     * 自动解析时间为指定格式的字符串
     * @param date
     * @param format
     * @return
     */
    public static String parseDateToFormatStr(Date date, String format) {

        if (date == null) {
            date = new Date();
        }
        if (StringUtils.isBlank(format)) {
            format = ConstantProvider.DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        // 严格解析时间
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }

    /**
     * 自动解析指定格式的时间字符串
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parseStrToFormatDate(String dateStr, String format) {

        if (StringUtils.isBlank(dateStr)) {
            throw new NullPointerException("未指定解析字符串");
        }
        if (StringUtils.isBlank(format)) {
            format = ConstantProvider.DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        // 严格解析时间
        dateFormat.setLenient(false);

        Date date = null;

        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 比较时间是否比指定提前
     * @param date
     * @param target
     * @return
     */
    public static Boolean checkDateBeforeTarget(Date date, Date target) {

        if (date == null || target == null) {
            return false;
        }

        return date.getTime() < target.getTime();
    }

    /**
     *
     * @param date
     * @param target
     * @return
     */
    public static Boolean checkDateAfterTarget(Date date, Date target) {

        if (date == null || target == null) {
            return false;
        }

        return date.getTime() > target.getTime();
    }

    /**
     *
     * @param dateStr
     * @param targetStr
     * @param format
     * @return
     */
    public static Boolean checkDateBeforeTarget(String dateStr, String targetStr, String format) {

        Date date = parseStrToFormatDate(dateStr, format);
        Date targetDate = parseStrToFormatDate(targetStr, format);

        return checkDateBeforeTarget(date, targetDate);
    }

    /**
     *
     * @param dateStr
     * @param targetStr
     * @param format
     * @return
     */
    public static Boolean checkDateAfterTarget(String dateStr, String targetStr, String format) {

        Date date = parseStrToFormatDate(dateStr, format);
        Date targetDate = parseStrToFormatDate(targetStr, format);

        return checkDateAfterTarget(date, targetDate);
    }

}
