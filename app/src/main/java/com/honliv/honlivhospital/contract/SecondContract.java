package com.honliv.honlivhospital.contract;


import com.honliv.honlivhospital.activity.CoreBaseView;
import com.honliv.honlivhospital.model.CoreBaseModel;
import com.honliv.honlivhospital.presenter.CoreBasePresenter;

/**
 * Created by Rodin on 2016/11/15.
 */
public interface SecondContract {
    interface SecondView extends CoreBaseView {
    }
    interface SecondMainView extends CoreBaseView {
    }

    interface SecondModel extends CoreBaseModel {
    }

    public interface SecondMainModel  extends CoreBaseModel {
    }

    abstract class SecondPresenter extends CoreBasePresenter<SecondModel, SecondView> {
    }

    abstract class SecondMainPresenter extends CoreBasePresenter<SecondMainModel, SecondMainView> {
    }
}
