package com.honliv.honlivmall.model.fifth.child;

import com.honliv.honlivmall.bean.HxtBean;
import com.honliv.honlivmall.bean.VersionInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.engine.HelpPostUtils;
import com.honliv.honlivmall.engine.HxtPostUtils;
import com.honliv.honlivmall.engine.UserPostUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/22.
 */
public class FifthSettingModel implements FifthContract.FifthSettingModel {
    @Override
    public Observable<VersionInfo> checkVersion() {
        Observable<VersionInfo> result = HelpPostUtils.checkVersion();
        return result;
    }

    @Override
    public Observable<Boolean> LogOut() {
        Observable<Boolean> result = UserPostUtils.LogOut();
        return result;
    }
}
