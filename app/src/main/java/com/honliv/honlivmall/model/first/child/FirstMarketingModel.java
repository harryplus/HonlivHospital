package com.honliv.honlivmall.model.first.child;

import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.engine.HomePostUtils;
import com.honliv.honlivmall.util.RxUtil;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstMarketingModel implements FirstContract.FirstMarketingModel {

    @Override
    public Observable<List<Product>> CountDownList() {
        Observable<List<Product>> result = HomePostUtils.CountDownList();
        return result;
    }
}
