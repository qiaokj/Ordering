package com.example.order.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class OddNumberGenerator {

    /**
     * 第二位手机号的值
     */
    private static String[] PHONE_BIT = {"3", "4", "5", "7", "8"};

    /**
     * @param bits   自动生成数字字符串的长度
     * @param isZero 表明是否允许第一位为 0
     * @return
     */
    private static String generateRandomNumberStr(int bits, boolean isZero) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < bits; index++) {
            int val = -1;
            do {
                val = random.nextInt() % 9 + 1;
                if (index == 0 && (val < 0 || !isZero && val == 0)) {
                    val = -1;
                }
            } while (val < 0);

            builder.append(val);
        }

        String result = builder.toString();

        if (StringUtils.isNotBlank(result) && result.length() == bits) {
            return result;
        }

        return null;
    }

    /**
     * 从字符串数组中随机取出一个字符串
     *
     * @param values
     * @return
     */
    private static String getRandomStr(String[] values) {
        return values[(int) (Math.random() * values.length)];
    }

    /**
     * 随机生成机构代码
     *
     * @return
     */
    private static String getRandomAgencyCode() {
        StringBuilder builder = new StringBuilder();
        String provinceCode = ConstantProvider.ProvinceInfo.getRandomProvinceInfo().getProvinceCode();
        // 拼接省级代码
        if (StringUtils.isNotBlank(provinceCode)) {
            provinceCode = provinceCode.substring(0, 2);
            builder.append(provinceCode);
        }
        String cityLevelCode = getRandomStr(ConstantProvider.cityCode);
        builder.append(cityLevelCode);
        String countyLevelCode = getRandomStr(ConstantProvider.cityCode);
        builder.append(countyLevelCode);

        return builder.toString();
    }

    /**
     * 生成随机工商注册号
     *
     * @return
     */
    public static String generateIndustryId() {
        // 91331002MA28GEX11B
        StringBuilder builder = new StringBuilder(Objects.requireNonNull(generateRandomNumberStr(2, false)));

        // 拼接机构代码
        builder.append(getRandomAgencyCode());
        builder.append("MA");
        builder.append(Objects.requireNonNull(generateRandomNumberStr(2, true)));
        builder.append("GEX");
        builder.append(Objects.requireNonNull(generateRandomNumberStr(2, true)));
        builder.append("B");
        return builder.toString();
    }

    /**
     * 随机生成商家 ID
     *
     * @return
     */
    public static String generateBusinessId() {
        // B2020N8781
        StringBuilder builder = new StringBuilder("B");
        String year = CommonUtil.parseDateToFormatStr(new Date(), "yyyy");
        builder.append(year);
        builder.append("N");
        builder.append(generateRandomNumberStr(4, true));

        return builder.toString();
    }

    /**
     * 随机生成用户 ID
     *
     * @return
     */
    public static String generateCustomerId() {
        StringBuilder builder = new StringBuilder("C");
        String year = CommonUtil.parseDateToFormatStr(new Date(), "yyyy");
        builder.append(year);
        builder.append("N");
        builder.append(generateRandomNumberStr(4, true));
        return builder.toString();
    }

    /**
     * 随机生成产品 ID
     *
     * @return
     */
    public static String generateProductId() {
        StringBuilder builder = new StringBuilder("P");
        String year = CommonUtil.parseDateToFormatStr(new Date(), "yyyy");
        builder.append(year);
        builder.append("N");
        builder.append(generateRandomNumberStr(4, true));
        return builder.toString();
    }

    /**
     * 随机生成订单 ID
     * @return
     */
    public static String generateOrderId() {
        StringBuilder builder = new StringBuilder("O");
        String year = CommonUtil.parseDateToFormatStr(new Date(), "yyyy");
        builder.append(year);
        builder.append("N");
        builder.append(generateRandomNumberStr(4, true));
        return builder.toString();
    }

    /**
     * 随机生成订单明细 ID
     * @return
     */
    public static String generateOrderDetailId() {
        StringBuilder builder = new StringBuilder("OD");
        String year = CommonUtil.parseDateToFormatStr(new Date(), "yyyy");
        builder.append(year);
        builder.append("N");
        builder.append(generateRandomNumberStr(3, true));
        return builder.toString();
    }

    /**
     * 随机手机号
     * 第一位为 1，第二位必定为3或4或5或7或8，其他位置的可以为 0-9
     *
     * @return
     */
    public static String generatePhoneNumber() {

        StringBuilder builder = new StringBuilder("1");
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int newVal = -1;
            do {
                newVal = random.nextInt();
            } while (newVal < 0);
            builder.append(PHONE_BIT[newVal % PHONE_BIT.length]);
        }
        builder.append(generateRandomNumberStr(8, true));
        return builder.toString();
    }

    /**
     * 随机身份证号
     * 六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     *
     * @return
     */
    public static String generateIdentifyNumber() {
        StringBuilder builder = new StringBuilder("");

        builder.append(getRandomAgencyCode());
        Random random = new Random();

        int year = 0;
        int month = -1;
        int day = -1;

        String monthStr = "";
        String dayStr = "";

        String newDay = "";

        boolean flag = true;

        // 随机生成年份
        String yyyy = CommonUtil.parseDateToFormatStr(new Date(), "yyyy");
        int maxYear = Integer.parseInt(yyyy) - 18;
        do {
            if (flag = false) {
                flag = true;
                newDay = "";
                monthStr = "";
                dayStr = "";
            }

            do {
                year = 1900 + random.nextInt() % (maxYear - 1900 + 1);
                System.out.println("年 : " + year);
            } while (year < 1900 || year > maxYear);

            do {
                month = random.nextInt() % 13;
                System.out.println("月 : " + month);
            } while (month <= 0);
            if (month < 10) {
                monthStr = "0" + month;
            } else {
                monthStr = String.valueOf(month);
            }

            do {
                day = random.nextInt() % 32;
                System.out.println("日 : " + day);
            } while (day <= 0);

            if (day < 10) {
                dayStr = "0" + day;
            } else {
                dayStr = String.valueOf(day);
            }

            newDay = year + "-" + monthStr + "-" + dayStr;

            Date date = null;
            try {
                date = CommonUtil.parseStrToFormatDate(newDay, "yyyy-MM-dd");
            } catch (Exception e) {
            }

            flag = date != null;
            if (flag == true) {
                builder.append(String.valueOf(year)).append(monthStr).append(dayStr);
                builder.append(generateRandomNumberStr(4, true));
            }
        } while (!flag || builder.toString().length() != 18);


        return builder.toString();
    }
}
