package com.honliv.honlivmall.presenter.first.child;

import android.util.Log;

import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstMarketingPresenter extends FirstContract.FirstMarketingPresenter {
    private static final String TAG = "FirstMarketingPresenter";

    @Override
    public void onStart() {

    }

    @Override
    public void getServiceProductLS() {
        mRxManager.add(mModel.getServiceProductLS().subscribe(result -> {
            mView.updateProductLS(result);
        }));
    }
}
