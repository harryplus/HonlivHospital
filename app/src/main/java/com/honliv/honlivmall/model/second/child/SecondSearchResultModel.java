package com.honliv.honlivmall.model.second.child;

import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.SecondContract;
import com.honliv.honlivmall.engine.SearchPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class SecondSearchResultModel implements SecondContract.SecondSearchResultModel  {
    @Override
    public Observable<List<Product>> getSeaviceSearchProductList(String searchKey, String orderby, int start) {
        Observable<List<Product>> result = SearchPostUtils.SearchProductList(searchKey,orderby,start,30);
        return result;
    }
}
