package com.honliv.honlivmall.model.second.child;

import com.honliv.honlivmall.contract.SecondContract;
import com.honliv.honlivmall.engine.HomePostUtils;
import com.honliv.honlivmall.engine.SearchPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class SecondMainModel  implements SecondContract.SecondMainModel {
    @Override
    public Observable<List<String>> getSeaviceSearchKeyList() {
        Observable<List<String>> result = SearchPostUtils.HotKeyword();
        return result;
    }
}
