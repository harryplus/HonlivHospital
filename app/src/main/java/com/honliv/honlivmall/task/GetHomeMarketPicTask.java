package com.honliv.honlivmall.task;

import android.os.AsyncTask;

import com.honliv.honlivmall.activity.HomeActivity;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.BeanFactory;
import com.honliv.honlivmall.util.PromptManager;

import java.util.List;

/**
 * Created by Rodin on 2016/10/15.
 */
public class GetHomeMarketPicTask extends AsyncTask<Void, Void, List<Product>> {
//    HomeEngine engine = BeanFactory.getImpl(HomeEngine.class);
    UpdateHomeMarketing updateHomeMarketing;

    InitTimer initTimer;

    public void setInitTimer(InitTimer initTimer) {
        this.initTimer = initTimer;
    }


    public void setUpdateHomeMarketing(UpdateHomeMarketing updateHomeMarketing) {
        this.updateHomeMarketing = updateHomeMarketing;
    }

    public interface UpdateHomeBargain {
        void update(List<Product> serviceHomeBargain);
    }

    public interface UpdateHomeRecommend {
        void update(List<Product> serviceHomeRecommend);
    }

    public interface UpdateHomeMarketing {
        void update(List<Product> result);
    }

    public interface InitTimer {
        void init();
    }

    @Override
    protected List<Product> doInBackground(Void... params) {
//        return engine.getServiceHomeMarketing();
        return null;
    }

    @Override
    protected void onPostExecute(List<Product> result) {
        PromptManager.closeProgressDialog();
        updateHomeMarketing.update(result);
        initTimer.init();
    }

    @Override
    protected void onPreExecute() {
        PromptManager.showCommonProgressDialog(HomeActivity.mContext);
    }
}
