package com.honliv.honlivmall.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.Product;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Rodin on 2016/11/6.
 */
public class GetServiceSubmitOrderTask extends AsyncTask<Integer, Void, Object> {

    private Context mContext;
    private AddressInfo addressInfo;
    private int regionId;
    private int shipId;
    private int payId;
    private List<JSONObject> productJsobList;
    private CouponInfo currentUseCouponInfo;
    private Product favorableProduct;

    public void setListener(PostMotion listener) {
        this.listener = listener;
    }

    private PostMotion listener;

    public interface PostMotion {
        void motion(Object result);
    }

    public GetServiceSubmitOrderTask(Context mContext, AddressInfo addressInfo, int regionId, int shipId, int payId, List<JSONObject> productJsobList, CouponInfo currentUseCouponInfo, Product favorableProduct) {
        this.mContext = mContext;
        this.addressInfo = addressInfo;
        this.regionId = regionId;
        this.shipId = shipId;
        this.payId = payId;
        this.productJsobList = productJsobList;
        this.currentUseCouponInfo = currentUseCouponInfo;
        this.favorableProduct = favorableProduct;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Integer... params) {
//        CategoryEngine engine = BeanFactory
//                .getImpl(CategoryEngine.class);
//        return engine != null ? engine.getServiceSubmitOrder(
//                GloableParams.USERID,
//                addressInfo.getId(),
//                regionId,
//                shipId,
//                payId,
//                0,
//                productJsobList,
//                "",
//                currentUseCouponInfo != null ? currentUseCouponInfo
//                        .getCouponCode() : "",
//                favorableProduct != null ? favorableProduct
//                        .getCountDownId() : 0,
//                favorableProduct != null ? favorableProduct
//                        .getGroupBuyid() : 0) : null;
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        listener.motion(result);
    }
}
