package com.honliv.honlivmall.task;

import android.os.AsyncTask;

import com.honliv.honlivmall.bean.HxtBean;

import java.util.List;

/**
 * Created by Rodin on 2016/11/2.
 */
public class HxtUserListTask extends AsyncTask<Void, Void, List<HxtBean>> {
    public void setItem(UpdateView item) {
        this.item = item;
    }

    UpdateView item;

    public interface UpdateView {
        void update(List<HxtBean> list);
    }

    @Override
    protected List<HxtBean> doInBackground(Void... params) {
//        HXTEngine engine = new HXTEngineImpl();
//        List<HxtBean> list = engine.getList();
//        return list;
        return null;
    }

    @Override
    protected void onPostExecute(List<HxtBean> list) {
        item.update(list);
    }
}
