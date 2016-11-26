package com.honliv.honlivmall.contract;


import com.honliv.honlivmall.base.CoreBaseView;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.bean.ProductListFilter;
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
    interface ThirdCategory2View extends CoreBaseView {
    }

    interface ThirdMainView extends CoreBaseView {
        void updateView(List<Category> result);
    }

    interface ThirdCategoryView extends CoreBaseView {
    }
    interface ThirdProductListView extends CoreBaseView {
        void updateView(ProductListFilter result);
    }

    interface ThirdModel extends CoreBaseModel {
    }
    interface ThirdProductListModel extends CoreBaseModel {
        Observable<ProductListFilter> ProductList(int categoryId, String orderBy, int page, int pageNum);
    }
    interface ThirdCategory2Model extends CoreBaseModel {
    }

    public interface ThirdMainModel extends CoreBaseModel {
        Observable<List<Category>> getServiceCategoryList();
    }

    public interface ThirdCategoryModel extends CoreBaseModel {
    }

    abstract class ThirdPresenter extends CoreBasePresenter<ThirdModel, ThirdView> {
    }

    abstract class ThirdMainPresenter extends CoreBasePresenter<ThirdMainModel, ThirdMainView> {
        public abstract void getServiceCategoryList();
    }

    abstract class ThirdCategoryPresenter extends CoreBasePresenter<ThirdCategoryModel, ThirdCategoryView> {
    }
    abstract class ThirdCategory2Presenter extends CoreBasePresenter<ThirdCategory2Model, ThirdCategory2View> {
    }

    abstract class ThirdProductListPresenter extends CoreBasePresenter<ThirdProductListModel, ThirdProductListView>{
        public abstract void getServiceProductList(int cId, String hot, int start, int pageNum);
    }
}
