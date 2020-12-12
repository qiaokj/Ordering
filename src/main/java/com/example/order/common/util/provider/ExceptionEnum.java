package com.example.order.common.util.provider;

public enum ExceptionEnum {

    // 系统级 Error
    /**
     * 商品不存在
     */
    SYSTEM_SERVER_EXCEPTION(500, "SYSTEM_SERVER_EXCEPTION", "系统服务端异常"),
    PRODUCT_NO_EXIST(500, "PRODUCT_NO_EXIST", "商品不存在"),
    ORDER_PARAM_INVALID(500, "ORDER_PARAM_INVALID", "订单参数异常"),
    ORDER_PROCESS_ERROR(500, "ORDER_PROCESS_ERROR", "订单处理异常"),
    ORDER_NOT_EXIST_ERROR(500, "ORDER_NOT_EXIST_ERROR", "订单查询异常"),
    ORDERDETAIL_NOT_EXIST_ERROR(500, "ORDERDETAIL_NOT_EXIST_ERROR", "订单明细查询异常"),
    USER_INFO_ERROR(500, "USER_INFO_ERROR", "用户信息错误"),
    USER_INFO_NOT_EXIST_ERROR(500, "USER_INFO_NOT_EXIST_ERROR", "用户信息错误"),
    BUSINESS_INFO_ERROR(500, "BUSINESS_INFO_ERROR", "商户信息错误"),
    PRODUCT_INFO_ERROR(500, "PRODUCT_INFO_ERROR", "商品信息错误"),
    PRODUCT_NO_STOCK(500, "PRODUCT_NO_STOCK", "商品无剩余库存"),

    PARAM_INVALID_ERROR(500, "PARAM_INVALID_ERROR", "参数异常"),

    // 用户级 Error
    SYSTEM_CLIENT_EXCEPTION(400, "SYSTEM_CLIENT_EXCEPTION", "系统客户端数据异常"),


    // 未知异常
    SYSTEM_OTHER_EXCEPTION(999, "SYSTEM_OTHER_EXCEPTION", "系统未知异常"),
    ;

    /**
     * 异常代码
     */
    private final Integer code;
    /**
     * 异常短语
     */
    private final String msg;
    /**
     * 异常说明
     */
    private final String explain;

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
