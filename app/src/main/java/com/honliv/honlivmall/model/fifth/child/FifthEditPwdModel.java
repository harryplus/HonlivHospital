package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.AddressPostUtils;
import com.honliv.honlivmall.engine.UserPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthEditPwdModel implements FifthContract.FifthEditPwdModel {
    @Override
    public Observable<Boolean> updateServicePassword(UserInfo userInfo) {
        Observable<Boolean> result = UserPostUtils.UpdateUser(userInfo);
        return result;
    }
}
