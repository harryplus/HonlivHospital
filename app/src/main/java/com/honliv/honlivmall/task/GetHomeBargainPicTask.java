package com.honliv.honlivmall.task;

import android.os.AsyncTask;

import com.honliv.honlivmall.activity.HomeActivity;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.PromptManager;

import java.util.List;

/**
 * Created by Rodin on 2016/10/15.
 */
public class GetHomeBargainPicTask extends AsyncTask<Void, Void, List<Product>> {
//    HomeEngine engine = BeanFactory.getImpl(HomeEngine.class);
    UpdateHomeBargain updateHomeBargain;


    public void setUpdateHomeBargain(UpdateHomeBargain updateHomeBargain) {
        this.updateHomeBargain = updateHomeBargain;
    }

    public interface UpdateHomeBargain {
        void update(List<Product> serviceHomeBargain);
    }

    @Override
    protected List<Product> doInBackground(Void... params) {
//        return engine.getServiceHomeBargain();
        return null;
    }

    @Override
    protected void onPostExecute(List<Product> result) {
        PromptManager.closeProgressDialog();
        updateHomeBargain.update(result);
    }

    @Override
    protected void onPreExecute() {
        PromptManager.showCommonProgressDialog(HomeActivity.mContext);
    }
}
