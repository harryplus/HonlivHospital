package com.honliv.honlivmall.engine;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.BeanFactory;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Rodin on 2016/11/21.
 */
public class CategoryPostUtils {
    private static final String TAG = "CategoryPostUtils";

    public static Observable<List<Category>> CategoryList(HashMap<String, Object> map) {
//        HashMap<String, Object> postMap = Utils.getBaseMap("CategoryList");
//        postMap.put("params", map);
//        return RxService.createApi(MainApi.class).CategoryList(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());

        return Observable.just(map).map(m -> {
            CategoryEngine engine = BeanFactory.getImpl(CategoryEngine.class);
            return engine.getServiceCategoryList();
        }).compose(RxUtil.rxSchedulerHelper());


//        return RxService.createApi(MainApi.class).postBody(postMap).map(bean -> {
//            Type type = new TypeToken<List<Category>>() {
//            }.getType();
//            List<Category> result = null;
//            result = new Gson().fromJson(bean.getResult().getResult(), type);
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                Log.i(TAG, bean.getResult().getResult());
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//        }).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<Category>>() {
//            }.getType();
//            List<Category> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                Log.i(TAG,bean.getResult().getResult());
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Float> GetFreight(int userId, int shipId, int shipTypeId,
                                               List<JSONObject> productList, float allCouponMoney) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetFreight");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userId);
        parames.put("shipId", shipId);
        parames.put("shipTypeId", shipTypeId);
//			parames.put("allCouponMoney", allCouponMoney);
        parames.put("productList", productList);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).GetFreight(postMap).map(bean -> bean.getResult().getResult().getFreight()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Float result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = Float.valueOf(bean.getResult().getResult());
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Float> GetTotalPrice(int userid, int proSaleId, int groupBuyId, List<JSONObject> productList) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetTotalPrice");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userid);
        parames.put("groupBuyId", groupBuyId);
        parames.put("proSaleId", proSaleId);
        parames.put("productList", productList);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).GetTotalPrice(postMap).map(bean -> bean.getResult().getResult().getAllprice()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Float result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = Float.valueOf(bean.getResult().getResult());
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<GifInfo> GetGiftInfo(int userid, List<JSONObject> productList, float couponPrice) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetGiftInfo");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userid);
        parames.put("coupPrice", couponPrice);
        parames.put("productList", productList);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).GetGiftInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<GifInfo>() {
//            }.getType();
//            GifInfo result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Product> ProductDetail(int pId, int userid) {
        HashMap<String, Object> postMap = Utils.getBaseMap("ProductDetail");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("pId", pId);
        parames.put("userId", userid);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ProductDetail(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<Product>() {
//            }.getType();
//            Product result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());

    }

    public static Observable<String> ProductAddFav(int userid, int pid) {
        HashMap<String, Object> postMap = Utils.getBaseMap("ProductAddFav");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userid);
        parames.put("productId", pid);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ProductAddFav(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            String result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = bean.getResult().getResult();
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }
}
