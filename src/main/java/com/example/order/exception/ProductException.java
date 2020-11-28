package com.example.order.exception;

import com.example.order.common.util.provider.ExceptionEnum;

/**
 * 商品异常
 */
public class ProductException extends BusinessException {


    public ProductException(Integer errorCode, String errorMsg, String errorExplain) {
        super(errorCode, errorMsg, errorExplain);
    }

    public ProductException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
