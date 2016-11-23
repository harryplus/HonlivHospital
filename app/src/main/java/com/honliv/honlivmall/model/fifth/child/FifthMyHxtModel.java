package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.HxtBean;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.HxtPostUtils;
import com.honliv.honlivmall.engine.UserPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthMyHxtModel implements FifthContract.FifthMyHxtModel {
    @Override
    public Observable<List<HxtBean>> getData() {
        Observable<List<HxtBean>> result = HxtPostUtils.HxtUserList();
        return result;
    }
}
