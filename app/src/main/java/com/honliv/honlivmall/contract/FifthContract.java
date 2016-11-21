package com.honliv.honlivmall.contract;

import com.honliv.honlivmall.activity.CoreBaseView;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import rx.Observable;
import rx.Subscription;

/**
 * Created by Rodin on 2016/11/16.
 */
public interface FifthContract {
    public interface FifthModel extends CoreBaseModel {
    }

    interface FifthView extends CoreBaseView {
    }

    interface FifthHomeView extends CoreBaseView {
        void updateView(UserInfo result);
    }

    interface FifthHomeModel extends CoreBaseModel {
        Observable<UserInfo> getServiceUserInfo(int userId);
    }

    abstract class FifthPresenter extends CoreBasePresenter<FifthModel, FifthView> {
    }

    abstract class FifthHomePresenter extends CoreBasePresenter<FifthHomeModel, FifthHomeView> {
        public abstract void getServiceUserInfo(int userId);
    }
}
