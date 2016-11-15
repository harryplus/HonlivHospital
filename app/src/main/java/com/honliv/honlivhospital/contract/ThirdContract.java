package com.honliv.honlivhospital.contract;


import com.honliv.honlivhospital.activity.CoreBaseView;
import com.honliv.honlivhospital.model.CoreBaseModel;
import com.honliv.honlivhospital.presenter.CoreBasePresenter;

/**
 * Created by Rodin on 2016/11/15.
 */
public interface ThirdContract {

    interface ThirdView extends CoreBaseView {
    }

    interface ThirdMainView extends CoreBaseView {
    }

    interface ThirdModel extends CoreBaseModel {
    }

    public interface ThirdMainModel extends CoreBaseModel {
    }

    abstract class ThirdPresenter extends CoreBasePresenter<ThirdModel, ThirdView> {
    }

    abstract class ThirdMainPresenter extends CoreBasePresenter<ThirdMainModel, ThirdMainView> {
    }
}
