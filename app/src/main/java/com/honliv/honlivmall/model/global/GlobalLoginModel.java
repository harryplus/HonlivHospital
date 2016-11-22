package com.honliv.honlivmall.model.global;

import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.util.RxUtil;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class GlobalLoginModel implements GlobalContract.GlobalOfficeSelectModel  {
    @Override
    public Observable<UserInfo> getServiceLoginInfo(UserInfo userInfo) {
//        Observable<UserInfo> result = Observable.just(userInfo).map(s -> {
//            UserInfo serviceHomeMarketing = UserInfoEngine.getServiceLoginInfo(s);
//                    return serviceHomeMarketing;
//                }
//        );
//        return result.compose(RxUtil.rxSchedulerHelper());
        return null;
    }
}
