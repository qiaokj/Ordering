package com.example.order.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class OddNumberGenerator {

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
}
