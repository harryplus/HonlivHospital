package com.honliv.honlivmall.contract;


import com.honliv.honlivmall.base.CoreBaseView;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public interface GlobalContract {
    public interface GlobalOfficeSelectModel extends CoreBaseModel {
        Observable<UserInfo> getServiceLoginInfo(UserInfo userInfo);
    }

    interface GlobalLoginView extends CoreBaseView {
        void updateView(UserInfo result);
    }

    interface GlobalRegisterStatementView extends CoreBaseView {
        void updateView(String result);
    }

    interface GlobalRegisterView extends CoreBaseView {
        void updateView(UserInfo result);
    }

    interface GlobalFindView extends CoreBaseView {
    }

    public interface GlobalRegisterStatementModel extends CoreBaseModel {
        Observable<String> getServiceStateMent();
    }

    public interface GlobalRegisterModel extends CoreBaseModel {
        Observable<UserInfo> getServiceRegist(UserInfo userInfo);
    }

    public interface GlobalFindModel extends CoreBaseModel {
    }

    abstract class GlobalOfficeSelectPresenter extends CoreBasePresenter<GlobalOfficeSelectModel, GlobalLoginView> {
        public abstract void getServiceLoginInfo(UserInfo userInfo);
    }

    abstract class GlobalRegisterStatementPresenter extends CoreBasePresenter<GlobalRegisterStatementModel, GlobalRegisterStatementView> {

        public abstract void getServiceStateMent();
    }

    abstract class GlobalRegisterPresenter extends CoreBasePresenter<GlobalRegisterModel, GlobalRegisterView> {
        public abstract void getServiceRegist(UserInfo userInfo);
    }

    abstract class GlobalFindPresenter extends CoreBasePresenter<GlobalFindModel, GlobalFindView> {
    }
    abstract class GlobalProductDetailPresenter extends CoreBasePresenter<GlobalProductDetailModel, GlobalProductDetailView> {
        public abstract void getServiceProductDetail(int pId);

        public abstract void addProductFav(int id);
    }
    interface GlobalProductDetailModel extends CoreBaseModel {
        Observable<Product> getServiceProductDetail(int pId);

        Observable<String> addProductFav(int pid);
    }

    interface GlobalProductDetailView extends CoreBaseView {
        void updateView(Product result);

        void updateAddProductFavView(String result);

        void updateStartView(Product arg);
    }
}
