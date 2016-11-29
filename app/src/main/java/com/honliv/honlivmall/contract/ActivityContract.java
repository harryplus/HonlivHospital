package com.honliv.honlivmall.contract;

import com.facebook.datasource.DataSource;
import com.honliv.honlivmall.base.CoreBaseView;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.DefaultParas;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/21.
 */
public interface ActivityContract {
    interface FlashModel extends CoreBaseModel {

        Observable<DefaultParas> getServiceDafault();
    }

    interface CaptureModel extends CoreBaseModel {
        Observable<Integer> GetOrder(String contentStr);
    }

    interface PaymentCenterModel extends CoreBaseModel {
        Observable<Float> getServiceTotalPrice(int proSaleId, int groupBuyId, List<HashMap<String,Object>> productList);

        Observable<Float> getServiceFreight(int id, int shipId, List<HashMap<String,Object>> productJsobList, float i);

        Observable<GifInfo> getServiceGifInfo(List<HashMap<String,Object>> productList, float couponPrice);

        Observable<List<AddressInfo>> getServiceAddressList(int arg, int arg2);

        Observable<List<PayShip>> getServicePayList();

        Observable<CouponInfo> GetCunpons(int userid, List<HashMap<String,Object>> productJsobList);
    }

    interface FlashView extends CoreBaseView {

        void updateView(DefaultParas result);
    }

    interface CaptureView extends CoreBaseView {
        void updateView(Integer result);
    }

    interface PaymentCenterView extends CoreBaseView {
        void updateServiceTotalPrice(Float result);

        void updateServiceFreight(Float result);

        void updateServiceGifInfo(GifInfo result);

        void updateServiceAddressList(List<AddressInfo> result);

        void updateServicePayList(List<PayShip> result);

        void updateCunPonListInfos(CouponInfo result);
    }

    abstract class PaymentCenterPresenter extends CoreBasePresenter<PaymentCenterModel, PaymentCenterView> {
        public abstract void getServiceTotalPrice(int proSaleId, int groupBuyId, List<HashMap<String,Object>> productList);

        public abstract void getServiceFreight(int id, int shipId, List<HashMap<String,Object>> productJsobList, int i);

        public abstract void getServiceGifInfo(List<HashMap<String,Object>> productList, float couponPrice);

        public abstract void getServiceAddressList(int arg, int arg2);

        public abstract void getServicePayList();

        public abstract void getCunPonListInfos(int userid, List<HashMap<String,Object>> productJsobList, ArrayList<Product> currentProductList);
    }

    abstract class FlashPresenter extends CoreBasePresenter<FlashModel, FlashView> {
        public abstract void getServiceDafault();
    }

    abstract class CapturePresenter extends CoreBasePresenter<CaptureModel, CaptureView> {
        public abstract void getOrderId(String contentStr);
    }
}
