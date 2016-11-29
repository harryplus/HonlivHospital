package com.honliv.honlivmall.contract;


import com.honliv.honlivmall.base.CoreBaseView;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/15.
 */
public interface GlobalContract {
    public interface GlobalAddressListModel extends CoreBaseModel {
        Observable<List<AddressInfo>> addresslist(int userId, int pageIndex, int pageNum);
    }

    interface GlobalAddressListView extends CoreBaseView {
        void updateServiceProductList(List<AddressInfo> result);
    }

    public interface GlobalOfficeSelectModel extends CoreBaseModel {
        Observable<UserInfo> getServiceLoginInfo(UserInfo userInfo);
    }

    interface GlobalPaymentCenterModel extends CoreBaseModel {
        Observable<Float> getServiceTotalPrice(int proSaleId, int groupBuyId, List<HashMap<String, Object>> productList);

        Observable<Float> getServiceFreight(int id, int shipId, List<HashMap<String, Object>> productJsobList, float i);

        Observable<GifInfo> getServiceGifInfo(List<HashMap<String, Object>> productList, float couponPrice);

        Observable<List<AddressInfo>> getServiceAddressList(int arg, int arg2);

        Observable<List<PayShip>> getServicePayList();

        Observable<CouponInfo> GetCunpons(int userid, List<HashMap<String, Object>> productJsobList);

        Observable<OrderInfo> SubmitOrder(int userId, int shipId, int regionId, int shipTypeId, int paymentId, float paymoney, List<HashMap<String, Object>> productList, String remark, String couponCode, int proSaleId, int groupBuyId);
    }

    interface GlobalPaymentCenterView extends CoreBaseView {
        void updateServiceTotalPrice(Float result);

        void updateServiceFreight(Float result);

        void updateServiceGifInfo(GifInfo result);

        void updateServiceAddressList(List<AddressInfo> result);

        void updateServicePayList(List<PayShip> result);

        void updateCunPonListInfos(CouponInfo result);

        void updateServiceSubmitOrder(OrderInfo result);
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

    public interface GlobalAddAddressModel extends CoreBaseModel {
        Observable<List<AddressInfo>> initSpinner1(int i);

        Observable<List<AddressInfo>> initSpinner2(int pcode);

        Observable<List<AddressInfo>> getServiceProductList(AddressInfo addressPreInfo, AddressInfo addressInfo, boolean b);

        Observable<String> getServiceAddressName(int regionId);

        Observable<Boolean> SetAddressDefault(int userid, int id);
    }

    public interface GlobalAddAddressView extends CoreBaseView {
        void updateView(List<AddressInfo> result);

        void updateView2(List<AddressInfo> result);

        void updateServiceProductList(List<AddressInfo> result, boolean b);

        void updateServiceAddressName(String result);

        void updateAddressDefault(Boolean result);
    }

    abstract class GlobalPaymentCenterPresenter extends CoreBasePresenter<GlobalPaymentCenterModel, GlobalPaymentCenterView> {
        public abstract void getServiceTotalPrice(int proSaleId, int groupBuyId, List<HashMap<String, Object>> productList);

        public abstract void getServiceFreight(int id, int shipId, List<HashMap<String, Object>> productJsobList, int i);

        public abstract void getServiceGifInfo(List<HashMap<String, Object>> productList, float couponPrice);

        public abstract void getServiceAddressList(int arg, int arg2);

        public abstract void getServicePayList();

        public abstract void getCunPonListInfos(int userid, List<HashMap<String, Object>> productJsobList, ArrayList<Product> currentProductList);

        public abstract void getServiceSubmitOrder(int userId, int shipId, int regionId,
                                                   int shipTypeId, int paymentId, float paymoney,
                                                   List<HashMap<String,Object>> productList, String remark, String couponCode, int proSaleId, int groupBuyId);
    }

    abstract class GlobalAddressListPresenter extends CoreBasePresenter<GlobalAddressListModel, GlobalAddressListView> {
        public abstract void getServiceProductList(int userId, int pageIndex, int pageNum);
    }

    abstract class GlobalAddAddressPresenter extends CoreBasePresenter<GlobalAddAddressModel, GlobalAddAddressView> {
        public abstract void initSpinner1(int i);

        public abstract void initSpinner2(int pcode);

        public abstract void getServiceProductList(AddressInfo addressPreInfo, AddressInfo addressInfo, boolean b);

        public abstract void getServiceAddressName(int regionId);

        public abstract void addressDefault(int userid, int id);
    }
}
