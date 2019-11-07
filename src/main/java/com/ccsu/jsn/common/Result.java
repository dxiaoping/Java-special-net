package com.ccsu.jsn.common;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-10-29 10:59
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<T>(msg);
    }

    private Result(T data) {
        this.msg = "success";
        this.data = data;
    }

    private Result(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
