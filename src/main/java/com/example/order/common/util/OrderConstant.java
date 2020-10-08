package com.example.order.common.util;

import java.util.HashMap;
import java.util.Map;

public class OrderConstant {

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
