package com.honliv.honlivmall.model.first.child;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.engine.CategoryPostUtils;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstProductDetailModel implements FirstContract.FirstProductDetailModel {
    @Override
    public Observable<Product> getServiceProductDetail(int pId) {
        Observable<Product> result = CategoryPostUtils.ProductDetail(pId, GloableParams.USERID);
        return result;
    }

    @Override
    public Observable<String> addProductFav(int pid) {
        HashMap<String, Object> map = new HashMap<>();
        Observable<String> result = CategoryPostUtils.ProductAddFav(GloableParams.USERID, pid);
        return result;
    }
}
