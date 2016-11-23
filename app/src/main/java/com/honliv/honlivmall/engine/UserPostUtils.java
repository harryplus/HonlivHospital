package com.honliv.honlivmall.engine;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

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
public class UserPostUtils {

    private static final String TAG = "UserPostUtils";

    public static Observable<UserInfo> GetUserInfo(HashMap<String, Object> map) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetUserInfo");
        postMap.put("params", map);
        return RxService.createApi(MainApi.class).GetUserInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type2 = new TypeToken<UserInfo>() {
//            }.getType();
//            UserInfo result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type2);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<UserInfo> getServiceLoginInfo(UserInfo userInfo) {
        HashMap<String, Object> postMap = Utils.getBaseMap("Login");
        HashMap<String, String> map = new HashMap<>();
        map.put("UserName", userInfo.getUserName());
        map.put("Password", userInfo.getPassword());
        postMap.put("params", map);
        return RxService.createApi(MainApi.class).Login(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type2 = new TypeToken<UserInfo>() {
//            }.getType();
//            UserInfo result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type2);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Boolean> updateUserInfo(UserInfo userInfo) {
        HashMap<String, Object> postMap = Utils.getBaseMap("UpdateUser");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userInfo.getUserId());
        parames.put("nickName", userInfo.getNickName());
        parames.put("email", userInfo.getEmail());
        parames.put("phone", userInfo.getPhone());
        parames.put("tel", userInfo.getTel());
        parames.put("birthday", userInfo.getBirthday());
        parames.put("regionId", userInfo.getRegionId());
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
            Boolean result = false;
            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
                result = true;
            }
            return result;
        }).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Boolean result = false;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = true;
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Boolean> LogOut() {
        HashMap<String, Object> postMap = Utils.getBaseMap("LogOut");
        HashMap<String, String> map = new HashMap<>();
        postMap.put("params", map);
        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
            Boolean result = false;
            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
                result = true;
            }
            return result;
        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Boolean> UpdateUser(UserInfo userInfo) {
        HashMap<String, Object> postMap = Utils.getBaseMap("UpdateUser");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userInfo.getUserId());
        parames.put("password", userInfo.getPassword());
        parames.put("newPassword", userInfo.getNewpassword());
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
            Boolean result = false;
            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
                result = true;
            }
            return result;
        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<List<CouponInfo>> MyCunpon(int userId, int pageIndex,
                                                        int pageNum, int type) {
        HashMap<String, Object> postMap = Utils.getBaseMap("MyCunpon");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userId);
        parames.put("pageIndex", pageIndex);
        parames.put("pageNum", pageNum);
        parames.put("type", type);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).MyCunpon(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type2 = new TypeToken<List<CouponInfo>>() {
//            }.getType();
//            List<CouponInfo> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type2);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<List<Product>> MyFavorList(int UserID, int pageIndex, int pageSize) {
        HashMap<String, Object> postMap = Utils.getBaseMap("MyFavorList");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("UserID", UserID);
        parames.put("pageIndex", pageIndex);
        parames.put("pageSize", pageSize);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).MyFavorList(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Boolean> CancelFav(int userId, int productId) {
        HashMap<String, Object> postMap = Utils.getBaseMap("CancelFav");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userId);
        parames.put("favId", productId);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
                return true;
            }
            return false;
        }).compose(RxUtil.rxSchedulerHelper());

    }

    public static Observable<Boolean> LiveMessage(int userId, String telephone, String email, String content) {
        HashMap<String, Object> postMap = Utils.getBaseMap("CancelFav");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userId);
        parames.put("telephone", telephone);
        parames.put("email", email);
        parames.put("content", content);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
                return true;
            }
            return false;
        }).compose(RxUtil.rxSchedulerHelper());
    }
}
