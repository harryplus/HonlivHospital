package com.honliv.honlivmall.engine;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.BaseBean;
import com.honliv.honlivmall.bean.BaseInfo;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.bean.GalleryProduct;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.ProductListFilter;
import com.honliv.honlivmall.util.MyJSUtil;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

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
        return RxService.createApi(MainApi.class).CountDownList(postMap).map(bean ->bean.getResult().getResult().getProductlist()).compose(RxUtil.rxSchedulerHelper());
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
        return RxService.createApi(MainApi.class).GetPhone(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
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
}
