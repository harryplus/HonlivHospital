package com.honliv.honlivmall.presenter.first.child;

import android.util.Log;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.fragment.first.child.FirstProductDetailFragment;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FirstProductDetailPresenter extends FirstContract.FirstProductDetailPresenter {
    private static final String TAG = "FirstProductDetailPresenter";

    @Override
    public void onStart() {
    }

    @Override
    public void getServiceProductDetail(int pId) {
        mRxManager.add(mModel.getServiceProductDetail(pId).subscribe(result -> {
            mView.updateView(result);
        }, e -> mView.showError(e.toString())));
    }

    @Override
    public void addProductFav(int pid) {
        mRxManager.add(mModel.addProductFav(pid).subscribe(result -> {
            mView.updateAddProductFavView(result);
        }, e -> mView.showError(e.toString())));
    }
}
