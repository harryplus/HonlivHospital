package com.honliv.honlivmall.presenter.third.child;

import android.util.Log;

import com.google.gson.Gson;
import com.honliv.honlivmall.contract.ThirdContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class ThirdMainPresenter extends ThirdContract.ThirdMainPresenter {
    private static final String TAG = "ThirdMainPresenter";

    @Override
    public void onStart() {

    }

    @Override
    public void getServiceCategoryList() {
//        new Thread() {
//            @Override
//            public void run() {
//                mModel.getServiceCategoryList().map(n -> {
//                    Log.i(TAG, "---"+new Gson().toJson(n).toString());
//                    return null;
//                }).subscribe(k -> {
//
//                });
//            }
//        }.run();
        mRxManager.add(mModel.getServiceCategoryList().subscribe(result -> {
            mView.updateView(result);
        }));
    }
}
