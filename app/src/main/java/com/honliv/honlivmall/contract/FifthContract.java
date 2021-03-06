package com.honliv.honlivmall.contract;

import android.content.Context;

import com.honliv.honlivmall.base.CoreBaseView;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.Help;
import com.honliv.honlivmall.bean.HxtBean;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.bean.VersionInfo;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/16.
 */
public interface FifthContract {
    public interface FifthModel extends CoreBaseModel {
    }



    public interface FifthMyOrderDetailModel extends CoreBaseModel {
        Observable<OrderInfo> OrderDetail(int userid, int orderId);

        Observable<Boolean> CancelOrder(int userid, int orderId);
    }

    public interface FifthAboutModel extends CoreBaseModel {
    }

    public interface FifthNearByModel extends CoreBaseModel {
    }

    interface FifthView extends CoreBaseView {
    }



    interface FifthMyOrderDetailView extends CoreBaseView {
        void updateServiceOrderDetail(OrderInfo result);

        void updateServiceCancelOrder(Boolean result);
    }

    interface FifthNearByView extends CoreBaseView {
    }

    interface FifthAboutView extends CoreBaseView {
    }

    interface FifthHomeView extends CoreBaseView {
        void updateView(UserInfo result);

        void updateLogOutView(Boolean result);
    }

    interface FifthHomeModel extends CoreBaseModel {
        Observable<UserInfo> getServiceUserInfo(int userId);

        Observable<Boolean> getServiceLogOut(String s);
    }

    public interface FifthEditPersonInfoView extends CoreBaseView {
        void updateServicePersonal(UserInfo result);

        void updateUserInfoView(Boolean result);
    }

    public interface FifthMyHxtView extends CoreBaseView {
        void updateView(List<HxtBean> result);
    }

    public interface FifthEditPwdView extends CoreBaseView {
        void updateView(Boolean result);
    }

    public interface FifthAddressManageView extends CoreBaseView {
        void updateServiceAddressList(List<AddressInfo> result);
    }

    public interface FifthMyOrderView extends CoreBaseView {
        void updateView(List<OrderInfo> result);

        void updateServiceCancelOrder(Boolean result, int position);
    }

    public interface FifthFavoriteView extends CoreBaseView {
        void updateServiceFavInfo(List<Product> result);

        void updateServiceCancelFav(Boolean result, int position);
    }

    public interface FifthMyCouponView extends CoreBaseView {
        void updateView2(List<CouponInfo> result);

        void updateView1(List<CouponInfo> result);
    }

    public interface FifthSettingView extends CoreBaseView {
        void updateVersion(VersionInfo result);

        void updateLogOut(Boolean result);
    }

    public interface FifthMyHxtPwdView extends CoreBaseView {
    }

    public interface FifthFindPwdView extends CoreBaseView {
    }

    public interface FifthFeedBackView extends CoreBaseView {
        void updateView(Boolean result);
    }

    public interface FifthMyHxtDetailView extends CoreBaseView {
    }

    public interface FifthHelpList2View extends CoreBaseView {
    }

    public interface FifthHelpDetailView extends CoreBaseView {
        void updateView(Help result);
    }

    public interface FifthHelpListView extends CoreBaseView {
        void updateView(List<Help> result);
    }



    public interface FifthEditPersonInfoModel extends CoreBaseModel {
        Observable<UserInfo> getServicePersonal(Context context);

        Observable<Boolean> updateUserInfo(UserInfo info);
    }

    public interface FifthMyHxtModel extends CoreBaseModel {
        Observable<List<HxtBean>> getData();
    }

    public interface FifthEditPwdModel extends CoreBaseModel {
        Observable<Boolean> updateServicePassword(UserInfo userInfo);
    }

    public interface FifthMyOrderModel extends CoreBaseModel {
        Observable<List<OrderInfo>> OrderList(int userId, int page, int pageNum);

        Observable<Boolean> CancelOrder(int userid, int orderid);
    }

    public interface FifthMyHxtPwdModel extends CoreBaseModel {
    }

    public interface FifthAddressManageModel extends CoreBaseModel {
        Observable<List<AddressInfo>> getServiceAddressList();
    }

    public interface FifthFavoriteModel extends CoreBaseModel {
        Observable<List<Product>> updateServiceFavInfo(int userid, int start, int i);

        Observable<Boolean> CancelFav(int userid, int favId);
    }

    public interface FifthMyCouponModel extends CoreBaseModel {
        Observable<List<CouponInfo>> getServiceCouponInfo(int userId, int start, int i, int i1);
    }

    public interface FifthMyHxtDetailModel extends CoreBaseModel {
    }

    public interface FifthHelpDetailModel extends CoreBaseModel {
        Observable<Help> HelpDetail(int helpId);
    }

    public interface FifthFindPwdModel extends CoreBaseModel {
    }

    public interface FifthHelpList2Model extends CoreBaseModel {
    }

    public interface FifthHelpListModel extends CoreBaseModel {
        Observable<List<Help>> HelpList(int i);
    }

    public interface FifthFeedBackModel extends CoreBaseModel {
        Observable<Boolean> LiveMessage(int userId, String telephone, String email, String content);
    }

    public interface FifthSettingModel extends CoreBaseModel {
        Observable<VersionInfo> checkVersion();

        Observable<Boolean> LogOut();
    }



    abstract class FifthPresenter extends CoreBasePresenter<FifthModel, FifthView> {
    }

    abstract class FifthHomePresenter extends CoreBasePresenter<FifthHomeModel, FifthHomeView> {
        public abstract void getServiceUserInfo(int userId);

        public abstract void getServiceLogOut(String s);
    }

    abstract class FifthEditPersonInfoPresenter extends CoreBasePresenter<FifthEditPersonInfoModel, FifthEditPersonInfoView> {
        public abstract void getServicePersonal(Context context);

        public abstract void updateUserInfo(UserInfo info);
    }

    abstract class FifthMyHxtPresenter extends CoreBasePresenter<FifthMyHxtModel, FifthMyHxtView> {
        public abstract void getData();
    }

    abstract class FifthEditPwdPresenter extends CoreBasePresenter<FifthEditPwdModel, FifthEditPwdView> {
        public abstract void updateServicePassword(UserInfo userInfo);
    }

    abstract class FifthAddressManagePresenter extends CoreBasePresenter<FifthAddressManageModel, FifthAddressManageView> {
    }

    abstract class FifthMyOrderPresenter extends CoreBasePresenter<FifthMyOrderModel, FifthMyOrderView> {
        public abstract void getServiceOrderList(int id, int start, int userId);

        public abstract void getServiceCancelOrder(int userid, int orderid, int position);
    }

    abstract class FifthFavoritePresenter extends CoreBasePresenter<FifthFavoriteModel, FifthFavoriteView> {
        public abstract void getServiceCancelFav(int USERID, int userid, int favId);

        public abstract void getServiceFavInfo(int userid, int start, int i);
    }

    abstract class FifthMyCouponPresenter extends CoreBasePresenter<FifthMyCouponModel, FifthMyCouponView> {
        public abstract void getServiceCoupon2(int userId, int start, int i, int i1);

        public abstract void getServiceCoupon1(int userId, int start, int i, int i1);
    }

    abstract class FifthSettingPresenter extends CoreBasePresenter<FifthSettingModel, FifthSettingView> {
        public abstract void checkVersion();

        public abstract void getServiceLogOut(String s);
    }



    abstract class FifthMyHxtPwdPresenter extends CoreBasePresenter<FifthMyHxtPwdModel, FifthMyHxtPwdView> {
    }

    abstract class FifthMyHxtDetailPresenter extends CoreBasePresenter<FifthMyHxtDetailModel, FifthMyHxtDetailView> {
    }

    abstract class FifthFindPwdPresenter extends CoreBasePresenter<FifthFindPwdModel, FifthFindPwdView> {
    }

    abstract class FifthHelpListPresenter extends CoreBasePresenter<FifthHelpListModel, FifthHelpListView> {
        public abstract void getServiceHelpList(int i);
    }

    abstract class FifthFeedBackPresenter extends CoreBasePresenter<FifthFeedBackModel, FifthFeedBackView> {
        public abstract void getServiceFeedBack(int userid, String mobilePhoneStr, String s, String contentEtString);
    }

    abstract class FifthHelpDetailPresenter extends CoreBasePresenter<FifthHelpDetailModel, FifthHelpDetailView> {
        public abstract void getServiceHelpList(int helpId);
    }

    abstract class FifthHelpList2Presenter extends CoreBasePresenter<FifthHelpList2Model, FifthHelpList2View> {
    }

    abstract class FifthAboutPresenter extends CoreBasePresenter<FifthAboutModel, FifthAboutView> {
    }

    abstract class FifthNearByPresenter extends CoreBasePresenter<FifthNearByModel, FifthNearByView> {
    }

    abstract class FifthMyOrderDetailPresenter extends CoreBasePresenter<FifthMyOrderDetailModel, FifthMyOrderDetailView> {
        public abstract void getServiceOrderDetail(int userid, int orderId);

        public abstract void getServiceCancelOrder(int userid, int orderId);
    }


}
