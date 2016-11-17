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
public class GetHomeRecommendPicTask extends AsyncTask<Void, Void, List<Product>> {
//    HomeEngine engine = BeanFactory.getImpl(HomeEngine.class);
    UpdateHomeRecommend updateHomeRecommend;

    public void setUpdateHomeRecommend(UpdateHomeRecommend updateHomeRecommend) {
        this.updateHomeRecommend = updateHomeRecommend;
    }

    public interface UpdateHomeRecommend {
        void update(List<Product> serviceHomeRecommend);
    }

    @Override
    protected List<Product> doInBackground(Void... params) {
//        return engine.getServiceHomeRecommend();
        return null;
    }

    @Override
    protected void onPostExecute(List<Product> result) {
        PromptManager.closeProgressDialog();
        updateHomeRecommend.update(result);
    }

    @Override
    protected void onPreExecute() {
        PromptManager.showCommonProgressDialog(HomeActivity.mContext);
    }
}
