package com.example.order.common.util;

import org.apache.commons.lang3.StringUtils;

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

}
