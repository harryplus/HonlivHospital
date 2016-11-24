package com.honliv.honlivmall.engine;

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
}
