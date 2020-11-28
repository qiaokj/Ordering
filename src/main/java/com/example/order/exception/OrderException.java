package com.example.order.exception;

import com.example.order.common.util.provider.ExceptionEnum;

public class OrderException extends BusinessException {
    public OrderException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }

    public OrderException(Integer errorCode, String errorMsg, String errorExplain) {
        super(errorCode, errorMsg, errorExplain);
    }
}
