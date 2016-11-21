package com.honliv.honlivmall.contract;


import com.honliv.honlivmall.activity.CoreBaseView;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public interface SecondContract {
    interface SecondView extends CoreBaseView {
    }

    interface SecondMainView extends CoreBaseView {
        void updataSearchKeyList(List<String> result);
    }

    interface SecondModel extends CoreBaseModel {
    }

    interface SecondMainModel extends CoreBaseModel {
        Observable<List<String>> getSeaviceSearchKeyList();
    }

    abstract class SecondPresenter extends CoreBasePresenter<SecondModel, SecondView> {
    }

    abstract class SecondMainPresenter extends CoreBasePresenter<SecondMainModel, SecondMainView> {
        public abstract void getSeaviceSearchKeyList();
    }
}
