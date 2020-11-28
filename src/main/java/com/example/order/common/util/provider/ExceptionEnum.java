package com.example.order.common.util.provider;

public enum ExceptionEnum {

    /**
     * 商品不存在
     */
    PRODUCT_NO_EXIST(400, "PRODUCT_NO_EXIST", "商品不存在"),
    ORDER_PARAM_INVALID(400, "ORDER_PARAM_INVALID", "订单参数异常"),
    ORDER_PROCESS_ERROR(400, "ORDER_PROCESS_ERROR", "订单处理异常"),
    USER_INFO_ERROR(400, "USER_INFO_ERROR", "用户信息错误"),
    BUSINESS_INFO_ERROR(400, "BUSINESS_INFO_ERROR", "商户信息错误"),
    PRODUCT_INFO_ERROR(400, "PRODUCT_INFO_ERROR", "商品信息错误"),
    PRODUCT_NO_STOCK(400, "PRODUCT_NO_STOCK", "商品无剩余库存")
    ;

    /**
     * 异常代码
     */
    private Integer code;
    /**
     * 异常短语
     */
    private String msg;
    /**
     * 异常说明
     */
    private String explain;

    ExceptionEnum(Integer code, String msg, String explain) {
        this.code = code;
        this.msg = msg;
        this.explain = explain;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getExplain() {
        return explain;
    }
}
