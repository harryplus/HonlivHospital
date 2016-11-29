package com.honliv.honlivmall.presenter.fourth.child;

import android.util.Log;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.contract.FourthContract;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FourthMainPresenter extends  FourthContract.FourthMainPresenter {
    private static final String TAG = "FourthMainPresenter";

    @Override
    public void onStart() {
        Log.i(TAG,"onStart");
//        mRxManager.add(mModel
//                .getNativeAllShopCart(GloableParams.USERID)
//                .subscribe(result -> {
//                    if (result != null)
//                        mView.updataAllShopCart(result);
//                },e->mView.showError(e.toString())));
    }

    @Override
    public void getNativeAllShopCart(int userId) {
        mRxManager.add(mModel
                .getNativeAllShopCart(userId)
                .subscribe(result -> {
                    if (result != null)
                        mView.updataAllShopCart(result);
                },e->mView.showError(e.toString())));
    }
}
