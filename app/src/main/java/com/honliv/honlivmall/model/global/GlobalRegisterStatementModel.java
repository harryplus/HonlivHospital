package com.honliv.honlivmall.model.global;

import com.honliv.honlivmall.contract.GlobalContract;
import com.honliv.honlivmall.util.RxUtil;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class GlobalRegisterStatementModel implements GlobalContract.GlobalRegisterStatementModel {
    @Override
    public Observable<String> getServiceStateMent() {
//        Observable<String> result = Observable.just("").map(s -> {
//            String serviceHomeMarketing = UserInfoEngine.getServiceRegistStateMent();
//                    return serviceHomeMarketing;
//                }
//        );
//        return result.compose(RxUtil.rxSchedulerHelper());
        return  null;
    }
}
