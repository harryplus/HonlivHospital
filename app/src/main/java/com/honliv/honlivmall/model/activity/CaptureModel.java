package com.honliv.honlivmall.model.activity;

import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.contract.ActivityContract;
import com.honliv.honlivmall.engine.HomePostUtils;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/21.
 */
public class CaptureModel implements ActivityContract.CaptureModel {
    @Override
    public Observable<Integer> GetOrder(String contentStr) {
        Observable<Integer> result = HomePostUtils.GetOrder(contentStr);
        return result;
    }
}
