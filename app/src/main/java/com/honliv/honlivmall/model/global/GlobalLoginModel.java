package com.honliv.honlivmall.model.global;

import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.engine.HomePostUtils;
import com.honliv.honlivmall.engine.UserPostUtils;
import com.honliv.honlivmall.util.RxUtil;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class GlobalLoginModel implements GlobalContract.GlobalOfficeSelectModel  {
    @Override
    public Observable<UserInfo> getServiceLoginInfo(UserInfo userInfo) {
        HashMap<String, Object> map = new HashMap<>();
        Observable<UserInfo> result = UserPostUtils.getServiceLoginInfo(userInfo);
        return result;
    }
}
