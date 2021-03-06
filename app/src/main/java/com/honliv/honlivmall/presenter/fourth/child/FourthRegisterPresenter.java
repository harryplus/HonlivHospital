package com.honliv.honlivmall.presenter.fourth.child;

import android.util.Log;

import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.bean.BaseResult;
import com.honliv.honlivmall.bean.UserBean;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FourthRegisterPresenter extends FourthContract.FourthRegisterPresenter {
    private static final String TAG = "FourthRegisterPresenter";

    @Override
    public void onStart() {

    }

    @Override
    public boolean register(UserBean bean, String VcodeData) {
        mRxManager.add(mModel
                .register(bean, VcodeData)
                .subscribe(
                        new Subscriber<BaseResult<UserBean>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable throwable) {

                            }

                            @Override
                            public void onNext(BaseResult<UserBean> userBeanBaseResult) {

                            }
                        }));
        return false;
    }

    @Override
    public String sendNote(String phoneNum) {
        mRxManager.add(mModel
                .sendNote(phoneNum)
                .subscribe(
                        new Subscriber<BaseResult<List<UserBean>>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(BaseResult<List<UserBean>> listBaseResult) {
                            }
                        }));
        return null;
    }
}
