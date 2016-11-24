package com.honliv.honlivmall.contract;


import com.honliv.honlivmall.activity.CoreBaseView;
import com.honliv.honlivmall.bean.BaseResult;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserBean;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public interface FourthContract {
    interface FourthModel extends CoreBaseModel {
    }

    interface FourthView extends CoreBaseView {
    }

    interface FourthMainView extends CoreBaseView {
        void updataAllShopCart(ArrayList<Product> result);

        void updateStart();
    }

    interface FourthLoginView extends CoreBaseView {
    }

    interface FourthPersonView extends CoreBaseView {
    }

    interface FourthDetailView extends CoreBaseView {
    }

    interface FourthRegisterView extends CoreBaseView {
    }

    interface FourthChangePwdView extends CoreBaseView {
    }

    interface FourthCardView extends CoreBaseView {
    }

    interface FourthBookView extends CoreBaseView {
    }

    interface FourthAddCardView extends CoreBaseView {
    }

    interface FourthMainModel extends CoreBaseModel {
        Observable<ArrayList<Product>> getNativeAllShopCart(int userId);
    }

    interface FourthLoginModel extends CoreBaseModel {
        Observable<BaseResult<List<UserBean>>> login(UserBean bean);
    }

    interface FourthPersonModel extends CoreBaseModel {
    }

    interface FourthDetailModel extends CoreBaseModel {
    }

    interface FourthRegisterModel extends CoreBaseModel {
        Observable<BaseResult<UserBean>> register(UserBean bean, String VcodeData);

        Observable<BaseResult<List<UserBean>>> sendNote(String phoneNum);
    }

    interface FourthChangePwdModel extends CoreBaseModel {
    }

    interface FourthCardModel extends CoreBaseModel {
    }

    interface FourthBookModel extends CoreBaseModel {
    }

    interface FourthAddCardModel extends CoreBaseModel {
    }

    abstract class FourthPresenter extends CoreBasePresenter<FourthModel, FourthView> {
    }

    abstract class FourthMainPresenter extends CoreBasePresenter<FourthMainModel, FourthMainView> {
        public abstract void getNativeAllShopCart(int userId);
    }

    abstract class FourthLoginPresenter extends CoreBasePresenter<FourthLoginModel, FourthLoginView> {
        public abstract boolean login(UserBean bean);
    }

    abstract class FourthPersonPresenter extends CoreBasePresenter<FourthPersonModel, FourthPersonView> {
    }

    abstract class FourthDetailPresenter extends CoreBasePresenter<FourthDetailModel, FourthDetailView> {
    }

    abstract class FourthRegisterPresenter extends CoreBasePresenter<FourthRegisterModel, FourthRegisterView> {
        public abstract boolean register(UserBean bean, String VcodeData);

        public abstract String sendNote(String phoneNum);
    }

    abstract class FourthChangePwdPresenter extends CoreBasePresenter<FourthChangePwdModel, FourthChangePwdView> {
    }

    abstract class FourthCardPresenter extends CoreBasePresenter<FourthCardModel, FourthCardView> {
    }

    abstract class FourthBookPresenter extends CoreBasePresenter<FourthBookModel, FourthBookView> {
    }

    abstract class FourthAddCardPresenter extends CoreBasePresenter<FourthAddCardModel, FourthAddCardView> {
    }
}
