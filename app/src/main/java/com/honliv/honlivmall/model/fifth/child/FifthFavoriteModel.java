package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.UserPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthFavoriteModel implements FifthContract.FifthFavoriteModel {
    @Override
    public Observable<List<Product>> updateServicePassword(int userid, int start, int i) {
        Observable<List<Product>> result = UserPostUtils.MyFavorList(userid,start,i);
        return result;
    }

    @Override
    public Observable<Boolean> CancelFav(int userid, int favId) {
        Observable<Boolean> result = UserPostUtils.CancelFav(userid,favId);
        return result;
    }
}
