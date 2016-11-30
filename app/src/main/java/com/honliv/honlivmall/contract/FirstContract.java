package com.honliv.honlivmall.contract;

import com.honliv.honlivmall.base.CoreBaseView;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import java.util.List;

import rx.Observable;


/**
 * Created by Rodin on 2016/11/15.
 */
public interface FirstContract {
    interface FirstModel extends CoreBaseModel {
    }
    interface FirstBrandListModel extends CoreBaseModel {
        Observable< List<Product>> ProductList(int brandId);
    }

    interface FirstView extends CoreBaseView {
    }
    interface FirstBrandListView extends CoreBaseView {
        void updateProductLS(List<Product> result);
    }


    interface FirstBargainView extends CoreBaseView {
        void updateProductLS(List<Product> result);
    }

    interface FirstHomeView extends CoreBaseView {
        void updataHomeInfo(HomeInfo info);

        void updataHomeMarketing(List<Product> result);
    }

    interface FirstMarketingView extends CoreBaseView {
        void updateProductLS(List<Product> result);
    }



    interface FirstBargainModel extends CoreBaseModel {
        Observable<List<Product>> getServiceProductLS();
    }


    interface FirstMarketingModel extends CoreBaseModel {
        Observable<List<Product>> CountDownList();
    }



    abstract class FirstPresenter extends CoreBasePresenter<FirstModel, FirstView> {
    }

    abstract class FirstBargainPresenter extends CoreBasePresenter<FirstBargainModel, FirstBargainView> {
        public abstract void getServiceProductLS();
    }

    abstract class FirstHomePresenter extends CoreBasePresenter<FirstHomeModel, FirstHomeView> {
        public abstract void getServiceHomeInfo(String s);

        public abstract void getServiceHomeMarketing();
    }

    interface FirstHomeModel extends CoreBaseModel {
        Observable<HomeInfo> getServiceHomeInfo(String s);

        Observable<List<Product>> getServiceHomeMarketing();
    }


    abstract class FirstMarketingPresenter extends CoreBasePresenter<FirstMarketingModel, FirstMarketingView> {
        public abstract void getServiceProductLS();
    }


    abstract class FirstBrandListPresenter  extends CoreBasePresenter<FirstBrandListModel, FirstBrandListView> {
        public abstract void getServiceProductLS(int brandId);
    }
}
