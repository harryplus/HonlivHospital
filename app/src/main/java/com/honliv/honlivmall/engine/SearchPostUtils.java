package com.honliv.honlivmall.engine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.HxtBean;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Rodin on 2016/11/21.
 */
public class SearchPostUtils {
    public static Observable<List<String>> HotKeyword() {
        HashMap<String, Object> postMap = Utils.getBaseMap("HotKeyword");
        HashMap<String, Object> map = new HashMap<>();
        postMap.put("params", map);
        return RxService.createApi(MainApi.class).HotKeyword(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<String>>() {
//            }.getType();
//            List<String> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<List<Product>> SearchProductList(String keyword, String orderby, int start, int pageNum) {
        HashMap<String, Object> postMap = Utils.getBaseMap("SearchProductList");
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderby", orderby);
        map.put("keyword", keyword);
        map.put("page", start);
        map.put("pageNum", pageNum);
        postMap.put("params", map);
        return RxService.createApi(MainApi.class).SearchProductList(postMap).map(bean -> bean.getResult().getResult().getProductlist()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<Product>>() {
//            }.getType();
//            List<Product> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }
}
