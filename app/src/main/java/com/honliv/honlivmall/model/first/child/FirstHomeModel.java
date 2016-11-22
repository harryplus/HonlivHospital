package com.honliv.honlivmall.model.first.child;

import android.util.Log;

import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.engine.HomePostUtils;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstHomeModel implements FirstContract.FirstHomeModel {
    private static final String TAG = "FirstHomeModel";


    @Override
    public Observable<HomeInfo> getServiceHomeInfo(String s) {
        HashMap<String, Object> map = new HashMap<>();
        Observable<HomeInfo> result = HomePostUtils.HomeIndex(map);
        return result;
    }

    @Override
    public Observable<List<Product>> getServiceHomeMarketing() {
        Observable<List<Product>> result = HomePostUtils.CountDownList();
        return result;
    }
}
