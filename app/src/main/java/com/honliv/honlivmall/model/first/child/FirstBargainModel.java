package com.honliv.honlivmall.model.first.child;

import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.engine.HomePostUtils;
import com.honliv.honlivmall.util.RxUtil;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstBargainModel implements FirstContract.FirstBargainModel {
    @Override
    public Observable<List<Product>> getServiceProductLS() {
        HashMap<String, Object> map = new HashMap<>();
        Observable<HomeInfo> result = HomePostUtils.HomeIndex(map);
        return result.map(bean -> bean.getCheapproductlist());
    }
}
