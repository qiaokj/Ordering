package com.example.order.common.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantProvider {

    // 默认时间格式
    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_FORMAT = DEFAULT_DATE_FORMAT;

    public static String TIME_FORMAT = "yyyy-MM-dd";

    public static Integer BUSINESS_INIT_SCORE = 1;

    /**
     * 请求结果返回状态码
     */
    public enum resultStatue {
        SUCCESS(200, "SUCCESS"),
        FAIL(400, "FAILED"),
        ;

        private int code;
        private String msg;

        private resultStatue(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 市、县级代码
     */
    public static String[] cityCode = {"00", "01", "02", "10", "13", "03"};

    /**
     * 省份信息
     */
    public enum ProvinceInfo {
        BJ("110000", "北京市"),
        TJ("120000", "天津市"),
        HB_13("130000", "河北省"),
        SX("140000", "山西省"),
        NM("150000", "内蒙古自治区"),
        LN("210000", "辽宁省"),
        JL("220000", "吉林省"),
        HL("230000", "黑龙江省"),
        SH("310000", "上海市"),
        JS("320000", "江苏省"),
        ZJ("330000", "浙江省"),
        AH("340000", "安徽省"),
        FJ("350000", "福建省"),
        JX("360000", "江西省"),
        SD("370000", "山东省"),
        HA("410000", "河南省"),
        HB("420000", "湖北省"),
        HN("430000", "湖南省"),
        GD("440000", "广东省"),
        GX("450000", "广西壮族自治区 "),
        HI("460000", "海南省"),
        SC("510000", "四川省"),
        GZ("520000", "贵州省"),
        YN("530000", "云南省"),
        XZ("540000", "西藏自治区"),
        CQ("500000", "重庆市"),
        SN("610000", "陕西省"),
        GS("620000", "甘肃省"),
        QH("630000", "青海省"),
        NX("640000", "宁夏回族自治区"),
        XJ("650000", "新疆维吾尔自治区"),
        HK("810000", "香港特别行政区"),
        Mo("820000", "澳门特别行政区"),
        TW("830000", "台湾省");

        private String provinceCode;
        private String provinceName;

        private ProvinceInfo(String code, String name) {
            this.provinceCode = code;
            this.provinceName = name;
        }

        public static ProvinceInfo getRandomProvinceInfo() {
            ProvinceInfo[] provinceInfos = {BJ, TJ, HB_13, SX, NM, LN, JL, HL, SH, JS, ZJ, AH, FJ, JX, SD, HA, HB, HN, GD,
                    GX, HI, SC, GZ, YN, XZ, CQ, SN, GS, QH, NX, XJ, HK, Mo, TW,};
            return provinceInfos[(int) (Math.random() * provinceInfos.length)];
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public String getProvinceName() {
            return provinceName;
        }
    }

    /**
     * 支付方式
     */
    public enum PayStyle {
        // 在线支付
        ONLINE_PAY(1);

        private int code;

        private PayStyle(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public enum ProductStatue {
        SHELVE(0),  // 下架
        ONSALE(1),  // 在售
        PRESELL(2); // 预售

        private int statue;

        private ProductStatue(int statue) {
            this.statue = statue;
        }

        public int getStatue() {
            return statue;
        }
    }

    /**
     * 支付状态
     */
    public enum PayStatus {

        // 等待支付
        WAIT_PAYMENT(0, "WAIT_PAYMENT"),
        // 支付成功
        SUCCESS_PAYMENT(1, "SUCCESS_PAYMENT"),
        // 支付失败
        FAILED_PAYMENT(2, "FAILED_PAYMENT"),
        // 超时支付
        TIMEOUT_PAYMENT(3, "TIMEOUT_PAYMENT");

        private int code;
        private String codeStr;

        private PayStatus(int code, String codeStr) {
            this.code = code;
            this.codeStr = codeStr;
        }

        private PayStatus() {
            this.code = 0;
            this.codeStr = "WAIT_PAYMENT";
        }

        public int getCode() {
            return code;
        }

        public String getCodeStr() {
            return codeStr;
        }
    }

    /**
     * 性别常量
     */
    public enum CustomerGender {
        // 男性
        MALE(1, "MALE"),
        // 女性
        FEMALE(0, "FEMALE");

        private int code;
        private String codeStr;

        private CustomerGender(int code, String codeStr) {
            this.code = code;
            this.codeStr = codeStr;
        }

        public int getCode() {
            return code;
        }

        public String getCodeStr() {
            return codeStr;
        }
    }

    /**
     * 是否可用常量
     */
    public enum Useable {

        // 有效
        VALID(1, "VALID"),
        // 无效
        INVALID(0, "INVALID");

        private int code;
        private String codeStr;

        private Useable(int code, String codeStr) {
            this.code = code;
            this.codeStr = codeStr;
        }

        public int getCode() {
            return code;
        }

        public String getCodeStr() {
            return codeStr;
        }
    }

}
