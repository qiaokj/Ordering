package com.example.order.exception;

import com.example.order.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常, 返回指定格式的数据
 */
@RestControllerAdvice
public class SystemExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(SystemExceptionHandler.class);

    /**
     * 处理系统级别异常
     *
     * @return
     */
    @ExceptionHandler({SystemServerException.class})
    public ResultVo businessException(SystemServerException e) {
        log.error("系统业务异常：{}", e.getErrorMsg(), e);

        return ResultVo.failedWithNoData(e);
    }

    /**
     * 处理客户端输入级别异常
     *
     * @return
     */
    @ExceptionHandler({SystemClientException.class})
    public ResultVo clientException(SystemClientException e) {
        log.error("系统业务输入数据异常：{}", e.getErrorMsg(), e);

        return ResultVo.failedWithNoData(e);
    }

    /***
     * 处理未知异常
     * @return
     */
    @ExceptionHandler({SystemOtherException.class})
    public ResultVo otherException(SystemOtherException e) {
        log.error("系统未知异常：{}", e.getErrorMsg(), e);

        return ResultVo.failedWithNoData(e);
    }


    @ExceptionHandler({Exception.class})
    public ResultVo runTimeException(Exception e) {

        log.error("系统未知异常：{}", e.getMessage(), e);

        return ResultVo.failedWithNoData(e);
    }

}
