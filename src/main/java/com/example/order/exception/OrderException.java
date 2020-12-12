package com.example.order.exception;

import com.example.order.common.util.provider.ExceptionEnum;

public class OrderException extends SystemServerException {
    public OrderException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }

    public OrderException(Integer errorCode, String errorMsg, String errorExplain) {
        super(errorCode, errorMsg, errorExplain);
    }
}
