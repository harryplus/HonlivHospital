package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.HomePostUtils;
import com.honliv.honlivmall.engine.UserPostUtils;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthHomeModel implements FifthContract.FifthHomeModel {
    @Override
    public Observable<UserInfo> getServiceUserInfo(int userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("UserId", userId);
        Observable<UserInfo> result = UserPostUtils.GetUserInfo(map);
        return result;
    }
}
