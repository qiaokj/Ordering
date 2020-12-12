package com.example.order.exception;

import com.example.order.common.util.provider.ExceptionEnum;

public class CustomerException extends SystemServerException {
    public CustomerException(Integer errorCode, String errorMsg, String errorExplain) {
        super(errorCode, errorMsg, errorExplain);
    }

    public CustomerException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
