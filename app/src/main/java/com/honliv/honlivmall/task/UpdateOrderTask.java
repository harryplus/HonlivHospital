package com.honliv.honlivmall.task;

import android.os.AsyncTask;

import com.honliv.honlivmall.bean.OrderInfo;

/**
 * Created by Rodin on 2016/9/18.
 */
public class UpdateOrderTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "UpdateOrderTask";
    private final OrderInfo orderInfo;

    public UpdateOrderTask(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
//        OrderEngine engine = BeanFactory.getImpl(OrderEngine.class);
//        boolean flag = engine.MobilePayUpdateOrder(GloableParams.USERID, orderInfo.getOrderid());

//        Log.i(TAG, GloableParams.USERID + "--USERID--" + flag + "--getOrderid()--" + orderInfo.getOrderid());
        return null;
    }

    protected void onPostExecute(Void result) {
    }
}