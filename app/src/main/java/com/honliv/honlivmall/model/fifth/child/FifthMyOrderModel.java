package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.OrderPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthMyOrderModel implements FifthContract.FifthMyOrderModel {
    @Override
    public Observable<List<OrderInfo>> OrderList(int userId, int page, int pageNum) {
        Observable<List<OrderInfo>> result = OrderPostUtils.OrderList(userId,page,pageNum);
        return result;
    }

    @Override
    public Observable<Boolean> CancelOrder(int userid, int orderid) {
        Observable<Boolean> result = OrderPostUtils.CancelOrder(userid,orderid);
        return result;
    }
}
