package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.HxtBean;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.HxtPostUtils;
import com.honliv.honlivmall.engine.OrderPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyOrderDetailModel implements FifthContract.FifthMyOrderDetailModel  {
    @Override
    public Observable<OrderInfo> OrderDetail(int userid, int orderId) {
        Observable<OrderInfo> result = OrderPostUtils.OrderDetail(userid,orderId);
        return result;
    }

    @Override
    public Observable<Boolean> CancelOrder(int userid, int orderId) {
        Observable<Boolean> result = OrderPostUtils.CancelOrder(userid,orderId);
        return result;
    }
}
