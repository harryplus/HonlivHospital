package com.honliv.honlivmall.model.third.child;

import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.contract.ThirdContract;
import com.honliv.honlivmall.engine.CategoryPostUtils;
import com.honliv.honlivmall.engine.HomePostUtils;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class ThirdMainModel implements ThirdContract.ThirdMainModel {
    @Override
    public Observable<List<Category>> getServiceCategoryList() {
        HashMap<String, Object> map = new HashMap<>();
        Observable<List<Category>> result = CategoryPostUtils.CategoryList(map);
        return result;
    }
}
