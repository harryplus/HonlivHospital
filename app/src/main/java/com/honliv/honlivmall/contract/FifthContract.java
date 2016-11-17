package com.honliv.honlivmall.contract;

import com.honliv.honlivmall.activity.CoreBaseView;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

/**
 * Created by Rodin on 2016/11/16.
 */
public interface FifthContract {
    public interface FifthModel extends CoreBaseModel {
    }

    interface FifthView extends CoreBaseView {
    }

    interface FifthHomeView extends CoreBaseView {
    }

    interface FifthHomeModel extends CoreBaseModel {
    }

    abstract class FifthPresenter extends CoreBasePresenter<FifthModel, FifthView> {
    }

    abstract class FifthHomePresenter extends CoreBasePresenter<FifthHomeModel, FifthHomeView> {
    }
}
