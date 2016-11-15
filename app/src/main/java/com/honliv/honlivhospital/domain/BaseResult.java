package com.honliv.honlivhospital.domain;

/**
 * Created by Rodin on 2016/11/14.
 */
public class BaseResult<T> {
    private T Data;
    private int StatusCode;
    private String Info;

    @Override
    public String toString() {
        return "BaseResult{" +
                "Data=" + Data +
                ", StatusCode=" + StatusCode +
                ", Info='" + Info + '\'' +
                '}';
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }


}
