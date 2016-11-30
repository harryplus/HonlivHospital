package com.honliv.honlivmall.engine;

import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/17.
 */
public class HomePostUtils {
    private static final String TAG = "HomePostUtils";

    /**
     * 首頁数据
     *
     * @param map
     * @return
     */
    public static Observable<HomeInfo> HomeIndex(HashMap<String, Object> map) {
        HashMap<String, Object> postMap = Utils.getBaseMap("HomeIndex");
        postMap.put("params", map);
        return RxService.createApi(MainApi.class).HomeIndex(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<HomeInfo>() {
//            }.getType();
//            HomeInfo result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                Log.i(TAG,bean.getResult().getResult());
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }


    public static Observable<List<Product>> CountDownList() {
        HashMap<String, Object> postMap = Utils.getBaseMap("CountDownList");
        postMap.put("params", new HashMap<>());
        return RxService.createApi(MainApi.class).ProductListFilter(postMap).map(bean ->{
            try {
                return bean.getResult().getResult().getProductlist();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ArrayList<Product>();
        }).compose(RxUtil.rxSchedulerHelper());
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


    public static Observable<DefaultParas> GetPhone() {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetPhone");
        postMap.put("params", new HashMap<>());
        return RxService.createApi(MainApi.class).DefaultParas(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<DefaultParas>() {
//            }.getType();
//            DefaultParas result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Integer> GetOrder(String twoCodeStr) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetOrder");
        HashMap<Object, Object> parames = new HashMap<>();
        parames.put("orderCode", twoCodeStr);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).Integer(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<List<Product>> ProductList(int brandId) {
        HashMap<String, Object> postMap = Utils.getBaseMap("ProductList");
        HashMap<Object, Object> parames = new HashMap<>();
        parames.put("brandid", brandId);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ProductListFilter(postMap).map(bean -> bean.getResult().getResult().getProductlist()).compose(RxUtil.rxSchedulerHelper());
    }
}
