package com.honliv.honlivmall.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.honliv.honlivmall.bean.OrderInfo;

/**
 * Created by Rodin on 2016/11/3.
 */
//{ "OrderId" : null, "UserID" : null, "Amount" : null, "
// OrderCode" : null, "CardNo" : null, "CardPwd" : null, "PayType" : null }
public class HXTPayTask extends AsyncTask<String, Void, Boolean> {
    private final OrderInfo orderInfo;
    private Context mContext;

    public HXTPayTask(Context mContext, OrderInfo orderInfo) {
        this.mContext = mContext;
        this.orderInfo = orderInfo;
    }

    @Override
    protected Boolean doInBackground(String... params) {
//        HXTEngine engine = new HXTEngineImpl();
//        //orderInfo.getOrderid() + "", orderInfo.getAllprice(), orderInfo.getOrderCode(),
//        return engine.pay(orderInfo, params[0], params[1], "hxt");
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
//        if (result) {
//            Intent intent = new Intent(mContext, OrderSubmitOkActivity.class);
//            intent.putExtra("orderInfo", orderInfo);
//            intent.putExtra("result", 0);
//            mContext.startActivity(intent);
//        } else {
//            Intent intent = new Intent(mContext, OrderSubmitOkActivity.class);
//            intent.putExtra("orderInfo", orderInfo);
//            intent.putExtra("result", -1);
//            mContext.startActivity(intent);
//        }
    }
}
