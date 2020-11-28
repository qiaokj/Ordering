package com.example.order.exception;

import com.example.order.common.util.provider.ExceptionEnum;

/**
 * 异常基类
 */
public abstract class BusinessException extends RuntimeException {

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

    public BusinessException(Integer errorCode, String errorMsg, String errorExplain) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorExplain = errorExplain;
    }

    public BusinessException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.errorCode = exceptionEnum.getCode();
        this.errorMsg = exceptionEnum.getMsg();
        this.errorExplain = exceptionEnum.getExplain();
    }

}
