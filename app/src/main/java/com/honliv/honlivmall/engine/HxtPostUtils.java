package com.honliv.honlivmall.engine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.bean.HxtBean;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/23.
 */
public class HxtPostUtils {
    public static Observable<List<HxtBean>> HxtUserList() {
        HashMap<String, Object> postMap = Utils.getBaseMap("HxtUserList");
        HashMap<String, Object> params = new HashMap<>();
        params.put("UserID", GloableParams.USERID);//
        postMap.put("params", params);
        return RxService.createApi(MainApi.class).HxtUserList(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<HxtBean>>() {
//            }.getType();
//            List<HxtBean> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }
}
