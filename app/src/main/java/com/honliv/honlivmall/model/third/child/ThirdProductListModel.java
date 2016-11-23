package com.honliv.honlivmall.model.third.child;

import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.bean.ProductListFilter;
import com.honliv.honlivmall.contract.ThirdContract;
import com.honliv.honlivmall.engine.CategoryPostUtils;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class ThirdProductListModel implements ThirdContract.ThirdProductListModel  {
    @Override
    public Observable<ProductListFilter> ProductList(int categoryId, String orderBy, int page, int pageNum) {
        Observable<ProductListFilter> result = CategoryPostUtils.ProductList(categoryId,orderBy,page,pageNum);
        return result;
    }
}
