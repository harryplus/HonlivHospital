package com.honliv.honlivmall.engine;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/24.
 */
public class OrderPostUtils {
    public static Observable<List<OrderInfo>> OrderList(int userId, int page, int pageNum) {
        HashMap<String, Object> postMap = Utils.getBaseMap("OrderList");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("UserID", userId);
        parames.put("page", page);
        parames.put("pageNum", pageNum);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ListOrderInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());

    }

    public static Observable<OrderInfo> OrderDetail(int userid, int orderId) {
        HashMap<String, Object> postMap = Utils.getBaseMap("OrderDetail");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("UserID", userid);//UserID
        parames.put("id", orderId);//UserID
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).OrderInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Boolean> CancelOrder(int userId, int orderId) {
        HashMap<String, Object> postMap = Utils.getBaseMap("OrderDetail");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("UserID", userId);
        parames.put("Id", orderId);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).String(postMap).map(bean -> {
            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
                return true;
            }
            return false;
        }).compose(RxUtil.rxSchedulerHelper());
    }
}
