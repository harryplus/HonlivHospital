package com.honliv.honlivmall.contract;

import android.content.Context;

import com.honliv.honlivmall.activity.CoreBaseView;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;

/**
 * Created by Rodin on 2016/11/16.
 */
public interface FifthContract {
    public interface FifthModel extends CoreBaseModel {
    }

    interface FifthView extends CoreBaseView {
    }

    interface FifthHomeView extends CoreBaseView {
        void updateView(UserInfo result);
    }

    interface FifthHomeModel extends CoreBaseModel {
        Observable<UserInfo> getServiceUserInfo(int userId);
    }

    public interface FifthEditPersonInfoView extends CoreBaseView {
        void updateServicePersonal(UserInfo result);

        void updateUserInfoView(Boolean result);
    }

    public interface FifthMyHxtView extends CoreBaseView {
    }

    public interface FifthEditPwdView extends CoreBaseView {
    }

    public interface FifthAddressManageView extends CoreBaseView {
        void updateProductList(List<AddressInfo> result);
    }
    public interface FifthMyOrderView extends CoreBaseView {
    }
    public interface FifthFavoriteView extends CoreBaseView {
    }
    public interface FifthMyCouponView extends CoreBaseView {
    }
    public interface FifthSettingView extends CoreBaseView {
    }
    public interface FifthAddAddressView extends CoreBaseView {
        void updateView(List<AddressInfo> result);

        void updateView2(List<AddressInfo> result);

        void updateServiceProductList(List<AddressInfo> result, boolean b);

        void updateServiceAddressName(String result);
    }

    public interface FifthEditPersonInfoModel extends CoreBaseModel {
        Observable<UserInfo> getServicePersonal(Context context);

        Observable<Boolean> updateUserInfo(UserInfo info);
    }

    public interface FifthMyHxtModel extends CoreBaseModel {
    }

    public interface FifthEditPwdModel extends CoreBaseModel {
    }
    public interface FifthMyOrderModel extends CoreBaseModel {
    }

    public interface FifthAddressManageModel extends CoreBaseModel {
        Observable<List<AddressInfo>> getServiceProductList();
    }
    public interface FifthFavoriteModel extends CoreBaseModel {
    }
    public interface FifthMyCouponModel extends CoreBaseModel {
    }
    public interface FifthSettingModel extends CoreBaseModel {
    }
    public interface FifthAddAddressModel extends CoreBaseModel {
        Observable<List<AddressInfo>> initSpinner1(int i);

        Observable<List<AddressInfo>> initSpinner2(int pcode);

        Observable<List<AddressInfo>> getServiceProductList(AddressInfo addressPreInfo, AddressInfo addressInfo, boolean b);

        Observable<String> getServiceAddressName(int regionId);
    }

    abstract class FifthPresenter extends CoreBasePresenter<FifthModel, FifthView> {
    }

    abstract class FifthHomePresenter extends CoreBasePresenter<FifthHomeModel, FifthHomeView> {
        public abstract void getServiceUserInfo(int userId);
    }

    abstract class FifthEditPersonInfoPresenter extends CoreBasePresenter<FifthEditPersonInfoModel, FifthEditPersonInfoView> {
        public abstract void getServicePersonal(Context context);

        public abstract void updateUserInfo(UserInfo info);
    }

    abstract class FifthMyHxtPresenter extends CoreBasePresenter<FifthMyHxtModel, FifthMyHxtView> {
    }

    abstract class FifthEditPwdPresenter extends CoreBasePresenter<FifthEditPwdModel, FifthEditPwdView> {
    }

    abstract class FifthAddressManagePresenter extends CoreBasePresenter<FifthAddressManageModel, FifthAddressManageView> {
        public abstract void getServiceProductList();
    }

    abstract class FifthMyOrderPresenter extends CoreBasePresenter<FifthMyOrderModel, FifthMyOrderView>{
    }

    abstract class FifthFavoritePresenter  extends CoreBasePresenter<FifthFavoriteModel, FifthFavoriteView>{
    }

    abstract class FifthMyCouponPresenter extends CoreBasePresenter<FifthMyCouponModel, FifthMyCouponView>{
    }

    abstract class FifthSettingPresenter extends CoreBasePresenter<FifthSettingModel, FifthSettingView>{
    }

    abstract class FifthAddAddressPresenter extends CoreBasePresenter<FifthAddAddressModel, FifthAddAddressView>{
        public abstract void initSpinner1(int i);

        public abstract void initSpinner2(int pcode);

        public abstract void getServiceProductList(AddressInfo addressPreInfo, AddressInfo addressInfo, boolean b);

        public abstract void getServiceAddressName(int regionId);
    }
}
