package com.honliv.honlivmall.contract;

import com.honliv.honlivmall.activity.CoreBaseView;
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

    interface FirstView extends CoreBaseView {
    }

    interface FirstProductDetailView extends CoreBaseView {
        void updateView(Product result);

        void updateAddProductFavView(String result);

        void updateStartView(Product arg);
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

    interface FirstSelectDoctorView extends CoreBaseView {
    }

    interface FirstBargainModel extends CoreBaseModel {
        Observable<List<Product>> getServiceProductLS();
    }

    interface FirstProductDetailModel extends CoreBaseModel {
        Observable<Product> getServiceProductDetail(int pId);

        Observable<String> addProductFav(int pid);
    }

    interface FirstMarketingModel extends CoreBaseModel {
        Observable<List<Product>> getServiceProductLS();
    }

    interface FirstSelectDoctorModel extends CoreBaseModel {
    }

    abstract class FirstPresenter extends CoreBasePresenter<FirstModel, FirstView> {
    }

    abstract class FirstBargainPresenter extends CoreBasePresenter<FirstBargainModel, FirstBargainView> {
        public abstract void getServiceProductLS();
    }

    abstract class FirstHomePresenter extends CoreBasePresenter<FirstHomeModel, FirstHomeView> {
        public abstract void  getServiceHomeInfo(String s);

        public abstract void getServiceHomeMarketing();
    }

    interface FirstHomeModel extends CoreBaseModel {
        Observable<HomeInfo> getServiceHomeInfo(String s);

        Observable<List<Product>> getServiceHomeMarketing();
    }

    abstract class FirstProductDetailPresenter extends CoreBasePresenter<FirstProductDetailModel, FirstProductDetailView> {
        public abstract void getServiceProductDetail(int pId);

        public abstract void addProductFav(int id);
    }

    abstract class FirstMarketingPresenter extends CoreBasePresenter<FirstMarketingModel, FirstMarketingView> {
        public abstract void getServiceProductLS();
    }

    abstract class FirstSelectDoctorPresenter extends CoreBasePresenter<FirstSelectDoctorModel, FirstSelectDoctorView> {
    }
}
