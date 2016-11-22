package com.honliv.honlivmall.model.global;

import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.util.RxUtil;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class GlobalRegisterModel implements GlobalContract.GlobalRegisterModel {
    @Override
    public Observable<UserInfo> getServiceRegist(UserInfo userInfo) {
//        Observable<UserInfo> result = Observable.just(userInfo).map(s -> {
//                    UserInfo serviceHomeMarketing = UserInfoEngine.getServiceRegistInfo(s);
//                    return serviceHomeMarketing;
//                }
//        );
//        return result.compose(RxUtil.rxSchedulerHelper());
        return null;
    }
}
