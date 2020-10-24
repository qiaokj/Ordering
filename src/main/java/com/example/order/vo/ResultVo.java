package com.example.order.vo;

import com.example.order.common.util.ConstantProvider;

import java.io.Serializable;

public class ResultVo<T> implements Serializable {

    public static ResultVo successWithNoData() {
        return new ResultVo(ConstantProvider.resultStatue.SUCCESS);
    }

    public static <T> ResultVo successWithData(T data) {
        return new ResultVo(ConstantProvider.resultStatue.SUCCESS, data);
    }

    public static ResultVo failedWithNoData() {
        return new ResultVo(ConstantProvider.resultStatue.FAIL);
    }

    public static <T> ResultVo failedWithData(T data) {
        return new ResultVo(ConstantProvider.resultStatue.FAIL, data);
    }

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态码说明
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    private ResultVo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultVo(ConstantProvider.resultStatue resultStatue) {
        this.code = resultStatue.getCode();
        this.msg = resultStatue.getMsg();
        this.data = null;
    }

    private ResultVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResultVo(ConstantProvider.resultStatue resultStatue, T data) {
        this.code = resultStatue.getCode();
        this.msg = resultStatue.getMsg();
        this.data = data;
    }

    private void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
