package com.honliv.honlivmall.presenter;

import com.honliv.honlivmall.util.RxManager;

/**
 * Created by Rodin on 2016/11/15.
 */
public abstract class CoreBasePresenter<M, T> {
    public M mModel;
    public T mView;
    public RxManager mRxManager = new RxManager();

    public void attachVM(T v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void detachVM() {
        mRxManager.clear();
        mView = null;
        mModel = null;
    }

    public abstract void onStart();
}