package com.honliv.honlivmall.model.first.child;

import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.engine.HomePostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstBrandListModel implements FirstContract.FirstBrandListModel {
    @Override
    public Observable<List<Product>> ProductList(int brandId) {
        Observable<List<Product>> result = HomePostUtils.ProductList(brandId);
        return result;
    }
}
