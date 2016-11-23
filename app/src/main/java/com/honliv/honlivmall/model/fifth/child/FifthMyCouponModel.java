package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.UserPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthMyCouponModel implements FifthContract.FifthMyCouponModel {
    @Override
    public Observable<List<CouponInfo>> getServiceCouponInfo(int userId, int start, int i, int i1) {
        Observable<List<CouponInfo>> result = UserPostUtils.MyCunpon(userId,start,i,i1);
        return result;
    }
}
