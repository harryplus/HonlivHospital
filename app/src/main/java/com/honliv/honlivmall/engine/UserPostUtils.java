package com.honliv.honlivmall.engine;

import android.util.Log;

import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

import java.io.IOException;
import java.util.HashMap;
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
    }

    public static Observable<UserInfo> getServiceLoginInfo(UserInfo userInfo) {
        HashMap<String, Object> postMap = Utils.getBaseMap("Login");
        HashMap<String, String> map = new HashMap<>();
        map.put("UserName", userInfo.getUserName());
        map.put("Password", userInfo.getPassword());
        postMap.put("params", map);
        return RxService.createApi(MainApi.class).Login(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Boolean> updateUserInfo(UserInfo userInfo) {
        HashMap<String, Object> postMap = Utils.getBaseMap("Login");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userInfo.getUserId());
        parames.put("nickName", userInfo.getNickName());
        parames.put("email", userInfo.getEmail());
        parames.put("phone", userInfo.getPhone());
        parames.put("tel", userInfo.getTel());
        parames.put("birthday", userInfo.getBirthday());
        parames.put("regionId", userInfo.getRegionId());
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).updateUserInfo(postMap).map(bean -> {
            try {
                Log.i(TAG,bean.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }).compose(RxUtil.rxSchedulerHelper());
    }
}
