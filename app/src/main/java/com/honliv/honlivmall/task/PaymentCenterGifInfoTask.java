package com.honliv.honlivmall.task;

import android.content.Intent;
import android.os.AsyncTask;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.util.BeanFactory;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Rodin on 2016/11/4.
 */
public class PaymentCenterGifInfoTask extends AsyncTask<Intent, Void, Object> {
    private List<JSONObject> productJsobList;
    private float couponPrice;

    public void setListener(PostMotion listener) {
        this.listener = listener;
    }

    PostMotion listener;

    interface PostMotion {
        void motion(Object result);
    }

    ;

    @Override
    protected Object doInBackground(Intent... params) {
//        CategoryEngine engine = BeanFactory
//                .getImpl(CategoryEngine.class);
//
//        return engine.getServiceGifProduct(GloableParams.USERID,
//                productJsobList, couponPrice);// getServicePorductDetail
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        listener.motion(result);
    }
}
