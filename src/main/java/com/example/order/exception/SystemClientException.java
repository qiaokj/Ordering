package com.example.order.exception;

import com.example.order.common.util.provider.ExceptionEnum;

public class SystemClientException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 错误说明
     */
    private String errorExplain;

    public SystemClientException(Integer errorCode, String errorMsg, String errorExplain) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorExplain = errorExplain;
    }

    public SystemClientException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.errorCode = exceptionEnum.getCode();
        this.errorMsg = exceptionEnum.getMsg();
        this.errorExplain = exceptionEnum.getExplain();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorExplain() {
        return errorExplain;
    }
}
