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
    public static Date parseStrToFormatDate(String dateStr, String format) throws ParseException {

        if (StringUtils.isBlank(dateStr)) {
            throw new NullPointerException("未指定解析字符串");
        }
        if (StringUtils.isBlank(format)) {
            format = ConstantProvider.DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        // 严格解析时间
        dateFormat.setLenient(false);
        return dateFormat.parse(dateStr);
    }

}
