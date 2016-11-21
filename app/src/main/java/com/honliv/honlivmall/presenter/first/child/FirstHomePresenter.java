package com.honliv.honlivmall.presenter.first.child;

import android.util.Log;

import com.honliv.honlivmall.bean.BaseInfo;
import com.honliv.honlivmall.bean.BaseResult;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.contract.FirstContract;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstHomePresenter extends FirstContract.FirstHomePresenter {
    private static final String TAG = "FirstHomePresenter";

    @Override
    public void onStart() {

    }

    @Override
    public void getServiceHomeInfo(String s) {
        mRxManager.add(mModel
                .getServiceHomeInfo(s)
                .subscribe(result -> {
                    if (result != null)
                        mView.updataHomeInfo(result);
                }));
    }

    @Override
    public void getServiceHomeMarketing() {
        mRxManager.add(mModel
                .getServiceHomeMarketing()
                .subscribe(result -> {
                    if (result != null)
                        mView.updataHomeMarketing(result);
                }));
    }
}
