package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * Created by Rodin on 2016/11/14.
 */
public class BaseResult<T> implements Serializable {
    private T result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    private String status;

}
