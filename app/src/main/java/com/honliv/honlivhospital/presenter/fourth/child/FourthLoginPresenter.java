package com.honliv.honlivhospital.presenter.fourth.child;

import com.honliv.honlivhospital.contract.FourthContract;
import com.honliv.honlivhospital.domain.BaseResult;
import com.honliv.honlivhospital.domain.UserBean;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Rodin on 2016/11/15.
 */
public class FourthLoginPresenter extends FourthContract.FourthLoginPresenter {
    private static final String TAG = "FourthLoginPresenter";

    @Override
    public void onStart() {

    }

    @Override
    public boolean login(UserBean bean) {
        mRxManager.add(mModel
                .login(bean)
                .subscribe(
                        new Subscriber<BaseResult<List<UserBean>>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable throwable) {

                            }

                            @Override
                            public void onNext(BaseResult<List<UserBean>> listBaseResult) {

                            }
                        }));
        return false;
    }
}
