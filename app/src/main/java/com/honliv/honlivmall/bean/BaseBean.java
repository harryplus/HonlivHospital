package com.honliv.honlivmall.bean;

/**
 * Created by Rodin on 2016/11/18.
 */
public class BaseBean<T> {
    private int id;
    private T result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
