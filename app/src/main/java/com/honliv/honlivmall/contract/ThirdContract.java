package com.honliv.honlivmall.contract;


import com.honliv.honlivmall.activity.CoreBaseView;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public interface ThirdContract {

    interface ThirdView extends CoreBaseView {
    }

    interface ThirdMainView extends CoreBaseView {
        void updateView(List<Category> result);
    }

    interface ThirdModel extends CoreBaseModel {
    }

    public interface ThirdMainModel extends CoreBaseModel {
        Observable<List<Category>> getServiceCategoryList();
    }

    abstract class ThirdPresenter extends CoreBasePresenter<ThirdModel, ThirdView> {
    }

    abstract class ThirdMainPresenter extends CoreBasePresenter<ThirdMainModel, ThirdMainView> {
        public abstract void getServiceCategoryList() ;
    }
}
